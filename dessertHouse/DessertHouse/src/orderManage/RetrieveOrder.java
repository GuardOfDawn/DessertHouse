package orderManage;

import java.util.ArrayList;

import javax.ejb.EJB;

import dao.OrderDao;
import models.Order;

public class RetrieveOrder {

	private static RetrieveOrder retrieveOrder = new RetrieveOrder();
	
	private RetrieveOrder(){
		
	}
	
	public static RetrieveOrder getInstance(){
		return retrieveOrder;
	}
	
	@EJB OrderDao orderDao;
	
	public ArrayList<Order> findOrder(String memberId,int orderState){
		String[] columns = {"memberId","orderState"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = String.valueOf(orderState);
		ArrayList<Order> orderList = orderDao.findOrder(columns, values);
		columns = new String[]{"orderId"};
		for(Order o:orderList){
			o.setItemList(orderDao.findOrderDetail(columns, new String[]{o.getOrderId()}));
		}
		return orderList;
	}
}
