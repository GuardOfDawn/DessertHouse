package storeOperation;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import action.business.StoreBean;
import models.Store;
import remoteService.StoreManageService;
import service.StoreOpService;

@Service
public class StoreOperationController implements StoreOpService{
	
	@EJB StoreManageService storeManage;
	
	public StoreOperationController(){
		
	}

	public boolean addStore(StoreBean storeBean) {
		Store store = new Store();
		store.setStoreId(storeBean.getStoreId());
		store.setStoreName(storeBean.getStoreName());
		store.setStoreTel(storeBean.getStoreTel());
		store.setProvince(storeBean.getProvince());
		store.setCity(storeBean.getCity());
		store.setStoreLoc(storeBean.getStoreLoc());
		return storeManage.addStore(store);
	}

	public boolean deleteStore(String storeId) {
		return storeManage.deleteStore(storeId);
	}

	public ArrayList<StoreBean> getAllStore() {
		ArrayList<Store> storeList = storeManage.getAllStore();
		ArrayList<StoreBean> storeBeanList = new ArrayList<StoreBean>();
		if(storeList!=null){
			for(Store store:storeList){
				StoreBean storeBean = new StoreBean();
				storeBean.setStoreId(store.getStoreId());
				storeBean.setStoreName(store.getStoreName());
				storeBean.setStoreTel(store.getStoreTel());
				storeBean.setProvince(store.getProvince());
				storeBean.setCity(store.getCity());
				storeBean.setStoreLoc(store.getStoreLoc());
				storeBeanList.add(storeBean);
			}
		}
		return storeBeanList;
	}

	public StoreBean getStoreInfo(String storeId) {
		StoreBean storeBean = new StoreBean();
		Store store= storeManage.getStoreInfo(storeId);
		if(store!=null){
			storeBean.setStoreId(storeId);
			storeBean.setStoreName(store.getStoreName());
			storeBean.setStoreTel(store.getStoreTel());
			storeBean.setProvince(store.getProvince());
			storeBean.setCity(store.getCity());
			storeBean.setStoreLoc(store.getStoreLoc());
			return storeBean;
		}
		else{
			return null;
		}
	}

	public ArrayList<StoreBean> retrieveStore(String province, String city) {
		ArrayList<Store> storeList = storeManage.retrieveStore(province, city);
		ArrayList<StoreBean> storeBeanList = new ArrayList<StoreBean>();
		if(storeList!=null){
			for(Store store:storeList){
				StoreBean storeBean = new StoreBean();
				storeBean.setStoreId(store.getStoreId());
				storeBean.setStoreName(store.getStoreName());
				storeBean.setStoreTel(store.getStoreTel());
				storeBean.setProvince(store.getProvince());
				storeBean.setCity(store.getCity());
				storeBean.setStoreLoc(store.getStoreLoc());
				storeBeanList.add(storeBean);
			}
		}
		return storeBeanList;
	}

	public ArrayList<String> getAllProvince() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllCity(String province) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modifyStore(String storeId,StoreBean modifiedStoreBean){
		Store store = storeManage.getStoreInfo(storeId);
		if(store!=null){
			store.setStoreName(modifiedStoreBean.getStoreName());
			store.setStoreTel(modifiedStoreBean.getStoreTel());
			store.setStoreLoc(modifiedStoreBean.getStoreLoc());
			return storeManage.modifyStore(store);
		}
		else{
			return false;
		}
	}

}
