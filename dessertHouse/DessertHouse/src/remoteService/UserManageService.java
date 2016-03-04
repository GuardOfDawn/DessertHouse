package remoteService;

import javax.ejb.Remote;

import models.StoreUser;

@Remote
public interface UserManageService {

	public boolean checkUserLogin(String userId,String password);
	
	public StoreUser findStoreUserInfo(String userId);
	
	public boolean updateUser(StoreUser user);
	
}
