package dessert.service.userOperation;

import dessert.models.StoreUser;

public interface UserOpService {

	public boolean checkStoreUserLogin(String userId,String password);
	
	public StoreUser getStoreUser(String userId);
	
	public boolean updateUser(StoreUser user);
	
}
