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
@Table(name="bills")
public class Bill implements Serializable{

	private String billId;
	private Member billMember;
	private Date billTime;
	private Store billStore;
	private int billType;
	private double billCost;
	private double favorRate;
	private int bonusUsed;
	private double costAfterDiscount;
	private double payment;
	private double changeGiven;
	private int bonusGiven;
	private Set<BillDetail> itemList;
	
	
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
	
	@OneToMany(mappedBy="bill", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="productPrice DESC")
	public Set<BillDetail> getItemList() {
		return itemList;
	}
	public void setItemList(Set<BillDetail> itemList) {
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
	public double getChangeGiven() {
		return changeGiven;
	}
	public void setChangeGiven(double changeGiven) {
		this.changeGiven = changeGiven;
	}

	@Column
	public int getBonusGiven() {
		return bonusGiven;
	}
	public void setBonusGiven(int bonusGiven) {
		this.bonusGiven = bonusGiven;
	}
	
}
