package service;

import models.Bill;
import models.Member;
import models.Product;
import models.Strategy;

public interface SaleManageService {
	
	/**
	 * 根据会员卡号，得到会员信息
	 * @param memberId
	 * @return
	 */
	public Member getMemberInfo(String memberId);
	
	/**
	 * 获得商品信息
	 * @param productId
	 * @return
	 */
	public Product getProductInfo(String productId);

	/**
	 * 获得优惠策略
	 * @param memberId
	 * @param cost
	 * @return
	 */
	public Strategy getSaleStrategy(String memberId,double cost);
	
	/**
	 * 验证会员支付密码
	 * @param memberId
	 * @param payPassword
	 * @return
	 */
	public boolean checkPayPassword(String memberId,String payPassword);
	
	/**
	 * 保存账单信息
	 * @param bill
	 */
	public void saveBill(Bill bill);
	
}
