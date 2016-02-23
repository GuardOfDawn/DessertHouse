package memberOperation;

public interface MemberRegisterService {

	/**
	 * ����Ա���Ƿ����
	 * @param memberName
	 * @return
	 */
	public boolean checkRegisterName(String memberName);
	
	/**
	 * �û������ֻ������ȡע����֤�룬ϵͳ������֤��󣬷��͵����ֻ�������
	 * @param phoneNumber
	 */
	public void verifyByPhone(String phoneNumber);
	
	/**
	 * �û�������֤�룬ϵͳ������֤���
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerifyCode(String verifyCode);
	
	/**
	 * ��֤��ע�ᣬϵͳ���ɻ�Ա���뷵��
	 * @param memberName
	 * @param password
	 * @return
	 */
	public String register(String memberName,String password);
	
}
