package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.StoreDao;
import dessert.models.Store;

@Repository
public class StoreDaoImpl implements StoreDao{
	
	@Autowired
	private BaseDao baseDao;
	private Session session;
	
	public boolean saveStore(Store store) {
		try {
			baseDao.save(store);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteStore(Store store){
		try {
			baseDao.delete(store);
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
				ql.append("s.").append(columns[index-1]).append("=").append("?")
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			query.executeUpdate();
			session.close();
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
				ql.append("s.").append(columns[index-1]).append("=").append("?")
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			List list = query.list();
			session.close();
			return (ArrayList<Store>) list;
		}
		else{
			return findAllStore();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Store> findAllStore() {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select s from Store s");
		List list = query.list();
//		session.close();
		return (ArrayList<Store>) list;
	}

	public boolean updateStore(Store store) {
		try{
			baseDao.update(store);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
