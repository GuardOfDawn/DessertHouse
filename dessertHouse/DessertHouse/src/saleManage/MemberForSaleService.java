package saleManage;

import models.Member;

public interface MemberForSaleService {
	
	public Member getMemberInfo(String memberId);
	
	public boolean checkPayPassword(String memberId, String payPassword);
	
}
