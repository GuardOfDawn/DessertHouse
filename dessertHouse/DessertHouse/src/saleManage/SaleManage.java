package saleManage;

import javax.ejb.EJB;

import dao.BillDao;
import memberManage.MemberInfo;
import models.Bill;
import models.Member;
import models.Product;
import models.Strategy;
import productManage.ProductInfo;
import purchaseStrategy.StrategyInfo;

public class SaleManage {

	private static SaleManage saleManage = new SaleManage();
	private MemberForSaleService memberService;
	private ProductForSaleService productService;
	private StrategyForSaleService strategyService;
	
	private SaleManage(){
		memberService = MemberInfo.getInstance();
		productService = ProductInfo.getInstance();
		strategyService = StrategyInfo.getInstance();
	}
	
	public static SaleManage getInstance(){
		return saleManage;
	}
	
	@EJB BillDao billDao;
	
	public Member getMemberInfo(String memberId){
		return memberService.getMemberInfo(memberId);
	}
	
	public Product getProductInfo(String productId){
		return productService.getProductInfo(productId);
	}
	
	public Strategy getSaleStrategy(String memberId, double cost){
		return strategyService.getStrategy(memberId, cost);
	}
	
	public boolean checkPayPassword(String memberId, String payPassword){
		return memberService.checkPayPassword(memberId, payPassword);
	}
	
	public void saveBill(Bill bill){
		billDao.saveBill(bill);
	}
	
}
