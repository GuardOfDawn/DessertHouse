package dessert.action.memberOrder;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;
import dessert.remoteService.orderManage.OrderManageService;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.remoteService.storeManage.StoreManageService;
import dessert.utility.BonusUtility;
import dessert.utility.DayTransformer;
import dessert.utility.FormulationNumber;
import dessert.utility.IDProducer;

@Controller
public class EnsureOrderAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StoreManageService storeManage;
	@Autowired
	private OrderManageService orderManage;
	@Autowired
	private ScheduleManageService scheduleManage;
	
	public String execute(){
		if(session.get("member")!=null){
			ListBean orderItemListBean = (ListBean)session.get("orderItemList");
			if(orderItemListBean!=null){
				Order order = new Order();
				order.setOrderId(IDProducer.getInstance().produceOrderId());
				order.setOrderTime(new Date(System.currentTimeMillis()));
				String targetDateString = request.getParameter("targetDate");
				Date targetDate = null;
				if(targetDateString==null||targetDateString.equals("null")){
					targetDate = new Date(System.currentTimeMillis());
				}
				else{
					targetDate = DayTransformer.transform(targetDateString);
				}
				order.setTargetDay(targetDate);
				order.setOrderMember((Member)session.get("member"));
				String storeId = request.getParameter("storeId");
				order.setOrderStore(storeManage.getStoreInfo(storeId));
				order.setOrderCost(Double.parseDouble(request.getParameter("total")));
				order.setFavorRate(Double.parseDouble(request.getParameter("favorRate")));
				order.setBonusUsed(Integer.parseInt(request.getParameter("memberBonusUsed")));
				double finalTotal = Double.parseDouble(request.getParameter("finalTotal"));
				order.setCostAfterDiscount(finalTotal);
				order.setBonusGiven(BonusUtility.getInstance().getBonus(finalTotal));
				order.setOrderState(FormulationNumber.orderMade);
				boolean res = orderManage.saveOrder(order);
				if(res){
					WeekSchedule schedule = scheduleManage.retrieveSchedule(storeId, order.getTargetDay());
					ArrayList<ScheduleDetail> detailList = scheduleManage.retrieveScheduleDetail(schedule.getScheduleId(),order.getTargetDay());
					for(int i=0;i<orderItemListBean.getListBean().size();i++){
						OrderDetail detail = (OrderDetail) orderItemListBean.getBean(i);
						detail.setOrder(order);
						orderManage.saveOrderDetail(detail);
						//update product remaining count
						for(ScheduleDetail item:detailList){
							if(item.getProductId().equals(detail.getProduct().getProductId())){
								item.setRemainingCount(item.getRemainingCount()-detail.getProductCount());
								scheduleManage.updateScheduleDetail(item);
								break;
							}
						}
					}
					request.setAttribute("orderRes", "1");
					session.remove("orderItemList");
					return SUCCESS;
				}
				else{
					request.setAttribute("orderRes", "0");
					return "failure";
				}
			}
			else{
				request.setAttribute("orderRes", "0");
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
