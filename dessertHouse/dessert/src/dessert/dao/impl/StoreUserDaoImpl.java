package dessert.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.StoreUserDao;
import dessert.models.StoreUser;

@Repository
public class StoreUserDaoImpl implements StoreUserDao{

	@Autowired
	private BaseDao baseDao;

	public StoreUser findUser(String userId) {
		try{
			return (StoreUser) baseDao.load(StoreUser.class, userId);
		}
		catch(Exception e){
			return null;
		}
	}

	public boolean updateUser(StoreUser user) {
		try{
			baseDao.update(user);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void save(StoreUser user) {
		baseDao.save(user);
	}
	
	public void deleteUser(String userId){
		baseDao.delete(StoreUser.class, userId);
	}

}
