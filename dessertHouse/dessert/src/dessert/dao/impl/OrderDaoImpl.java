package dessert.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.OrderDao;
import dessert.models.Order;
import dessert.models.OrderDetail;
import dessert.utility.FormulationNumber;

@Repository
public class OrderDaoImpl implements OrderDao{

	@Autowired
	private BaseDao baseDao;
	private Session session;
	

	@SuppressWarnings("rawtypes")
	public Order findOrder(String orderId){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select o from Order o where o.orderId=?");
		query.setString(0, orderId);
		List list = query.list();
		session.close();
		if(list!=null&&list.size()==1){
			return (Order) list.get(0);
		}
		else{
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Order> findOrder(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select o from Order o where ");
			for(index=0;index<columns.length;index++){
				if(columns[index].equals("storeId")){
					ql.append("o.orderStore.").append(columns[index]).append("=").append("?")
					.append(" and ");
				}
				else if(columns[index].equals("memberId")){
					ql.append("o.orderMember.").append(columns[index]).append("=").append("?")
					.append(" and ");
				}
				else{
					ql.append("o.").append(columns[index]).append("=").append("?")
					.append(" and ");
				}
			}
			ql.delete(ql.length()-5, ql.length());
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			List list = query.list();
			session.close();
			return (ArrayList<Order>) list;
		}
		else{
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Order> findOrderForStore(String storeId, Date startDate, Date endDate){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select o from Order o where o.orderStore.storeId=?"
						+ " and o.orderState=? and o.orderTime>=? and o.orderTime<?");
		query.setString(0, storeId);
		query.setInteger(1, FormulationNumber.orderPaid);
		query.setDate(2, startDate);
		query.setDate(3, endDate);
		List list = query.list();
		return (ArrayList<Order>) list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<OrderDetail> findOrderDetail(String orderId){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select o.itemList from Order o where o.orderId=?");
		query.setString(0, orderId);
		List list = query.list();
		session.close();
		return (ArrayList<OrderDetail>) list;
	}

	public boolean saveOrder(Order order) {
		try {
			baseDao.save(order);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveOrderDetail(OrderDetail detail){
		try {
			baseDao.save(detail);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void updateOrder(Order order) {
		try{
			baseDao.update(order);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
