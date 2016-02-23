package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Bill;

@Remote
public interface BillDao {

	public ArrayList<Bill> findBillForMember(String memberId);
	
	public void saveBill(String[] columns,String[] values);
	
	public void saveBillDetail(String[] columns,String[] values);
	
}
