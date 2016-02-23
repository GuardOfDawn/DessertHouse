package saleManage;

import javax.ejb.EJB;

import dao.BillDao;
import memberManage.MemberInfo;
import models.Bill;
import models.BillDetail;
import models.Member;
import models.Product;
import models.Strategy;
import productManage.ProductInfo;
import purchaseStrategy.StrategyInfo;
import utility.DayTransformer;

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
		String[] billColumns = {"billId","billTime","billMember","billType","billCost","costAfterDiscount"};
		String[] billValues = new String[6];
		billValues[0] = bill.getBillId();
		billValues[1] = DayTransformer.transform(bill.getBillTime());
		billValues[2] = bill.getBillMember();
		billValues[3] = String.valueOf(bill.getBillType());
		billValues[4] = String.valueOf(bill.getBillCost());
		billValues[5] = String.valueOf(bill.getCostAfterDiscount());
		billDao.saveBill(billColumns, billValues);
		String[] itemColumns = {"billId","productId","productPrice","productCount"};
		String[] itemValues = new String[4];
		itemValues[0] = bill.getBillId();
		for(BillDetail b:bill.getItemList()){
			itemValues[1] = b.getProductId();
			itemValues[2] = String.valueOf(b.getProductPrice());
			itemValues[3] = String.valueOf(b.getProductCount());
			billDao.saveBillDetail(itemColumns, itemValues);
		}
	}
	
}
