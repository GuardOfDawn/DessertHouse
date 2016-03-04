package service;

import action.business.StoreUserBean;

public interface UserOpService {

	public boolean checkStoreUserLogin(String userId,String password);
	
	public StoreUserBean getStoreUser(String userId);
	
}
