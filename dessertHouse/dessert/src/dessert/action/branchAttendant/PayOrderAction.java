package dessert.action.branchAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.Store;
import dessert.models.StoreUser;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.remoteService.orderManage.OrderManageService;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.utility.FormulationNumber;

@Controller
public class PayOrderAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SalespersonManageService salespersonManage;
	@Autowired
	private OrderManageService orderManage;
	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("user")!=null){
			Store store = (Store) session.get("store");
			if(store==null){
				store = salespersonManage.getSalespersonStore(((StoreUser) session.get("user")).getUserId());
				session.put("store", store);
			}
			String orderId = request.getParameter("orderId");
			orderManage.orderPayed(orderId);
			request.setAttribute("payRes", "1");
			String memberId = request.getParameter("memberId");
			Member m = memberManage.checkMemberInfo(memberId);
			ArrayList<Order> orderMade = orderManage.findTodaysOrderForMember(m.getMemberId(), store.getStoreId(), FormulationNumber.orderMade);
			if(orderMade!=null&&orderMade.size()>0){
				ListBean orderMadeBean = new ListBean();
				orderMadeBean.setListBean(orderMade);
				request.setAttribute("member", m);
				request.setAttribute("memberId", memberId);
				request.setAttribute("orderForMember", orderMadeBean);
				return SUCCESS;
			}
			else{
				request.setAttribute("searchRes", "noOrder");
				request.setAttribute("memberId", memberId);
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
