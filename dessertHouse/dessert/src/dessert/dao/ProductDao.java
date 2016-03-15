package dessert.dao;

import java.util.ArrayList;

import dessert.models.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);
	
	public boolean removeProduct(String[] columns,String[] values);
	
	public Product findProduct(String productId);
	
	public ArrayList<Product> findProduct(String[] columns,String[] values);
	
	public ArrayList<Product> findAllProduct();
	
	public boolean updateProduct(Product product);
	
}
