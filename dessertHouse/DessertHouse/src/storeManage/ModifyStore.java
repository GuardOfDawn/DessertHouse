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
	
	public boolean modifyStore(String storeId,Store store) {
		if(storeId!=null){
			String[] columns = {"storeName","storeTel","province","city","storeLoc"};
			String[] values = new String[5];
			values[0] = store.getStoreName();
			values[1] = store.getStoreTel();
			values[2] = store.getProvince();
			values[3] = store.getCity();
			values[4] = store.getStoreLoc();
			return storeDao.modifyStore(storeId, columns, values);
		}
		else{
			return false;
		}
	}
	
}
