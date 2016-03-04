package orderProduct;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;

import models.Member;
import models.Order;
import models.OrderDetail;
import remoteService.MemberManageService;
import remoteService.OrderManageService;
import utility.IDProducer;

public class OrderProduct {
	
	@EJB OrderManageService orderManage;
	@EJB MemberManageService memberManage;

	public OrderProduct(){
		
	}
	
	private Order order;
	
	public void startOrder(String memberId){
		order = new Order();
		Member m = memberManage.checkMemberInfo(memberId);
		order.setOrderMember(m);
	}
	
	public void addOrderItem(OrderDetail item){
		order.addItem(item);
	}
	
	public void deleteOrderItem(String productId){
		ArrayList<OrderDetail> itemList = order.getItemList();
		int index = 0;
		for(OrderDetail item:itemList){
			if(item.getProduct().getProductId().equals(productId)){
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
		String Id = IDProducer.getInstance().produceOrderId();
		order.setOrderId(Id);
		int orderState = 0;
		order.setOrderState(orderState);
		order.setOrderTime(new Date(System.currentTimeMillis()));
		orderManage.saveOrder(order);
		return res;
	}
	
	public void cancelOrder(){
		order = null;
	}
	
}
