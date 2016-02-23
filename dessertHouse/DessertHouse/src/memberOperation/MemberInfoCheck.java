package memberOperation;

import java.util.ArrayList;

import models.Bill;
import models.Member;

public class MemberInfoCheck {
	
	public MemberInfoCheck(){
		
	}
	
	/**
	 * 用户查看自己的会员信息
	 * @param memberId
	 * @return
	 */
	public Member checkMemberInfo(String memberId){
		Member member = null;
		
		return member;
	}
	
	/**
	 * 用户查看自己的消费记录
	 * @param memberId
	 * @return
	 */
	public ArrayList<Bill> checkPayment(String memberId){
		ArrayList<Bill> billList = null;
		
		return billList;
	}
	
	/**
	 * 用户修改自己的会员信息
	 * @param memberInfo
	 * @return
	 */
	public boolean modifyRegisterInfo(Member memberInfo){
		boolean res = false;
		
		return res;
	}
	
}
