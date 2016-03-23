package dessert.remoteService.orderManage;

import java.util.ArrayList;

import dessert.models.Order;
import dessert.models.OrderDetail;

public interface OrderManageService {

	public boolean saveOrder(Order order);
	
	public boolean saveOrderDetail(OrderDetail detail);
	
	public ArrayList<Order> findOrderByMember(String memberId,int orderState);
	
	public ArrayList<Order> findTodaysOrderForMember(String memberId,String storeId ,int orderState);

	public ArrayList<OrderDetail> findOrderDetail(String orderId);
	
	public void orderPayed(String orderId);
	
}
