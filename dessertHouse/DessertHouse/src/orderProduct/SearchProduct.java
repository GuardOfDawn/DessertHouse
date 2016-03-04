package orderProduct;

import java.util.ArrayList;

import javax.ejb.EJB;

import models.Product;
import remoteService.ProductManageService;

public class SearchProduct {
	
	@EJB ProductManageService productManage;

	public SearchProduct(){
		
	}
	
	
	public ArrayList<Product> searchProducts(String keywords){
		return productManage.searchProducts(keywords);
	}
	
	public ArrayList<Product> getProductForStore(String storeId){
		return productManage.retrieveProduct(storeId);
	}
	
	public Product getProduct(String productId){
		return productManage.findProduct(productId);
	}
}
