package saleProcess;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import models.Bill;
import models.Member;
import models.Order;
import models.Product;
import models.Strategy;
import service.SaleProcessService;

@Service
public class SaleController implements SaleProcessService{

	private DealOrderSale dealOrderSale;
	private DealStoreSale dealStoreSale;
	private int billFormat;//1表示由预订单形成，2表示现场购买
	
	public SaleController(){
		dealOrderSale = new DealOrderSale();
		dealStoreSale = new DealStoreSale();
	}
	
	public ArrayList<Order> searchMemberOrder(String memberId) {
		return dealOrderSale.searchMemberOrder(memberId);
	}

	public Bill formBill(String[] orderIdList) {
		billFormat = 1;
		return dealOrderSale.formBill(orderIdList);
	}

	public Strategy getSaleStrategy() {
		if(billFormat==1){
			return dealOrderSale.getSaleStrategy();
		}
		else{
			return dealStoreSale.getSaleStrategy();
		}
	}

	public boolean payBill(String memberId, String payPassword) {
		if(billFormat==1){
			return dealOrderSale.payBill(memberId, payPassword);
		}
		else{
			return dealStoreSale.payBill(memberId, payPassword);
		}
	}

	public double payBill(double money) {
		if(billFormat==1){
			return dealOrderSale.payBill(money);
		}
		else{
			return dealStoreSale.payBill(money);
		}
	}
	
	public Member inputMemberCard(String memberId){
		billFormat = 2;
		return dealStoreSale.inputMember(memberId);
	}

	public Product addProductToBill(String productId,int count) {
		return dealStoreSale.addProduct(productId,count);
	}

	public double finishProductInput() {
		return dealStoreSale.finishProductInput();
	}
	
	public void saveBill(){
		if(billFormat==1){
			dealOrderSale.saveBill();
		}
		else{
			dealStoreSale.saveBill();
		}
	}
	
	public void cancelBill(){
		if(billFormat==1){
			dealOrderSale.cancelBill();;
		}
		else{
			dealStoreSale.cancelBill();
		}
	}

}
