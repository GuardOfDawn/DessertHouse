package userOperation;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import action.business.StoreUserBean;
import models.StoreUser;
import remoteService.UserManageService;
import service.UserOpService;

@Service
public class UserOperationController implements UserOpService{

	@EJB UserManageService userManage;
	
	public boolean checkStoreUserLogin(String userId, String password) {
		return userManage.checkUserLogin(userId, password);
	}

	public StoreUserBean getStoreUser(String userId) {
		StoreUser user = userManage.findStoreUserInfo(userId);
		StoreUserBean userBean = new StoreUserBean();
		userBean.setUserId(user.getUserId());
		userBean.setUserName(user.getUserName());
		userBean.setUserType(user.getUserType());
		userBean.setLastLogoutTime(user.getLastLogoutTime());
		return userBean;
	}
	
	public boolean updateUser(StoreUserBean userBean){
		StoreUser user = userManage.findStoreUserInfo(userBean.getUserId());
		if(user!=null){
			user.setLastLogoutTime(userBean.getLastLogoutTime());
			return userManage.updateUser(user);
		}
		else{
			return false;
		}
	}

}
