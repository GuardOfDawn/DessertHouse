package dessert.remoteService.productManage;

import java.util.ArrayList;

import dessert.models.Product;

public interface ProductManageService {

	public ArrayList<Product> searchProducts(String keywords);
	
	public Product findProduct(String productId);

	/**
	 * TODO ????
	 * @param storeId
	 * @return
	 */
	public ArrayList<Product> retrieveProduct(String storeId);
	
	public ArrayList<Product> getAllProduct();
	
	public boolean addProduct(Product product);
	
	public boolean deleteProduct(String productId);
	
	public boolean modifyProduct(Product product);
	
}
