package dessert.service.storeOperation;

import java.util.ArrayList;

import dessert.models.Store;

public interface StoreOpService {

	public boolean addStore(Store store);
	
	public boolean deleteStore(String storeId);
	
	public ArrayList<Store> getAllStore();
	
	public ArrayList<String> getAllStoreId();
	
	public Store getStoreInfo(String storeId);
	
	public ArrayList<Store> retrieveStore(String province,String city);

	public ArrayList<String> getAllProvince();
	
	public ArrayList<String> getAllCity(String province);
	
	public boolean modifyStore(Store modifiedStore);
	
	
}
