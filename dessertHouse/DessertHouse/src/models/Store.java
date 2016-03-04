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
@Table(name="store")
public class Store implements Serializable{

	private String storeId;
	private String storeName;
	private String storeTel;
	private String province;
	private String city;
	private String storeLoc;
	private Set<Order> orderList;
	private Set<Bill> billList;
	private Set<Salesperson> salespersonList;
	private Set<WeekSchedule> weekScheduleList;
	
	
	@Id
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Column
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	@Column
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	@Column
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Column
	public String getStoreLoc() {
		return storeLoc;
	}
	public void setStoreLoc(String storeLoc) {
		this.storeLoc = storeLoc;
	}
	
	@OneToMany(mappedBy="orderStore", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="orderTime DESC")
	public Set<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(Set<Order> orderList) {
		this.orderList = orderList;
	}
	
	@OneToMany(mappedBy="billStore", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="billTime DESC")
	public Set<Bill> getBillList() {
		return billList;
	}
	public void setBillList(Set<Bill> billList) {
		this.billList = billList;
	}

	@OneToMany(mappedBy="store", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="salespersonId ASC")
	public Set<Salesperson> getSalespersonList() {
		return salespersonList;
	}
	public void setSalespersonList(Set<Salesperson> salespersonList) {
		this.salespersonList = salespersonList;
	}

	@OneToMany(mappedBy="store", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="startTime DESC")
	public Set<WeekSchedule> getWeekScheduleList() {
		return weekScheduleList;
	}
	public void setWeekScheduleList(Set<WeekSchedule> weekScheduleList) {
		this.weekScheduleList = weekScheduleList;
	}
	
}
