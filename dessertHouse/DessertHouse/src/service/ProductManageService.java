package service;

import java.util.ArrayList;

import models.Product;

public interface ProductManageService {

	public ArrayList<Product> searchProducts(String keywords);
	
	public Product findProduct(String productId);
	
}
