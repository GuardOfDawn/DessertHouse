package dessert.remoteService.saleManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.BillDao;
import dessert.models.Bill;
import dessert.models.BillDetail;

@Service
public class SaleManage implements SaleManageService{

	@Autowired
	private BillDao billDao;
	
	public boolean saveBill(Bill bill) {
		return billDao.saveBill(bill);
	}

	public void saveBillDetail(BillDetail billDetail) {
		billDao.saveBillDetail(billDetail);
	}
	
}
