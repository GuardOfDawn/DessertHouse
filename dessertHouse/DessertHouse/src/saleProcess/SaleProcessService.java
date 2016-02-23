package saleProcess;

import java.util.ArrayList;

import models.Bill;
import models.Member;
import models.Order;
import models.Product;
import models.Strategy;

public interface SaleProcessService {

	/**
	 * 获得会员的预订单
	 * @param memberId
	 * @return
	 */
	public ArrayList<Order> searchMemberOrder(String memberId);
	
	/**
	 * 将选择的预订单形成账单，供会员支付
	 * @param orderIdList
	 * @return
	 */
	public Bill formBill(String[] orderIdList);

	/**
	 * 获得优惠策略
	 * @return
	 */
	public Strategy getSaleStrategy();
	
	/**
	 * 会员通过会员卡支付账单
	 * @return
	 */
	public boolean payBill(String memberId,String payPassword);
	
	/**
	 * 现金支付账单，返回找零金额
	 * @param money
	 * @return
	 */
	public double payBill(double money);
	
	/**
	 * 在店铺购买时，输入会员卡信息
	 * @param memberId
	 * @return
	 */
	public Member inputMemberCard(String memberId);
	
	/**
	 * 在店铺购买时，服务员扫描或者输入商品Id，系统返回商品信息
	 * @param productId
	 * @param count
	 * @return
	 */
	public Product addProductToBill(String productId,int count);
	
	/**
	 * 结束商品输入，返回总价
	 * @return
	 */
	public double finishProductInput();
	
	/**
	 * 账单支付完后，保存账单
	 */
	public void saveBill();
	
	/**
	 * 用户中途取消账单支付
	 */
	public void cancelBill();
	
}
