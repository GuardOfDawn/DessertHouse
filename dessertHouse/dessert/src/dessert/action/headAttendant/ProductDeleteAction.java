package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Product;
import dessert.service.productOperation.ProductOpService;

@Controller
public class ProductDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductOpService productManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String productId = request.getParameter("productId");
			boolean delRes = productManage.deleteProduct(productId);
			request.setAttribute("deleteRes", String.valueOf(delRes));
			request.setAttribute("deleteProductId", productId);
			if(delRes){
				Object product = null;
	        	for(Object o:session.keySet()){
	        		if(o.equals("productList")){
	        			product = o;
	        		}
	        	}
	        	session.remove(product);
	        	ArrayList<Product> productList = productManage.getAllProduct();
	        	ListBean productListBean = new ListBean();
	        	productListBean.setListBean(productList);
	        	session.put("productList", productListBean);
	        	return SUCCESS;
			}
			else{
				return "failure";
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
