package models;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private String orderId;
	private Date orderTime;
	private String orderMember;//id
	private String orderStoreId;
	private double orderCost;
	private int orderState;//0-unpaid;1-paid;2-cancel
	private ArrayList<OrderDetail> itemList;
	
	public void addItem(OrderDetail item){
		orderCost += item.getProductPrice()*item.getProductCount();
		itemList.add(item);
	}
	
	public void deleteItem(int index){
//		OrderLineItem item = itemList.get(index);
//		orderCost -= item.getProductPrice()*item.getProductCount();
		itemList.remove(index);
		orderCost = 0;
		for(OrderDetail item:itemList){
			orderCost += item.getProductPrice()*item.getProductCount();
		}
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderMember() {
		return orderMember;
	}
	public void setOrderMember(String orderMember) {
		this.orderMember = orderMember;
	}
	public String getOrderStoreId() {
		return orderStoreId;
	}
	public void setOrderStoreId(String orderStoreId) {
		this.orderStoreId = orderStoreId;
	}
	public double getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public ArrayList<OrderDetail> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<OrderDetail> itemList) {
		this.itemList = itemList;
	}
	
}
