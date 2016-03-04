package remoteService;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Bill;
import models.Member;

@Remote
public interface MemberManageService {
	
	/**
	 * 查找用户名是否存在
	 * @param memberName
	 * @return
	 */
	public boolean findMemberName(String memberName);
	
	/**
	 * 查找手机号码是否已经被注册使用
	 * @param tel
	 * @return
	 */
	public boolean findMemberTel(String tel);
	
	/**
	 * 验证会员卡与手机是否匹配
	 * @param memberId
	 * @param tel
	 * @return
	 */
	public boolean matchMemberAndTel(String memberId,String tel);
	
	/**
	 * 向手机号发送验证码
	 * @param phoneNumber
	 */
	public String verifyByPhone(String phoneNumber);
	
	/**
	 * 验证后注册，系统生成会员号码返回
	 * @param memberName
	 * @param memberTel
	 * @param password
	 * @return
	 */
	public Member register(String memberName,String memberTel,String password);
	
	/**
	 * 用户登录，系统返回登录结果
	 * @param member 可以为会员名、电话号码或者会员卡号
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password);
	
	/**
	 * 更新用户登录密码
	 * @param memberId
	 * @param newPassword
	 */
	public void newPassword(String memberId,String newPassword);
	
	/**
	 * 用户绑定银行卡时，输入银行卡号，系统检查该卡是否符合要求
	 * @param bankCard
	 * @return
	 */
	public boolean checkBankCard(String bankCard);
	
	/**
	 * 用户设置预订、支付密码
	 * @param memberId
	 * @param payPassword
	 */
	public void setPayPassword(String memberId,String payPassword);
	
	/**
	 * 用户从绑定的银行卡向会员卡上充值，返回充值结果
	 * @param memberId
	 * @param bankCard
	 * @param payPassword
	 * @param money
	 */
	public boolean rechargeToMemberCard(String memberId,String bankCard,String payPassword,double money);
	
	/**
	 * 用户查看自己的会员信息
	 * @param memberId
	 * @return
	 */
	public Member checkMemberInfo(String memberId);
	
	/**
	 * 用户查看自己的消费记录
	 * @param memberId
	 * @return
	 */
	public ArrayList<Bill> checkPayment(String memberId);
	
	/**
	 * 用户修改自己的会员信息
	 * @param memberInfo
	 * @return
	 */
	public boolean modifyRegisterInfo(Member memberInfo);
	
}
