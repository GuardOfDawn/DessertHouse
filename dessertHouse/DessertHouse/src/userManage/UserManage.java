package userManage;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.StoreUserDao;
import models.StoreUser;
import remoteService.UserManageService;

@Stateless
public class UserManage implements UserManageService{
	
	@EJB StoreUserDao storeUserDao;

	public boolean checkUserLogin(String userId, String password) {
		return storeUserDao.checkLogin(userId, password);
	}

	public StoreUser findStoreUserInfo(String userId) {
		return storeUserDao.findUser(userId);
	}
	
	public boolean updateUser(StoreUser user){
		return storeUserDao.updateUser(user);
	}

}
