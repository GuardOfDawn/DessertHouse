package service;

public interface MemberActivateService{
	
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
	
}
