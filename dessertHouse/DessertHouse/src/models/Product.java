package models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="product")
public class Product implements Serializable{

	private String productId;
	private String productName;
	private double sellPrice;
	private double openingPrice;
	private int stockQuantity;
	private Set<OrderDetail> orderdetailList;
	private Set<BillDetail> billdetailList;
	

	@Id
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Column
	public double getOpeningPrice() {
		return openingPrice;
	}
	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	@Column
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="orderId ASC")
	public Set<OrderDetail> getOrderdetailList() {
		return orderdetailList;
	}
	public void setOrderdetailList(Set<OrderDetail> orderdetailList) {
		this.orderdetailList = orderdetailList;
	}
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="billId ASC")
	public Set<BillDetail> getBilldetailList() {
		return billdetailList;
	}
	public void setBilldetailList(Set<BillDetail> billdetailList) {
		this.billdetailList = billdetailList;
	}
	
}
