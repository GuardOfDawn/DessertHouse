package dessert.models;

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
	private String productType;
	private String imagePath;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
