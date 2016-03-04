package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Product;

@Local
public interface ProductDao {

	public boolean saveProduct(Product product);
	
	public boolean removeProduct(String[] columns,String[] values);
	
	public ArrayList<Product> findProduct(String[] columns,String[] values);
	
	public ArrayList<Product> findAllProduct();
	
	public boolean updateProduct(Product product);
	
}
