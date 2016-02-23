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
		String[] columns = {"storeId","storeName","storeTel","province","city","storeLoc"};
		String[] values = new String[6];
		values[0] = store.getStoreId();
		values[1] = store.getStoreName();
		values[2] = store.getStoreTel();
		values[3] = store.getProvince();
		values[4] = store.getCity();
		values[5] = store.getStoreLoc();
		return storeDao.insertStore(columns, values);
	}
	
}
