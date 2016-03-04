package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="order")
public class Order implements Serializable{
	private String orderId;
	private Date orderTime;
	private Member orderMember;
	private Store orderStore;
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
	
	@Id
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="orderMember")
	public Member getOrderMember() {
		return orderMember;
	}
	public void setOrderMember(Member orderMember) {
		this.orderMember = orderMember;
	}

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="orderStoreId")
	public Store getOrderStore() {
		return orderStore;
	}
	public void setOrderStore(Store orderStore) {
		this.orderStore = orderStore;
	}

	@Column
	public double getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	@Column
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="productPrice DESC")
	public ArrayList<OrderDetail> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<OrderDetail> itemList) {
		this.itemList = itemList;
	}
	
}
