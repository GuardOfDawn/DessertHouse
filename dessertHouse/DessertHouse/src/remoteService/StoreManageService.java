package remoteService;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Store;

@Remote
public interface StoreManageService {

	/**
	 * 添加一个店铺
	 * @param store
	 * @return
	 */
	public boolean addStore(Store store);
	
	/**
	 * 删除店铺
	 * @param storeId
	 * @return
	 */
	public boolean deleteStore(String storeId);
	
	/**
	 * 修改一个店铺的名称
	 * @param store
	 * @return
	 */
	public boolean modifyStore(Store store);
	
	/**
	 * 返回所有店铺信息
	 * @return
	 */
	public ArrayList<Store> getAllStore();
	
	/**
	 * 获得店铺信息
	 * @param storeId
	 * @return
	 */
	public Store getStoreInfo(String storeId);
	
	/**
	 * 根据省、市搜索店铺
	 * 如果市为空，则搜索全省的店铺
	 * @param province
	 * @param city
	 * @return
	 */
	public ArrayList<Store> retrieveStore(String province,String city);
	
}
