package orderProduct;

import java.util.ArrayList;

import models.OrderDetail;
import models.Product;
import models.Store;

public class ProductOrderController 
		implements ProductOrderService,ProductPurchaseService{

	private SearchProduct searchProduct;
	private OrderProduct orderProduct;
	
	public ProductOrderController(){
		searchProduct = new SearchProduct();
		orderProduct = new OrderProduct();
	}
	
	public ArrayList<Product> searchProducts(String keywords) {
		return searchProduct.searchProducts(keywords);
	}

	public Product getProduct(String productId) {
		return searchProduct.getProduct(productId);
	}
	
	public ArrayList<Store> getStore() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Product> getProductInStore(String storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product orderProduct(String memberId,String productId) {
		Product p = searchProduct.getProduct(productId);
		orderProduct.startOrder(memberId);
		return p;
	}

	public void addProductToOrder(OrderDetail item) {
		orderProduct.addOrderItem(item);
	}

	public void deleteProductFromOrder(String productId) {
		orderProduct.deleteOrderItem(productId);
	}

	public boolean ensureOrder() {
		return orderProduct.saveOrder();
	}

	public void cancelOrder() {
		orderProduct.cancelOrder();
	}

	public void removeOrder() {
		// TODO Auto-generated method stub
		
	}
	
}
