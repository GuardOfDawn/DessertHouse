package dessert.action.headAttendant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Product;
import dessert.remoteService.productManage.ProductManageService;

@Controller
public class ProductModifyAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductManageService productManage;
	
	public String execute(){
		if(session.get("userId")!=null){
			String modifiedId = request.getParameter("modifiedId");
			Product product = productManage.findProduct(modifiedId);
			//TODO
			product.setProductType(request.getParameter("productType"));
			String imageName = (request.getParameter("imagePath")==null)?"default.jpg":request.getParameter("imagePath");
			String imagePath = "/upload/" + imageName;
			product.setImagePath(imagePath);
			productManage.modifyProduct(product);
			request.setAttribute("modifiedProduct",product);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
