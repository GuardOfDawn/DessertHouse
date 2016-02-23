package orderManage;

import javax.ejb.EJB;

import dao.OrderDao;

public class ModifyOrder {

	private static ModifyOrder modifyOrder = new ModifyOrder();
	
	private ModifyOrder(){
		
	}
	
	public static ModifyOrder getInstance(){
		return modifyOrder;
	}
	
	@EJB OrderDao orderDao;
	
	public void modifyOrderState(String orderId,int orderState){
		String column = "orderState";
		String value = String.valueOf(orderState);
		orderDao.modifyOrder(orderId, column, value);
	}
}
