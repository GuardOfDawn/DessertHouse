package memberOperation;

import java.util.ArrayList;

import models.Bill;
import models.Member;

public interface MemberInfoService {

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
