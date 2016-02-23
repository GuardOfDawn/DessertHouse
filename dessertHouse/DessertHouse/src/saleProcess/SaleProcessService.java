package saleProcess;

import java.util.ArrayList;

import models.Bill;
import models.Member;
import models.Order;
import models.Product;
import models.Strategy;

public interface SaleProcessService {

	/**
	 * ��û�Ա��Ԥ����
	 * @param memberId
	 * @return
	 */
	public ArrayList<Order> searchMemberOrder(String memberId);
	
	/**
	 * ��ѡ���Ԥ�����γ��˵�������Ա֧��
	 * @param orderIdList
	 * @return
	 */
	public Bill formBill(String[] orderIdList);

	/**
	 * ����Żݲ���
	 * @return
	 */
	public Strategy getSaleStrategy();
	
	/**
	 * ��Աͨ����Ա��֧���˵�
	 * @return
	 */
	public boolean payBill(String memberId,String payPassword);
	
	/**
	 * �ֽ�֧���˵�������������
	 * @param money
	 * @return
	 */
	public double payBill(double money);
	
	/**
	 * �ڵ��̹���ʱ�������Ա����Ϣ
	 * @param memberId
	 * @return
	 */
	public Member inputMemberCard(String memberId);
	
	/**
	 * �ڵ��̹���ʱ������Աɨ�����������ƷId��ϵͳ������Ʒ��Ϣ
	 * @param productId
	 * @param count
	 * @return
	 */
	public Product addProductToBill(String productId,int count);
	
	/**
	 * ������Ʒ���룬�����ܼ�
	 * @return
	 */
	public double finishProductInput();
	
	/**
	 * �˵�֧����󣬱����˵�
	 */
	public void saveBill();
	
	/**
	 * �û���;ȡ���˵�֧��
	 */
	public void cancelBill();
	
}
