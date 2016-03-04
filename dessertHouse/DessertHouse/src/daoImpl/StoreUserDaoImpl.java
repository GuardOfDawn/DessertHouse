package daoImpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.StoreUserDao;
import models.StoreUser;

@Stateless
public class StoreUserDaoImpl implements StoreUserDao{
	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("rawtypes")
	public boolean checkLogin(String userId, String password) {
		Query query = em.createQuery("select su from StoreUser su "
				+ "where su.userId=?1 and su.passwd.password=?2");
		query.setParameter(1, userId);
		query.setParameter(2, password);
		List list = query.getResultList();
		if(list!=null&&list.size()==1){
			return true;
		}
		else{
			return false;
		}
	}

	public StoreUser findUser(String userId) {
		return em.find(StoreUser.class, userId);
	}

	public boolean updateUser(StoreUser user) {
		try{
			em.merge(user);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void save(StoreUser user) {
		try {
			em.persist(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
