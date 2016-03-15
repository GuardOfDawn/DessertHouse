package dessert.service.productOperation;

import java.util.ArrayList;

import dessert.models.Product;

public interface ProductOpService {

	public boolean addProduct(Product product);
	
	public boolean deleteProduct(String productId);
	
	public ArrayList<Product> getAllProduct();
	
	public Product getProductInfo(String productId);
	
	public boolean modifyProduct(Product modifiedProduct);
	
}
