package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.SalespersonDao;
import models.Salesperson;

@Stateless
public class SalespersonDaoImpl implements SalespersonDao{
	
	@PersistenceContext
	protected EntityManager em;
	
	public boolean saveSalesperson(Salesperson salesperson) {
		try {
			em.persist(salesperson);
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
				ql.append("sp.").append(columns[index-1]).append("=").append("?").append(index)
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
	public ArrayList<Salesperson> findSalesperson(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select sp from Salesperson sp where ");
			for(index=1;index<=columns.length;index++){
				if(!columns[index-1].equals("storeId")){
					ql.append("sp.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
				}
				else{
					ql.append("sp.store.").append(columns[index-1]).append("=").append("?").append(index)
					.append(" and ");
				}
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			List list = query.getResultList();
			return (ArrayList<Salesperson>) list;
		}
		else{
			return findAllSalesperson();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Salesperson> findAllSalesperson() {
		Query query = em.createQuery("select sp from Salesperson sp");
		List list = query.getResultList();
		return (ArrayList<Salesperson>) list;
	}

	public boolean updateSalesperson(Salesperson salesperson) {
		try{
			em.merge(salesperson);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
