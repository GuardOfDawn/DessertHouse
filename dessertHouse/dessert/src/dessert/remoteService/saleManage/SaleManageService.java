package dessert.remoteService.saleManage;


import dessert.models.Bill;
import dessert.models.BillDetail;

public interface SaleManageService {
	
	public boolean saveBill(Bill bill);

	public void saveBillDetail(BillDetail billDetail);
	
}
