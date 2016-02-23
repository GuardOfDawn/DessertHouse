package service;

import java.util.ArrayList;

import models.Order;

public interface OrderManageService {

	/**
	 * 生成新的订单编号
	 * @return
	 */
	public String produceNewOrderId();
	
	/**
	 * 向系统保存预订单
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order);
	
	/**
	 * 查找某个会员的预订单
	 * @param memberId
	 * @param orderState
	 * @return
	 */
	public ArrayList<Order> findOrderByMember(String memberId,int orderState);
	
	/**
	 * 一个预订单支付后，修改其状态
	 * @param orderId
	 */
	public void orderPayed(String orderId);
	
}
