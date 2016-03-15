package dessert.dao;

import java.util.ArrayList;

import dessert.models.Store;

public interface StoreDao {

	public boolean saveStore(Store store);
	
	public boolean deleteStore(Store store);
	
	public boolean removeStore(String[] columns,String[] values);
	
	public ArrayList<Store> findStore(String[] columns,String[] values);
	
	public ArrayList<Store> findAllStore();
	
	public boolean updateStore(Store store);
	
}
