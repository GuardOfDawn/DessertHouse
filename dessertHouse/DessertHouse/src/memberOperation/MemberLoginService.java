package memberOperation;

public interface MemberLoginService {

	/**
	 * 用户登录，系统返回登录结果
	 * @param member 可以为会员名、电话号码或者会员卡号
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password);
	
	/**
	 * 用户忘记登录密码，需要找回，系统发送验证码
	 * @param memberId
	 * @param memberTel
	 * @return
	 */
	public void findPassword(String memberId,String memberTel);
	
	/**
	 * 用户输入验证码，系统返回检查结果
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerify(String verifyCode);
	
	/**
	 * 更新用户登录密码，即密码找回成功
	 * @param memberId
	 * @param newPassword
	 */
	public void newPassword(String memberId,String newPassword);
	
}
