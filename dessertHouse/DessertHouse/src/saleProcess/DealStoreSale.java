package saleProcess;

import java.sql.Date;

import models.Bill;
import models.BillDetail;
import models.Member;
import models.Product;
import models.Strategy;
import remoteService.SaleManageService;
import utility.IDProducer;

public class DealStoreSale {

	public DealStoreSale(){
		
	}
	
	private Bill bill;
	
	private SaleManageService saleService;
	
	public Member inputMember(String memberId){
		bill = new Bill();
		bill.setBillId(IDProducer.getInstance().produceBillId());
		Member m = saleService.getMemberInfo(memberId);
		if(m!=null){
			bill.setBillMember(m);
			return m;
		}
		else{
			return null;
		}
	}
	
	public Product addProduct(String productId,int count){
		Product p = saleService.getProductInfo(productId);
		BillDetail item = new BillDetail();
		item.setProduct(p);
		item.setProductPrice(p.getSellPrice());
		item.setProductCount(count);
		bill.getItemList().add(item);
		return p;
	}
	
	public double finishProductInput(){
		double cost = 0;
		for(BillDetail item:bill.getItemList()){
			cost += item.getProductPrice()*item.getProductCount();
		}
		bill.setBillCost(cost);
		return cost;
	}
	
	public Strategy getSaleStrategy(){
		//TODO 获得优惠策略
		Strategy strategy = saleService.getSaleStrategy(bill.getBillMember().getMemberId(), bill.getBillCost());
		//bill.setCostAfterDiscount();
		return null;
	}
	
	public boolean payBill(String memberId, String payPassword){
		boolean payResult = saleService.checkPayPassword(memberId, payPassword);
		if(payResult){
			int cardPay = 0;
			bill.setBillType(cardPay);
			bill.setBillTime(new Date(System.currentTimeMillis()));
		}
		return payResult;
	}
	
	public double payBill(double money){
		int cashPay = 1;
		bill.setBillType(cashPay);
		bill.setBillTime(new Date(System.currentTimeMillis()));
		return money - bill.getCostAfterDiscount();
	}
	
	public void saveBill(){
		for(BillDetail item:bill.getItemList()){
			item.setBill(bill);
		}
		saleService.saveBill(bill);
		bill = null;
	}
	
	public void cancelBill(){
		bill = null;
	}
	
}
