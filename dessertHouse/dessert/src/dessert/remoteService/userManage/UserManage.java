package dessert.remoteService.userManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.StoreUserDao;
import dessert.models.StoreUser;

@Service
public class UserManage implements UserManageService{

	@Autowired
	private StoreUserDao storeUserDao;

	public boolean checkUserLogin(String userId, String password) {
		boolean res = false;
		StoreUser user = storeUserDao.findUser(userId);
		if(user!=null&&user.getPassword().equals(password)){
			res = true;
		}
		return res;
	}

	public StoreUser findStoreUserInfo(String userId) {
		return storeUserDao.findUser(userId);
	}
	
	public boolean updateUser(StoreUser user){
		return storeUserDao.updateUser(user);
	}

}
