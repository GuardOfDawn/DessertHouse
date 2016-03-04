package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.OrderDao;
import models.Order;

@Stateless
public class OrderDaoImpl implements OrderDao{

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Order> findOrder(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select o from Order o where ");
			for(index=1;index<=columns.length;index++){
				if(columns[index-1].equals("storeId")){
					ql.append("o.orderStore.").append(columns[index-1]).append("=").append("?").append(index)
					.append(" and ");
				}
				else if(columns[index-1].equals("memberId")){
					ql.append("o.orderMember.").append(columns[index-1]).append("=").append("?").append(index)
					.append(" and ");
				}
				else{
					ql.append("o.").append(columns[index-1]).append("=").append("?").append(index)
					.append(" and ");
				}
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			List list = query.getResultList();
			return (ArrayList<Order>) list;
		}
		else{
			return null;
		}
	}

	public boolean saveOrder(Order order) {
		try {
			em.persist(order);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void updateOrder(Order order) {
		try{
			em.merge(order);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
