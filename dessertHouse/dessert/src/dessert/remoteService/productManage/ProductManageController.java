package dessert.remoteService.productManage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.ProductDao;
import dessert.models.Product;

@Service
public class ProductManageController implements ProductManageService{
	
	@Autowired
	private ProductDao productDao;

	public ArrayList<Product> searchProducts(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product findProduct(String productId) {
		return productDao.findProduct(productId);
	}
	
	public ArrayList<Product> retrieveProduct(String storeId){
		//TODO retrieve form schedule
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
		//judge whether there are any bills or orders of this product TODO
		String[] columns = {"productId"};
		String[] values = new String[1];
		values[0] = productId;
		return productDao.removeProduct(columns, values);
	}

	public boolean modifyProduct(Product product) {
		return productDao.updateProduct(product);
	}

}
