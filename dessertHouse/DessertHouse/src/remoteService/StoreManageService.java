package remoteService;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Store;

@Remote
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
	 * @param store
	 * @return
	 */
	public boolean modifyStore(Store store);
	
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
