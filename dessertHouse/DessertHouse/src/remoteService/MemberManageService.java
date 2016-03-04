package remoteService;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Bill;
import models.Member;

@Remote
public interface MemberManageService {
	
	/**
	 * �����û����Ƿ����
	 * @param memberName
	 * @return
	 */
	public boolean findMemberName(String memberName);
	
	/**
	 * �����ֻ������Ƿ��Ѿ���ע��ʹ��
	 * @param tel
	 * @return
	 */
	public boolean findMemberTel(String tel);
	
	/**
	 * ��֤��Ա�����ֻ��Ƿ�ƥ��
	 * @param memberId
	 * @param tel
	 * @return
	 */
	public boolean matchMemberAndTel(String memberId,String tel);
	
	/**
	 * ���ֻ��ŷ�����֤��
	 * @param phoneNumber
	 */
	public String verifyByPhone(String phoneNumber);
	
	/**
	 * ��֤��ע�ᣬϵͳ���ɻ�Ա���뷵��
	 * @param memberName
	 * @param memberTel
	 * @param password
	 * @return
	 */
	public Member register(String memberName,String memberTel,String password);
	
	/**
	 * �û���¼��ϵͳ���ص�¼���
	 * @param member ����Ϊ��Ա�����绰������߻�Ա����
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password);
	
	/**
	 * �����û���¼����
	 * @param memberId
	 * @param newPassword
	 */
	public void newPassword(String memberId,String newPassword);
	
	/**
	 * �û������п�ʱ���������п��ţ�ϵͳ���ÿ��Ƿ����Ҫ��
	 * @param bankCard
	 * @return
	 */
	public boolean checkBankCard(String bankCard);
	
	/**
	 * �û�����Ԥ����֧������
	 * @param memberId
	 * @param payPassword
	 */
	public void setPayPassword(String memberId,String payPassword);
	
	/**
	 * �û��Ӱ󶨵����п����Ա���ϳ�ֵ�����س�ֵ���
	 * @param memberId
	 * @param bankCard
	 * @param payPassword
	 * @param money
	 */
	public boolean rechargeToMemberCard(String memberId,String bankCard,String payPassword,double money);
	
	/**
	 * �û��鿴�Լ��Ļ�Ա��Ϣ
	 * @param memberId
	 * @return
	 */
	public Member checkMemberInfo(String memberId);
	
	/**
	 * �û��鿴�Լ������Ѽ�¼
	 * @param memberId
	 * @return
	 */
	public ArrayList<Bill> checkPayment(String memberId);
	
	/**
	 * �û��޸��Լ��Ļ�Ա��Ϣ
	 * @param memberInfo
	 * @return
	 */
	public boolean modifyRegisterInfo(Member memberInfo);
	
}
