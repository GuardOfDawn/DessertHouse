package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Member;
import models.Recharge;

@Remote
public interface MemberDao {
	
	public Member find(String memberId);
	
	public ArrayList<Member> find(String column,String value);
	
	public ArrayList<Member> find(String[] columns,String[] values);
	
	public boolean checkLogin(String member,String password);
	
	public String addMember(String[] columns,String[] values);
	
	public String modifyMember(String memberId,String[] columns,String[] values);
	
	public void saveRecharge(Recharge recharge);
	
}
