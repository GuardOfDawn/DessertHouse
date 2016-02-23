package storeOperation;

import java.util.ArrayList;

import models.Store;

public interface StoreOpService {

	/**
	 * ���һ������
	 * @param store
	 * @return
	 */
	public boolean addStore(Store store);
	
	/**
	 * ɾ������
	 * @param storeId
	 * @return
	 */
	public boolean deleteStore(String storeId);
	
	/**
	 * �������е�����Ϣ
	 * @return
	 */
	public ArrayList<Store> getAllStore();
	
	/**
	 * ��õ�����Ϣ
	 * @param storeId
	 * @return
	 */
	public Store getStoreInfo(String storeId);
	
	/**
	 * ����ʡ������������
	 * �����Ϊ�գ�������ȫʡ�ĵ���
	 * @param province
	 * @param city
	 * @return
	 */
	public ArrayList<Store> retrieveStore(String province,String city);
	
	/**
	 * �������ʡ
	 * @return
	 */
	public ArrayList<String> getAllProvince();
	
	/**
	 * ���ĳһ��ʡ���е���
	 * @param province
	 * @return
	 */
	public ArrayList<String> getAllCity(String province);
	
	/**
	 * �޸�һ�����̵���Ϣ
	 * @param storeId
	 * @param modifiedStore
	 * @return
	 */
	public boolean modifyStore(String storeId,Store modifiedStore);
	
	
}
