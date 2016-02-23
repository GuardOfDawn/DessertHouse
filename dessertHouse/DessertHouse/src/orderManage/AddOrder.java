package orderManage;

import java.util.ArrayList;

import javax.ejb.EJB;

import dao.OrderDao;
import models.Order;
import models.OrderDetail;
import utility.DayTransformer;

public class AddOrder {

	private static AddOrder addOrder = new AddOrder();
	
	private AddOrder(){
		
	}
	
	public static AddOrder getInstance(){
		return addOrder;
	}
	
	@EJB OrderDao orderDao;
	
	public boolean addOrder(Order order){
		String[] orderColumns = {"orderId","orderTime","orderMember","orderCost","orderState"};
		String[] orderValues = new String[5];
		orderValues[0] = order.getOrderId();
		orderValues[1] = DayTransformer.transform(order.getOrderTime());
		orderValues[2] = order.getOrderMember();
		orderValues[3] = String.valueOf(order.getOrderCost());
		orderValues[4] = String.valueOf(order.getOrderState());
		orderDao.addOrder(orderColumns, orderValues);
		
		String[] detailColumns = {"orderId","productId","productPrice","productCount"};
		String[] detailValues = new String[4];
		ArrayList<OrderDetail> itemList = order.getItemList();
		for(OrderDetail detail:itemList){
			detailValues[0] = detail.getOrderId();
			detailValues[1] = detail.getProductId();
			detailValues[2] = String.valueOf(detail.getProductPrice());
			detailValues[3] = String.valueOf(detail.getProductCount());
			orderDao.addOrderDetail(detailColumns, detailValues);
		}
		return true;
	}
}
