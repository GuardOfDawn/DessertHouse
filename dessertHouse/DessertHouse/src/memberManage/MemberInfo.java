package memberManage;

import models.Member;
import saleManage.MemberForSaleService;

public class MemberInfo implements MemberForSaleService{
	
	private static MemberInfo memberInfo = new MemberInfo();
	
	private MemberInfo(){
		
	}
	
	public static MemberInfo getInstance(){
		return memberInfo;
	}

	public Member getMemberInfo(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkPayPassword(String memberId, String payPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
