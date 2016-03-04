package saleManage;

import javax.ejb.EJB;

import models.Bill;
import models.Member;
import models.Product;
import models.Strategy;
import remoteService.SaleManageService;

@EJB
public class SaleManageController implements SaleManageService{

	private static SaleManageController saleManageController = new SaleManageController();
	private SaleManage saleManage;
	
	private SaleManageController(){
		saleManage = SaleManage.getInstance();
	}
	
	public static SaleManageController getInstance(){
		return saleManageController;
	}
	
	
	public Member getMemberInfo(String memberId) {
		return saleManage.getMemberInfo(memberId);
	}

	public Product getProductInfo(String productId) {
		return saleManage.getProductInfo(productId);
	}

	public Strategy getSaleStrategy(String memberId, double cost) {
		return saleManage.getSaleStrategy(memberId, cost);
	}

	public boolean checkPayPassword(String memberId, String payPassword) {
		return saleManage.checkPayPassword(memberId, payPassword);
	}

	public void saveBill(Bill bill) {
		saleManage.saveBill(bill);		
	}

}
