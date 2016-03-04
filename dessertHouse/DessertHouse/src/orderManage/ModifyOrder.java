package orderManage;

import java.util.ArrayList;

import javax.ejb.EJB;

import dao.OrderDao;
import models.Order;

public class ModifyOrder {

	private static ModifyOrder modifyOrder = new ModifyOrder();
	
	private ModifyOrder(){
		
	}
	
	public static ModifyOrder getInstance(){
		return modifyOrder;
	}
	
	@EJB OrderDao orderDao;
	
	public void modifyOrderState(String orderId,int orderState){
		String[] columns = {"orderId"};
		String[] values = new String[1];
		values[0] = orderId;
		ArrayList<Order> list = orderDao.findOrder(columns, values);
		if(list!=null&&list.size()==1){
			Order order = list.get(0);
			order.setOrderState(orderState);
			orderDao.saveOrder(order);
		}
	}
}
