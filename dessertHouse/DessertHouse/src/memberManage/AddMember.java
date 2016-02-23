package memberManage;

import javax.ejb.EJB;

import dao.MemberDao;

public class AddMember {
	
	private static AddMember addMember = new AddMember();
	
	private AddMember(){
		
	}
	
	public static AddMember getInstance(){
		return addMember;
	}

	@EJB MemberDao memberDao;
	
	public String register(String memberName, String tel, String password) {
		String[] columns = {"memberName","memberTel","loginPassword"};
		String[] values = new String[3];
		values[0] = memberName;
		values[1] = tel;
		values[2] = password;
		return memberDao.addMember(columns, values);
	}

	public void setPayPassword(String memberId, String payPassword){
		String[] columns = {"payPassword"};
		String[] values = new String[1];
		values[0] = payPassword;
		memberDao.modifyMember(memberId, columns, values);
	}
	
}
