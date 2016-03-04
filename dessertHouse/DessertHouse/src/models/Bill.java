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
@Table(name="bill")
public class Bill implements Serializable{

	private String billId;
	private Date billTime;
	private Member billMember;
	private Store billStore;
	private int billType;
	private double billCost;
	private double costAfterDiscount;
	private double payment;
	private double change;
	private ArrayList<BillDetail> itemList;
	
	
	public Bill(){
		itemList = new ArrayList<BillDetail>();
	}
	
	@Id
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	
	@Column
	public Date getBillTime() {
		return billTime;
	}
	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="billMember")
	public Member getBillMember() {
		return billMember;
	}
	public void setBillMember(Member billMember) {
		this.billMember = billMember;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="billStoreId")
	public Store getBillStore() {
		return billStore;
	}
	public void setBillStore(Store billStore) {
		this.billStore = billStore;
	}

	@Column
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}

	@Column
	public double getBillCost() {
		return billCost;
	}
	public void setBillCost(double billCost) {
		this.billCost = billCost;
	}
	
	@OneToMany(mappedBy="bill", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="productPrice DESC")
	public ArrayList<BillDetail> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<BillDetail> itemList) {
		this.itemList = itemList;
	}

	@Column
	public double getCostAfterDiscount() {
		return costAfterDiscount;
	}
	public void setCostAfterDiscount(double costAfterDiscount) {
		this.costAfterDiscount = costAfterDiscount;
	}
	
	@Column
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}

	@Column
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	
}
