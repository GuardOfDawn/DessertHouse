package service;

import models.Bill;
import models.Member;
import models.Product;
import models.Strategy;

public interface SaleManageService {
	
	/**
	 * ���ݻ�Ա���ţ��õ���Ա��Ϣ
	 * @param memberId
	 * @return
	 */
	public Member getMemberInfo(String memberId);
	
	/**
	 * �����Ʒ��Ϣ
	 * @param productId
	 * @return
	 */
	public Product getProductInfo(String productId);

	/**
	 * ����Żݲ���
	 * @param memberId
	 * @param cost
	 * @return
	 */
	public Strategy getSaleStrategy(String memberId,double cost);
	
	/**
	 * ��֤��Ա֧������
	 * @param memberId
	 * @param payPassword
	 * @return
	 */
	public boolean checkPayPassword(String memberId,String payPassword);
	
	/**
	 * �����˵���Ϣ
	 * @param bill
	 */
	public void saveBill(Bill bill);
	
}
