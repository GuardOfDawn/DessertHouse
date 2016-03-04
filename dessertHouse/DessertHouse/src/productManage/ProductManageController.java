package productManage;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.ProductDao;
import models.Product;
import remoteService.ProductManageService;

@Stateless
public class ProductManageController implements ProductManageService{
	
	@EJB ProductDao productDao;

	public ArrayList<Product> searchProducts(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product findProduct(String productId) {
		String[] columns = {"productId"};
		String[] values = new String[1];
		values[0] = productId;
		ArrayList<Product> productList = productDao.findProduct(columns, values);
		if(productList!=null&&productList.size()==1){
			return productList.get(0);
		}
		else{
			return null;
		}
	}
	
	public ArrayList<Product> retrieveProduct(String storeId){
		String[] columns = {"storeId"};
		String[] values = new String[1];
		values[0] = storeId;
		return productDao.findProduct(columns, values);
	}

	public ArrayList<Product> getAllProduct() {
		return productDao.findAllProduct();
	}

	public boolean addProduct(Product product) {
		return productDao.saveProduct(product);
	}

	public boolean deleteProduct(String productId) {
		String[] columns = {"productId"};
		String[] values = new String[1];
		values[0] = productId;
		return productDao.removeProduct(columns, values);
	}

	public boolean modifyProduct(Product product) {
		return productDao.updateProduct(product);
	}

}
