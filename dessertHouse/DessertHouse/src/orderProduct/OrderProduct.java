package orderProduct;

import java.sql.Date;
import java.util.ArrayList;

import models.Order;
import models.OrderDetail;

public class OrderProduct {

	public OrderProduct(){
		
	}
	
	private Order order;
	
	public void startOrder(String memberId){
		order = new Order();
		order.setOrderMember(memberId);
	}
	
	public void addOrderItem(OrderDetail item){
		order.addItem(item);
	}
	
	public void deleteOrderItem(String productId){
		ArrayList<OrderDetail> itemList = order.getItemList();
		int index = 0;
		for(OrderDetail item:itemList){
			if(item.getProductId().equals(productId)){
				break;
			}
			index++;
		}
		if(index<itemList.size()){
			order.deleteItem(index);
		}
	}
	
	public boolean saveOrder(){
		boolean res = false;
		//TODO get orderId
		
		int orderState = 0;
		order.setOrderState(orderState);
		order.setOrderTime(new Date(System.currentTimeMillis()));
		//TODO save order
		return res;
	}
	
	public void cancelOrder(){
		order = null;
	}
	
}
