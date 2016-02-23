package memberOperation;

public class MemberLogin {

	public MemberLogin(){
		
	}
	
	/**
	 * 用户登录，系统返回登录结果
	 * @param member 可以为会员名、电话号码或者会员卡号
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 用户绑定银行卡时，输入银行卡号，系统检查该卡是否符合要求
	 * @param bankCard
	 * @return
	 */
	public boolean checkBankCard(String bankCard){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 用户设置预订、支付密码
	 * @param memberId
	 * @param payPassword
	 */
	public void setPayPassword(String memberId,String payPassword){
		
	}
	
	/**
	 * 用户从绑定的银行卡向会员卡上充值，返回充值结果
	 * @param memberId
	 * @param bankCard
	 * @param payPassword
	 * @param money
	 */
	public boolean rechargeToMemberCard(String memberId,String bankCard,String payPassword,double money){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 用户忘记登录密码，需要找回，系统发送验证码
	 * @param memberName
	 * @param memberTel
	 * @return
	 */
	public void findPassword(String memberName,String memberTel){
		
	}
	
	/**
	 * 用户输入验证码，系统返回检查结果
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerify(String verifyCode){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 更新用户登录密码，即密码找回成功
	 * @param memberId
	 * @param newPassword
	 */
	public void newPassword(String memberId,String newPassword){
		
	}
	
}
