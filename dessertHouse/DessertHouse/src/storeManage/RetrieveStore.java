package storeManage;

import java.util.ArrayList;

import javax.ejb.EJB;

import dao.StoreDao;
import models.Store;

public class RetrieveStore {

	private static RetrieveStore retrieveStore = new RetrieveStore();
	
	private RetrieveStore(){

	}
	
	public static RetrieveStore getInstance(){
		return retrieveStore;
	}
	
	@EJB StoreDao storeDao;
	
	public Store getStoreInfo(String storeId){
		String[] columns = {"storeId"};
		String[] values = new String[0];
		values[0] = storeId;
		ArrayList<Store> s = storeDao.findStore(columns, values);
		if(s!=null&&s.size()>0){
			return s.get(0);
		}
		else{
			return null;
		}
	}
	
	public ArrayList<Store> retrieveStore(String province, String city) {
		if(province!=null){
			if(city!=null){
				String[] columns = {"province","city"};
				String[] values = new String[2];
				values[0] = province;
				values[1] = city;
				return storeDao.findStore(columns, values);
			}
			else{
				String[] columns = {"province"};
				String[] values = new String[1];
				values[0] = province;
				return storeDao.findStore(columns, values);
			}
		}
		else{
			if(city!=null){
				String[] columns = {"city"};
				String[] values = new String[1];
				values[0] = city;
				return storeDao.findStore(columns, values);
			}
			else{
				return getAllStore();
			}
		}
	}
	
	public ArrayList<Store> getAllStore() {
		return storeDao.findAllStore();
	}
	
}
