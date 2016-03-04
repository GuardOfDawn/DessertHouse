package service;

import java.util.ArrayList;

import models.Product;

public interface ProductOpService {

	public boolean addProduct(Product product);
	
	public boolean deleteProduct(String productId);
	
	public ArrayList<Product> getAllProduct();
	
	public Product getProductInfo(String productId);
	
	public boolean modifyProduct(String productId,Product modifiedProduct);
	
}
