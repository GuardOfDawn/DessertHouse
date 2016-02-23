package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Store;

@Remote
public interface StoreDao {

	public boolean insertStore(String[] columns,String[] values);
	
	public boolean removeStore(String[] columns,String[] values);
	
	public ArrayList<Store> findStore(String[] columns,String[] values);
	
	public ArrayList<Store> findAllStore();
	
	public boolean modifyStore(String storeId,String[] columns,String[] values);
	
}
