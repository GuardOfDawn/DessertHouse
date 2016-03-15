package dessert.dao;

import dessert.models.StoreUser;

public interface StoreUserDao {
	
	public StoreUser findUser(String userId);
	
	public boolean updateUser(StoreUser user);
	
	public void save(StoreUser user);
	
	public void deleteUser(String userId);
	
}
