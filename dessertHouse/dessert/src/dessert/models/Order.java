package dessert.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

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
@Table(name="orders")
public class Order implements Serializable{
	private String orderId;
	private Date orderTime;
	private Member orderMember;
	private Store orderStore;
	private double orderCost;
	private double favorRate;
	private int bonusUsed;
	private double costAfterDiscount;
	private int orderState;//0-unpaid;1-paid;2-cancel
	private Set<OrderDetail> itemList;
	
	
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
	public double getFavorRate() {
		return favorRate;
	}
	public void setFavorRate(double favorRate) {
		this.favorRate = favorRate;
	}

	@Column
	public int getBonusUsed() {
		return bonusUsed;
	}
	public void setBonusUsed(int bonusUsed) {
		this.bonusUsed = bonusUsed;
	}

	@Column
	public double getCostAfterDiscount() {
		return costAfterDiscount;
	}
	public void setCostAfterDiscount(double costAfterDiscount) {
		this.costAfterDiscount = costAfterDiscount;
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
	public Set<OrderDetail> getItemList() {
		return itemList;
	}
	public void setItemList(Set<OrderDetail> itemList) {
		this.itemList = itemList;
	}
	
}
