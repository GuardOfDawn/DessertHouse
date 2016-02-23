package memberOperation;

public class MemberRegister {

	
	public MemberRegister(){
		
	}
	
	/**
	 * 检查待注册的会员名是否存在
	 * @param memberName
	 * @return
	 */
	public boolean checkRegisterName(String memberName){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 用户输入手机号码获取注册验证码，系统生成验证码后，发送到该手机号码上
	 * @param phoneNumber
	 */
	public void verifyByPhone(String phoneNumber){
		
	}
	
	/**
	 * 用户输入验证码，系统返回验证结果
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerifyCode(String verifyCode){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 验证后注册，系统生成会员号码返回
	 * @param memberName
	 * @param password
	 * @return
	 */
	public String register(String memberName,String password){
		String memberId = new String();
		
		return memberId;
	}
	
}
