package dessert.action.memberOrder;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.remoteService.orderManage.OrderManageService;
import dessert.service.storeOperation.StoreOpService;
import dessert.utility.FormulationNumber;
import dessert.utility.IDProducer;

@Controller
public class EnsureOrderAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StoreOpService storeManage;
	@Autowired
	private OrderManageService orderManage;
	
	public String execute(){
		if(session.get("member")!=null){
			ListBean orderItemListBean = (ListBean)session.get("orderItemList");
			if(orderItemListBean!=null){
				Order order = new Order();
				order.setOrderId(IDProducer.getInstance().produceOrderId());
				order.setOrderTime(new Date(System.currentTimeMillis()));
				order.setOrderMember((Member)session.get("member"));
				String storeId = request.getParameter("storeId");
				order.setOrderStore(storeManage.getStoreInfo(storeId));
				order.setOrderCost(Double.parseDouble(request.getParameter("total")));
				order.setFavorRate(Double.parseDouble(request.getParameter("favorRate")));
				order.setBonusUsed(Integer.parseInt(request.getParameter("memberBonusUsed")));
				order.setCostAfterDiscount(Double.parseDouble(request.getParameter("finalTotal")));
				order.setOrderState(FormulationNumber.orderMade);
				boolean res = orderManage.saveOrder(order);
				if(res){
					for(int i=0;i<orderItemListBean.getListBean().size();i++){
						OrderDetail item = (OrderDetail) orderItemListBean.getBean(i);
						item.setOrder(order);
						orderManage.saveOrderDetail(item);
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
