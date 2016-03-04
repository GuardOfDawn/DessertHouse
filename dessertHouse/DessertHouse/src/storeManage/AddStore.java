package storeManage;

import javax.ejb.EJB;

import dao.StoreDao;
import models.Store;

public class AddStore {

	private static AddStore addStore = new AddStore();
	
	private AddStore(){

	}
	
	public static AddStore getInstance(){
		return addStore;
	}
	
	@EJB StoreDao storeDao;
	
	public boolean addStore(Store store){
		return storeDao.saveStore(store);
	}
	
}
