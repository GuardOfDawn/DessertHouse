package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Store;

@Local
public interface StoreDao {

	public boolean saveStore(Store store);
	
	public boolean removeStore(String[] columns,String[] values);
	
	public ArrayList<Store> findStore(String[] columns,String[] values);
	
	public ArrayList<Store> findAllStore();
	
	public boolean updateStore(Store store);
	
}
