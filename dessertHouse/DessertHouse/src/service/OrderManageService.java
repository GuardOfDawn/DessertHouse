package service;

import java.util.ArrayList;

import models.Order;

public interface OrderManageService {

	/**
	 * �����µĶ������
	 * @return
	 */
	public String produceNewOrderId();
	
	/**
	 * ��ϵͳ����Ԥ����
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order);
	
	/**
	 * ����ĳ����Ա��Ԥ����
	 * @param memberId
	 * @param orderState
	 * @return
	 */
	public ArrayList<Order> findOrderByMember(String memberId,int orderState);
	
	/**
	 * һ��Ԥ����֧�����޸���״̬
	 * @param orderId
	 */
	public void orderPayed(String orderId);
	
}
