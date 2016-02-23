package service;

import java.util.ArrayList;

import models.Store;

public interface StoreManageService {

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
	 * �޸�һ�����̵�����
	 * @param storeId
	 * @param store
	 * @return
	 */
	public boolean modifyStore(String storeId,Store store);
	
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
	
}
