package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Order;
import models.OrderDetail;

@Remote
public interface OrderDao {
	
	public ArrayList<Order> findOrder(String[] columns,String[] values);
	
	public ArrayList<OrderDetail> findOrderDetail(String[] columns,String[] values);

	public void addOrder(String[] columns,String[] values);
	
	public void addOrderDetail(String[] columns,String[] values);
	
	public void modifyOrder(String orderId,String column,String value);
	
}
