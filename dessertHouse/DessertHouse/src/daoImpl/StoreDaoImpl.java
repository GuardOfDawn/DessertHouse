package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.StoreDao;
import models.Store;

@Stateless
public class StoreDaoImpl implements StoreDao{
	
	@PersistenceContext
	protected EntityManager em;
	
	public boolean saveStore(Store store) {
		try {
			em.persist(store);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeStore(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("delete from Store s where ");
			for(index=1;index<=columns.length;index++){
				ql.append("s.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			query.executeUpdate();
			return true;
		}
		else{
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Store> findStore(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select s from Store s where ");
			for(index=1;index<=columns.length;index++){
				ql.append("s.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			List list = query.getResultList();
			return (ArrayList<Store>) list;
		}
		else{
			return findAllStore();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Store> findAllStore() {
		Query query = em.createQuery("select s from Store s");
		List list = query.getResultList();
		return (ArrayList<Store>) list;
	}

	public boolean updateStore(Store store) {
		try{
			em.merge(store);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
