package dessert.remoteService.storeManage;

import java.util.ArrayList;

import dessert.models.Store;

public interface StoreManageService {

	public boolean addStore(Store store);
	
	public boolean deleteStore(String storeId);
	
	public boolean modifyStore(Store store);
	
	public ArrayList<Store> getAllStore();
	
	public Store getStoreInfo(String storeId);
	
	public ArrayList<Store> retrieveStore(String province,String city);
	
}
