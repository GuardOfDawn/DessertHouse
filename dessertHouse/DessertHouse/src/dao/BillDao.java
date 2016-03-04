package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Bill;

@Local
public interface BillDao {

	public ArrayList<Bill> findBillForMember(String memberId);
	
	public void saveBill(Bill bill);
	
}
