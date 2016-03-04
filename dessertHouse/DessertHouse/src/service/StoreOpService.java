package service;

import java.util.ArrayList;

import action.business.StoreBean;

public interface StoreOpService {

	/**
	 * 添加一个店铺
	 * @param store
	 * @return
	 */
	public boolean addStore(StoreBean storeBean);
	
	/**
	 * 删除店铺
	 * @param storeId
	 * @return
	 */
	public boolean deleteStore(String storeId);
	
	/**
	 * 返回所有店铺信息
	 * @return
	 */
	public ArrayList<StoreBean> getAllStore();
	
	/**
	 * 获得店铺信息
	 * @param storeId
	 * @return
	 */
	public StoreBean getStoreInfo(String storeId);
	
	/**
	 * 根据省、市搜索店铺
	 * 如果市为空，则搜索全省的店铺
	 * @param province
	 * @param city
	 * @return
	 */
	public ArrayList<StoreBean> retrieveStore(String province,String city);
	
	/**
	 * 获得所有省
	 * @return
	 */
	public ArrayList<String> getAllProvince();
	
	/**
	 * 获得某一个省所有的市
	 * @param province
	 * @return
	 */
	public ArrayList<String> getAllCity(String province);
	
	/**
	 * 修改一个店铺的信息
	 * @param storeId
	 * @param modifiedStore
	 * @return
	 */
	public boolean modifyStore(String storeId,StoreBean modifiedStore);
	
	
}
