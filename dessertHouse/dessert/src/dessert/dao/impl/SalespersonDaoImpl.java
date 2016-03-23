package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.SalespersonDao;
import dessert.models.Salesperson;
import dessert.models.Store;

@Repository
public class SalespersonDaoImpl implements SalespersonDao{

	@Autowired
	private BaseDao baseDao;
	private Session session;
	
	public boolean saveSalesperson(Salesperson salesperson) {
		try {
			baseDao.save(salesperson);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeSalesperson(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("delete from Salesperson sp where ");
			for(index=1;index<=columns.length;index++){
				ql.append("sp.").append(columns[index-1]).append("=").append("?")
						.append(" and ");
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setParameter(i, values[i]);
			}
			query.executeUpdate();
			session.close();
			return true;
		}
		else{
			return false;
		}
	}

	public Salesperson findSalesperson(String salespersonId){
		return (Salesperson) baseDao.load(Salesperson.class, salespersonId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Salesperson> findSalesperson(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select sp from Salesperson sp where ");
			for(index=1;index<=columns.length;index++){
				if(!columns[index-1].equals("storeId")){
					ql.append("sp.").append(columns[index-1]).append("=").append("?")
						.append(" and ");
				}
				else{
					ql.append("sp.store.").append(columns[index-1]).append("=").append("?")
					.append(" and ");
				}
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setParameter(i, values[i]);
			}
			List list = query.list();
			session.close();
			return (ArrayList<Salesperson>) list;
		}
		else{
			return findAllSalesperson();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Store findSalesperosnStore(String salespersonId){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select sp.store from Salesperson sp where sp.salespersonId=?");
		query.setString(0, salespersonId);
		List list = query.list();
		session.close();
		if(list!=null&&list.size()==1){
			return (Store)list.get(0);
		}
		else{
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Salesperson> findAllSalesperson() {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select sp from Salesperson sp");
		List list = query.list();
		session.close();
		return (ArrayList<Salesperson>) list;
	}

	public boolean updateSalesperson(Salesperson salesperson) {
		try{
			baseDao.update(salesperson);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
