package orderManage;

import javax.ejb.EJB;

import dao.OrderDao;
import models.Order;

public class AddOrder {

	private static AddOrder addOrder = new AddOrder();
	
	private AddOrder(){
		
	}
	
	public static AddOrder getInstance(){
		return addOrder;
	}
	
	@EJB OrderDao orderDao;
	
	public boolean addOrder(Order order){
		return orderDao.saveOrder(order);
	}
}
