package memberOperation;

import java.util.ArrayList;

import models.Bill;
import models.Member;

public interface MemberInfoService {

	/**
	 * �û��鿴�Լ��Ļ�Ա��Ϣ
	 * @param memberId
	 * @return
	 */
	public Member checkMemberInfo(String memberId);
	
	/**
	 * �û��鿴�Լ������Ѽ�¼
	 * @param memberId
	 * @return
	 */
	public ArrayList<Bill> checkPayment(String memberId);
	
	/**
	 * �û��޸��Լ��Ļ�Ա��Ϣ
	 * @param memberInfo
	 * @return
	 */
	public boolean modifyRegisterInfo(Member memberInfo);
	
}
