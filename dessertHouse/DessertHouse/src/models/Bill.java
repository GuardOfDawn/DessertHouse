package models;

import java.sql.Date;
import java.util.ArrayList;

public class Bill {

	private String billId;
	private Date billTime;
	private String billMember;
	private int billType;
	private double billCost;
	private double costAfterDiscount;
	private ArrayList<BillDetail> itemList;
	
	
	public Bill(){
		itemList = new ArrayList<BillDetail>();
	}
	
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public Date getBillTime() {
		return billTime;
	}
	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}
	public String getBillMember() {
		return billMember;
	}
	public void setBillMember(String billMember) {
		this.billMember = billMember;
	}
	public int getBillType() {
		return billType;
	}
	public void setBillType(int billType) {
		this.billType = billType;
	}
	public double getBillCost() {
		return billCost;
	}
	public void setBillCost(double billCost) {
		this.billCost = billCost;
	}
	public ArrayList<BillDetail> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<BillDetail> itemList) {
		this.itemList = itemList;
	}
	public double getCostAfterDiscount() {
		return costAfterDiscount;
	}
	public void setCostAfterDiscount(double costAfterDiscount) {
		this.costAfterDiscount = costAfterDiscount;
	}
	
}
