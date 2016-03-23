package dessert.action.memberOrder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.models.Product;
import dessert.models.Store;
import dessert.remoteService.orderManage.OrderManageService;
import dessert.service.productOperation.ProductOpService;
import dessert.service.storeOperation.StoreOpService;
import dessert.utility.FormulationNumber;
import dessert.utility.MemberLevelUtility;

@Controller
public class OrderProductAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductOpService productManage;
	@Autowired
	private StoreOpService storeManage;
	@Autowired
	private OrderManageService orderManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("member")!=null){
			String orderString = request.getParameter("order");
			String[] orderListString = orderString.split(";");
			ArrayList<OrderDetail> orderItemList = new ArrayList<OrderDetail>();
			double newOrderCost = 0;
			for(String orderItemString:orderListString){
				String[] parts = orderItemString.split(",");
				OrderDetail item = new OrderDetail();
				Product p = productManage.getProductInfo(parts[0]);
				item.setProduct(p);
				item.setProductPrice(Double.parseDouble(parts[1]));
				item.setProductCount(Integer.parseInt(parts[2]));
				newOrderCost += item.getProductPrice()*item.getProductCount();
				orderItemList.add(item);
			}
			//check whether member has enough residual
			Member m = (Member)session.get("member");
			newOrderCost = newOrderCost*MemberLevelUtility.getInstance().getFavorRate(m.getMemberLevel());
			ArrayList<Order> orders = orderManage.findOrderByMember(m.getMemberId(), FormulationNumber.orderMade);
			double total = 0;
			for(int i=0;i<orders.size();i++){
				total += orders.get(i).getCostAfterDiscount();
			}
			if((m.getResidual()-total)>=newOrderCost){
				ListBean orderItemListBean = new ListBean();
				orderItemListBean.setListBean(orderItemList);
				session.put("orderItemList", orderItemListBean);
				double favorRate = MemberLevelUtility.getInstance().getFavorRate(m.getMemberLevel());
				request.setAttribute("favorRate", Double.valueOf(favorRate));
				String storeId = request.getParameter("storeId");
				Store store = storeManage.getStoreInfo(storeId);
				request.setAttribute("store", store);
				return SUCCESS;
			}
			else{
				request.setAttribute("orderProductFail", "1");
				String storeId = request.getParameter("storeId");
				Store store = storeManage.getStoreInfo(storeId);
				request.setAttribute("store", store);
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
