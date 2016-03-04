package memberManage;

import javax.ejb.EJB;

import dao.MemberDao;
import models.Member;

public class AddMember {
	
	private static AddMember addMember = new AddMember();
	
	private AddMember(){
		
	}
	
	public static AddMember getInstance(){
		return addMember;
	}

	@EJB MemberDao memberDao;
	
	public Member register(String memberName, String tel, String password) {
		
		return null;
	}

	public void setPayPassword(String memberId, String payPassword){
		String[] columns = {"payPassword"};
		String[] values = new String[1];
		values[0] = payPassword;
		memberDao.modifyMember(memberId, columns, values);
	}
	
}
