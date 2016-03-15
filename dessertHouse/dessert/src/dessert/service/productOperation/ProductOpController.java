package dessert.service.productOperation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.models.Product;
import dessert.remoteService.productManage.ProductManageService;

@Service
public class ProductOpController implements ProductOpService{

	@Autowired
	private ProductManageService productManage;
	
	public boolean addProduct(Product product) {
		return productManage.addProduct(product);
	}

	public boolean deleteProduct(String productId) {
		return productManage.deleteProduct(productId);
	}

	public ArrayList<Product> getAllProduct() {
		return productManage.getAllProduct();
	}

	public Product getProductInfo(String productId) {
		return productManage.findProduct(productId);
	}

	public boolean modifyProduct(Product modifiedProduct) {
		return productManage.modifyProduct(modifiedProduct);
	}
	
}
