package models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="orderdetail")
public class OrderDetail implements Serializable{

	private Order order;
	private Product product;
	private double productPrice;
	private int productCount;
	
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="orderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product productId) {
		this.product = productId;
	}

	@Column
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Column
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public double getSubTotal(){
		return productPrice*productCount;
	}
}
