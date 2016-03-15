package dessert.service.userOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.models.StoreUser;
import dessert.remoteService.userManage.UserManageService;

@Service
public class UserOperationController implements UserOpService{

	@Autowired
	private UserManageService userManage;
	
	public boolean checkStoreUserLogin(String userId, String password) {
		return userManage.checkUserLogin(userId, password);
	}

	public StoreUser getStoreUser(String userId) {
		StoreUser user = userManage.findStoreUserInfo(userId);
		return user;
	}
	
	public boolean updateUser(StoreUser user){
		return userManage.updateUser(user);
	}

}
