package service;

import java.util.ArrayList;

import models.OrderDetail;
import models.Product;
import models.Store;

public interface ProductOrderService {

	/**
	 * �û�������Ʒ
	 * @param keywords
	 * @return
	 */
	public ArrayList<Product> searchProducts(String keywords);
	
	/**
	 * �û��õ�ĳһ�̵�Ĳ�Ʒ
	 * @param storeId
	 * @return
	 */
	public ArrayList<Product> getProductForStore(String storeId);
	
	/**
	 * �����Ʒ����ʾ��ϸ��Ϣ
	 * @param productId
	 * @return
	 */
	public Product getProduct(String productId);
	
	/**
	 * ������е�����Ϣ
	 * @return
	 */
	public ArrayList<Store> getStore();
	
	/**
	 * ���һ�����̵����в�Ʒ
	 * @param storeId
	 * @return
	 */
	public ArrayList<Product> getProductInStore(String storeId);
	
	/**
	 * Ԥ��ĳһ����Ʒ
	 * @param productId
	 * @return
	 */
	public Product orderProduct(String memberId,String productId);
	
	/**
	 * ��Ԥ��������Ӳ�Ʒ
	 * @param item
	 */
	public void addProductToOrder(OrderDetail item);
	
	/**
	 * ��Ԥ������ȥ����Ʒ
	 * @param productId
	 */
	public void deleteProductFromOrder(String productId);
	
	/**
	 * ȷ��Ԥ����
	 */
	public boolean ensureOrder();
	
	/**
	 * ȡ��Ԥ��������;��
	 */
	public void cancelOrder();
	
	/**
	 * ɾ���Ѿ�Ԥ���õĶ���
	 */
	public void removeOrder();
	
}
