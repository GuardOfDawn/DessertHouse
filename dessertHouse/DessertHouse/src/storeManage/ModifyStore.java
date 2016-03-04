package storeManage;

import javax.ejb.EJB;

import dao.StoreDao;
import models.Store;

public class ModifyStore {

	private static ModifyStore modifyStore = new ModifyStore();
	
	private ModifyStore(){

	}
	
	public static ModifyStore getInstance(){
		return modifyStore;
	}
	
	@EJB StoreDao storeDao;
	
	public boolean modifyStore(Store store) {
		return storeDao.updateStore(store);
	}
	
}
