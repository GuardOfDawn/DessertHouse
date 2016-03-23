package dessert.dao;

import java.sql.Date;
import java.util.ArrayList;

import dessert.models.Bill;
import dessert.models.BillDetail;

public interface BillDao {

	public ArrayList<Bill> findBillForMember(String memberId);
	
	public ArrayList<Bill> findBillByDate(String storeId,Date date);
	
	public boolean saveBill(Bill bill);

	public void saveBillDetail(BillDetail billDetail);
	
}
