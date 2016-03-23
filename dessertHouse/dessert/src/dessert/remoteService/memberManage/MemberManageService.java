package dessert.remoteService.memberManage;

import java.sql.Date;
import java.util.ArrayList;

import dessert.models.Bill;
import dessert.models.Member;
import dessert.models.MemberPasswd;
import dessert.models.Recharge;

public interface MemberManageService {

	/**
	 * 查找用户名是否已经存在
	 * @param memberName
	 * @return
	 */
	public boolean findMemberName(String memberName);

	/**
	 * 当用户注册完成后，要修改会员名时，查找是否重名
	 * @param memberId
	 * @param memberName
	 * @return
	 */
	public boolean findMemberName(String memberId,String memberName);
	
	/**
	 * 查找手机号码是否已经被注册使用
	 * @param tel
	 * @return
	 */
	public boolean findMemberTel(String tel);

	/**
	 * 当用户注册完成后，要修改电话时，查找是否已经被使用
	 * @param memberId
	 * @param tel
	 * @return
	 */
	public boolean findMemberTel(String memberId,String tel);
	
	/**
	 * 向手机号发送验证码
	 * @param phoneNumber
	 * @return 返回验证码
	 */
	public String verifyByPhone(String phoneNumber);
	
	/**
	 * 注册
	 * @param newMember
	 * @return
	 */
	public boolean register(Member newMember);

	/**
	 * 设置用户登录密码
	 * @param passwd
	 * @return
	 */
	public boolean setLoginPassword(MemberPasswd passwd);
	
	/**
	 * 更新用户登录密码
	 * @param memberId
	 * @param newPassword
	 */
	public boolean newLoginPassword(String memberId,String newPassword);

	/**
	 * 设置支付密码
	 * @param memberId
	 * @param payPassword
	 * @return
	 */
	public boolean setPayPassword(String memberId,String payPassword);
	
	/**
	 * 用户登录，系统返回登录结果
	 * @param member 可以为会员名、电话号码或者会员卡号
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password);

	/**
	 * 查看银行卡是否存在且可用
	 * @param bankCard
	 * @return
	 */
	public boolean checkBankCard(String bankCard);
	
	public boolean matchMemberAndPayPassword(String memberId,String password);
	
	/**
	 * 从银行卡向会员卡充值
	 * @param memberId
	 * @param bankCard
	 * @param payPassword
	 * @param money
	 * @return
	 */
	public boolean rechargeToMemberCard(String memberId,String bankCard,String payPassword,double money);
	
	/**
	 * 根据Id查找会员
	 * @param memberId
	 * @return
	 */
	public Member checkMemberInfo(String memberId);
	
	public ArrayList<Member> checkAllMember();
	
	/**
	 * 查找会员充值记录
	 * @param memberId
	 * @return
	 */
	public ArrayList<Recharge> checkRecharge(String memberId);

	public ArrayList<Recharge> checkAllRecharge();
	
	public ArrayList<Bill> checkBillInfo(String memberId);

	public ArrayList<Bill> checkBill(String storeId,Date date);
	
	/**
	 * 修改会员信息
	 * @param memberInfo
	 * @return
	 */
	public boolean modifyRegisterInfo(Member memberInfo);
	
	/**
	 * 注销会员卡信息
	 * @param memberId
	 */
	public void withdrawCardInfo(String memberId);
	
}
