package dessert.dao;

import java.sql.Date;
import java.util.ArrayList;

import dessert.models.Bill;
import dessert.models.BillDetail;

public interface BillDao {

	public ArrayList<Bill> findBillForMember(String memberId);
	
	public ArrayList<Bill> findBillByDate(String storeId,Date date);

	public ArrayList<Bill> findBillByStore(String storeId,Date startDate,Date endDate);
	
	public ArrayList<BillDetail> findBillDetailByStore(String storeId,Date startDate,Date endDate);

	public ArrayList<BillDetail> findBillDetailAll(Date startDate,Date endDate);
	
	public boolean saveBill(Bill bill);

	public void saveBillDetail(BillDetail billDetail);
	
}
