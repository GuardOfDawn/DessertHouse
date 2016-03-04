package dao;

import javax.ejb.Local;

import models.StoreUser;

@Local
public interface StoreUserDao {

	public boolean checkLogin(String userId,String password);
	
	public StoreUser findUser(String userId);
	
	public boolean updateUser(StoreUser user);
	
	public void save(StoreUser user);
	
}
