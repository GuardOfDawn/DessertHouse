package productManage;

import models.Product;
import saleManage.ProductForSaleService;

public class ProductInfo implements ProductForSaleService{

	private static ProductInfo productInfo = new ProductInfo();
	
	private ProductInfo(){

	}
	
	public static ProductInfo getInstance(){
		return productInfo;
	}
	
	public Product getProductInfo(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
