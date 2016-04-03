package dessert.remoteService.orderManage;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.MemberDao;
import dessert.dao.OrderDao;
import dessert.dao.ScheduleDao;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;

@Service
public class OrderManageController implements OrderManageService{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private ScheduleDao scheduleDao;
	
	public boolean saveOrder(Order order) {
		return orderDao.saveOrder(order);
	}

	public boolean saveOrderDetail(OrderDetail detail){
		return orderDao.saveOrderDetail(detail);
	}

	public ArrayList<Order> findOrderByMember(String memberId,int orderState) {
		String[] columns = {"memberId","orderState"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = String.valueOf(orderState);
		ArrayList<Order> orderList = orderDao.findOrder(columns, values);
		return orderList;
	}
	
	public ArrayList<Order> findTodaysOrderForMember(String memberId,String storeId ,int orderState){
		String[] columns = {"memberId","storeId","orderState"};
		String[] values = new String[3];
		values[0] = memberId;
		values[1] = storeId;
		values[2] = String.valueOf(orderState);
		ArrayList<Order> orderList = orderDao.findOrder(columns, values);
		ArrayList<Order> todaysOrder = new ArrayList<Order>();
		Date curDate = new Date(System.currentTimeMillis());
		for(Order o:orderList){
			if(DayTransformer.transform(o.getTargetDay()).equals(DayTransformer.transform(curDate))){
				todaysOrder.add(o);
			}
		}
		return todaysOrder;
	}
	
	public ArrayList<OrderDetail> findOrderDetail(String orderId){
		return orderDao.findOrderDetail(orderId);
	}

	public void orderPayed(String orderId) {
		Order order = orderDao.findOrder(orderId);
		order.setOrderState(FormulationNumber.orderPaid);
		//update member info
		Member m = memberDao.find(order.getOrderMember().getMemberId());
		m.setResidual(m.getResidual()-order.getCostAfterDiscount());
		m.setBonusPoint(m.getBonusPoint()-order.getBonusUsed()+order.getBonusGiven());
		m.setLatestUsage(new Date(System.currentTimeMillis()));
		memberDao.updateMember(m);
		//update order state
		orderDao.updateOrder(order);
	}
	
	public void orderCancelled(String orderId){
		Order order = orderDao.findOrder(orderId);
		order.setOrderState(FormulationNumber.orderCanceled);
		//update member info
		Member m = memberDao.find(order.getOrderMember().getMemberId());
		m.setResidual(m.getResidual()-0.02*order.getOrderCost());
		memberDao.updateMember(m);
		//update schedule product remaining count
		
		Date[] period = {order.getTargetDay(),order.getTargetDay()};
		ArrayList<WeekSchedule> sList = scheduleDao.retrieveScheduleForStore(order.getOrderStore().getStoreId(), period, FormulationNumber.scheduleApproved);
		WeekSchedule schedule = sList.get(0);
		ArrayList<ScheduleDetail> detailList = new ArrayList<ScheduleDetail>();
		for(ScheduleDetail detail:scheduleDao.findScheduleDetail(schedule.getScheduleId())){
			if(DayTransformer.transform(detail.getScheduleDate()).equals(DayTransformer.transform(order.getTargetDay()))){
				detailList.add(detail);
			}
		}
		for(OrderDetail detail:orderDao.findOrderDetail(orderId)){
			for(ScheduleDetail item:detailList){
				if(item.getProductId().equals(detail.getProduct().getProductId())){
					item.setRemainingCount(item.getRemainingCount()+detail.getProductCount());
					scheduleDao.updateScheduleDetail(item);
					break;
				}
			}
		}
		//update order state
		orderDao.updateOrder(order);
	}

}
