package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Order;

@Local
public interface OrderDao {
	
	public ArrayList<Order> findOrder(String[] columns,String[] values);
	
	public boolean saveOrder(Order order);
	
	public void updateOrder(Order order);
	
}
