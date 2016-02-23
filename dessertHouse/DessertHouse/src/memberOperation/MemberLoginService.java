package memberOperation;

public interface MemberLoginService {

	/**
	 * �û���¼��ϵͳ���ص�¼���
	 * @param member ����Ϊ��Ա�����绰������߻�Ա����
	 * @param password
	 * @return
	 */
	public boolean login(String member,String password);
	
	/**
	 * �û����ǵ�¼���룬��Ҫ�һأ�ϵͳ������֤��
	 * @param memberId
	 * @param memberTel
	 * @return
	 */
	public void findPassword(String memberId,String memberTel);
	
	/**
	 * �û�������֤�룬ϵͳ���ؼ����
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerify(String verifyCode);
	
	/**
	 * �����û���¼���룬�������һسɹ�
	 * @param memberId
	 * @param newPassword
	 */
	public void newPassword(String memberId,String newPassword);
	
}
