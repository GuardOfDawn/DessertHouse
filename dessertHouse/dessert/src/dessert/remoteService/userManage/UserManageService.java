package dessert.remoteService.userManage;

import dessert.models.StoreUser;

public interface UserManageService {

	public boolean checkUserLogin(String userId,String password);
	
	public StoreUser findStoreUserInfo(String userId);
	
	public boolean updateUser(StoreUser user);
	
}
