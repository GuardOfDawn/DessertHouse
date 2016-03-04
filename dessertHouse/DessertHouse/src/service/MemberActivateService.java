package service;

public interface MemberActivateService{
	
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
	
}
