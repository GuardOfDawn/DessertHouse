package service;

import java.util.ArrayList;

import models.OrderDetail;
import models.Product;
import models.Store;

public interface ProductOrderService {

	/**
	 * 用户搜索产品
	 * @param keywords
	 * @return
	 */
	public ArrayList<Product> searchProducts(String keywords);
	
	/**
	 * 用户得到某一商店的产品
	 * @param storeId
	 * @return
	 */
	public ArrayList<Product> getProductForStore(String storeId);
	
	/**
	 * 点击产品，显示详细信息
	 * @param productId
	 * @return
	 */
	public Product getProduct(String productId);
	
	/**
	 * 获得所有店铺信息
	 * @return
	 */
	public ArrayList<Store> getStore();
	
	/**
	 * 获得一个店铺的所有产品
	 * @param storeId
	 * @return
	 */
	public ArrayList<Product> getProductInStore(String storeId);
	
	/**
	 * 预订某一个产品
	 * @param productId
	 * @return
	 */
	public Product orderProduct(String memberId,String productId);
	
	/**
	 * 向预订单中添加产品
	 * @param item
	 */
	public void addProductToOrder(OrderDetail item);
	
	/**
	 * 从预订单中去除产品
	 * @param productId
	 */
	public void deleteProductFromOrder(String productId);
	
	/**
	 * 确认预订单
	 */
	public boolean ensureOrder();
	
	/**
	 * 取消预订单（中途）
	 */
	public void cancelOrder();
	
	/**
	 * 删除已经预订好的订单
	 */
	public void removeOrder();
	
}
