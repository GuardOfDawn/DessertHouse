package orderManage;

import java.util.ArrayList;

import models.Order;
import service.OrderManageService;
import utility.IDProducer;

public class OrderManageController implements OrderManageService{

	private static OrderManageController orderManageController = new OrderManageController();
	private static AddOrder addOrder;
	private static ModifyOrder modifyOrder;
	private static RetrieveOrder retrieveOrder;
	private static IDProducer idProducer;
	
	private OrderManageController(){
		addOrder = AddOrder.getInstance();
		modifyOrder = ModifyOrder.getInstance();
		retrieveOrder = RetrieveOrder.getInstance();
		idProducer = IDProducer.getInstance();
	}
	
	public static OrderManageController getInstance(){
		return orderManageController;
	}
	
	public String produceNewOrderId() {
		return idProducer.produceOrderId();
	}

	public boolean saveOrder(Order order) {
		return addOrder.addOrder(order);
	}

	public ArrayList<Order> findOrderByMember(String memberId,int orderState) {
		return retrieveOrder.findOrder(memberId,orderState);
	}

	public void orderPayed(String orderId) {
		int orderState = 1;
		modifyOrder.modifyOrderState(orderId,orderState);
	}

}
