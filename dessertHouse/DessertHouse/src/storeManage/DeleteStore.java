package storeManage;

import javax.ejb.EJB;

import dao.StoreDao;

public class DeleteStore {

	private static DeleteStore deleteStore = new DeleteStore();
	
	private DeleteStore(){

	}
	
	public static DeleteStore getInstance(){
		return deleteStore;
	}
	
	@EJB StoreDao storeDao;
	
	public boolean deleteStore(String storeId){
		String[] columns = {"storeId"};
		String[] values = new String[1];
		values[0] = storeId;
		return storeDao.removeStore(columns, values);
	}
	
}
