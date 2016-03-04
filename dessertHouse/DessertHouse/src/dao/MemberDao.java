package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Member;
import models.Recharge;

@Local
public interface MemberDao {
	
	public Member find(String memberId);
	
	public ArrayList<Member> find(String[] columns,String[] values);
	
	public boolean addMember(Member member);
	
	public boolean updateMember(Member member);
	
	public boolean saveRecharge(Recharge recharge);
	
}
