package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Product;
import dessert.models.StoreUser;
import dessert.service.productOperation.ProductOpService;
import dessert.utility.ProductType;

@Controller
public class ProductViewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductOpService productManage;

	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			StoreUser user = (StoreUser)session.get("user");
			if(user.getUserType().equals("HeadAttendant")){
				ArrayList<Product> productList = productManage.getAllProduct();
				ListBean productListBean = new ListBean();
				productListBean.setListBean(productList);
				session.put("productList", productListBean);
				ListBean productTypeListBean = new ListBean();
				productTypeListBean.setListBean(ProductType.getInstance().getTypeList());
				session.put("productTypeList", productTypeListBean);
				return SUCCESS;
			}
			else{
				return ERROR;
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}
	
}
