package dessert.remoteService.orderManage;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.OrderDao;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;

@Service
public class OrderManageController implements OrderManageService{

	@Autowired
	private OrderDao orderDao;
	
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
			if(DayTransformer.transform(o.getOrderTime()).equals(DayTransformer.transform(curDate))){
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
	}

}
