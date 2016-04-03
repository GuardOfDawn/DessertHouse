package dessert.action.memberOrder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Order;
import dessert.remoteService.orderManage.OrderManageService;
import dessert.utility.FormulationNumber;

@Controller
public class OrderCancelAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrderManageService orderManage;
	
	public String execute(){
		if(session.get("member")!=null){
			String orderToCancel = request.getParameter("orderToCancel");
			orderManage.orderCancelled(orderToCancel);
			request.setAttribute("cancelRes", "1");
			Member m = (Member) session.get("member");
			ArrayList<Order> orderMade = orderManage.findOrderByMember(m.getMemberId(), FormulationNumber.orderMade);
			ArrayList<Order> orderPaid = orderManage.findOrderByMember(m.getMemberId(), FormulationNumber.orderPaid);
			ListBean orderMadeBean = new ListBean();
			ListBean orderPaidBean = new ListBean();
			orderMadeBean.setListBean(orderMade);
			orderPaidBean.setListBean(orderPaid);
			request.setAttribute("orderMade", orderMadeBean);
			request.setAttribute("orderPaid", orderPaidBean);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}
	
}
