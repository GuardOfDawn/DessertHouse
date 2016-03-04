package productOperation;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import models.Product;
import remoteService.ProductManageService;
import service.ProductOpService;

@Service
public class ProductController implements ProductOpService{

	@EJB ProductManageService productManage;
	
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

	public boolean modifyProduct(String productId, Product modifiedProduct) {
		Product product = productManage.findProduct(productId);
		if(product!=null){
			product.setProductName(modifiedProduct.getProductName());
			product.setSellPrice(modifiedProduct.getSellPrice());
			product.setOpeningPrice(modifiedProduct.getOpeningPrice());
			product.setStockQuantity(modifiedProduct.getStockQuantity());
			return productManage.modifyProduct(product);
		}
		else{
			return false;
		}
	}
	
}
