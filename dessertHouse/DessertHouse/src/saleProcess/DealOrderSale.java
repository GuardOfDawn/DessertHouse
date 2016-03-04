package saleProcess;

import java.sql.Date;
import java.util.ArrayList;

import models.Bill;
import models.BillDetail;
import models.Order;
import models.OrderDetail;
import models.Strategy;
import remoteService.OrderManageService;
import remoteService.SaleManageService;
import utility.IDProducer;

public class DealOrderSale {

	public DealOrderSale(){
		
	}
	
	private Bill bill;
	private ArrayList<Order> memberOrderList;
	private Order[] payOrderList;
	
	private OrderManageService orderService;
	private SaleManageService saleService;
	
	public ArrayList<Order> searchMemberOrder(String memberId){
		int unpaidOrder = 0;
		memberOrderList = orderService.findOrderByMember(memberId, unpaidOrder);
		return memberOrderList;
	}
	
	public Bill formBill(String[] orderIdList){
		payOrderList = new Order[orderIdList.length];
		int index = 0;
		for(String id:orderIdList){
			for(Order o:memberOrderList){
				if(id.equals(o.getOrderId())){
					payOrderList[index] = o;
					break;
				}
			}
		}
		bill = new Bill();
		bill.setBillId(IDProducer.getInstance().produceBillId());
		bill.setBillMember(payOrderList[0].getOrderMember());
		bill.setBillStore(payOrderList[0].getOrderStore());
		double cost = 0;
		ArrayList<BillDetail> itemList = new ArrayList<BillDetail>();
		for(Order o:payOrderList){
			cost += o.getOrderCost();
			for(OrderDetail orderItem:o.getItemList()){
				BillDetail billItem = new BillDetail();
				billItem.setProduct(orderItem.getProduct());
				billItem.setProductPrice(orderItem.getProductPrice());
				billItem.setProductCount(orderItem.getProductCount());
				itemList.add(billItem);
			}
		}
		bill.setBillCost(cost);
		bill.setItemList(itemList);
		return bill;
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
		return money-bill.getCostAfterDiscount();
	}
	
	public void saveBill(){
		for(BillDetail item:bill.getItemList()){
			item.setBill(bill);
		}
		saleService.saveBill(bill);
		for(Order o:payOrderList){
			orderService.orderPayed(o.getOrderId());
		}
		bill = null;
	}
	
	public void cancelBill(){
		bill = null;
	}
	
}
