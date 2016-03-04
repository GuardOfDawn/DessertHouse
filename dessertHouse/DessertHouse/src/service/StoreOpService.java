package service;

import java.util.ArrayList;

import action.business.StoreBean;

public interface StoreOpService {

	/**
	 * ���һ������
	 * @param store
	 * @return
	 */
	public boolean addStore(StoreBean storeBean);
	
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
	public ArrayList<StoreBean> getAllStore();
	
	/**
	 * ��õ�����Ϣ
	 * @param storeId
	 * @return
	 */
	public StoreBean getStoreInfo(String storeId);
	
	/**
	 * ����ʡ������������
	 * �����Ϊ�գ�������ȫʡ�ĵ���
	 * @param province
	 * @param city
	 * @return
	 */
	public ArrayList<StoreBean> retrieveStore(String province,String city);
	
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
	public boolean modifyStore(String storeId,StoreBean modifiedStore);
	
	
}
