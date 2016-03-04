package memberManage;

import java.util.ArrayList;

import javax.ejb.EJB;

import dao.BillDao;
import dao.MemberDao;
import models.Bill;
import models.Member;

public class RetrieveMember {

	private static RetrieveMember retrieveMember = new RetrieveMember();
	
	private RetrieveMember(){
		
	}
	
	public static RetrieveMember getInstance(){
		return retrieveMember;
	}
	
	@EJB MemberDao memberDao;
	@EJB BillDao billDao;
	
	public boolean findMemberName(String memberName) {
		String[] columns = {"memberName"};
		String[] values = new String[1];
		values[0] = memberName;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}

	public boolean findMemberTel(String tel) {
		String[] columns = {"memberTel"};
		String[] values = new String[0];
		values[0] = tel;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}

	public boolean matchMemberAndTel(String memberId, String tel) {
		String[] columns = {"memberId","memberTel"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = tel;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()!=0);
	}
	
	public boolean matchMemberAndPassword(String member,String password){
		String[] columns = {"memberId","loginPassword"};
		String[] values = new String[2];
		values[0] = member;
		values[1] = password;
		ArrayList<Member> res = memberDao.find(columns, values);
		return (res!=null)&&(res.size()==1);
	}
	
	public boolean matchMemberAndPayPassword(String memberId,String password){
		String[] columns = {"memberId","payPassword"};
		String[] values = new String[2];
		values[0] = memberId;
		values[1] = password;
		ArrayList<Member> memberList = memberDao.find(columns, values);
		return (memberList!=null)&&(memberList.size()==1);
	}
	
	public Member checkMemberInfo(String memberId) {
		String[] columns = {"memberId"};
		String[] values = new String[1];
		values[0] = memberId;
		ArrayList<Member> res = memberDao.find(columns, values);
		if(res!=null&&res.size()>0){
			return res.get(0);
		}
		else{
			return null;
		}
	}

	public ArrayList<Bill> checkPayment(String memberId) {
		return billDao.findBillForMember(memberId);
	}

}
