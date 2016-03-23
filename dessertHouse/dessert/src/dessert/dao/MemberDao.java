package dessert.dao;

import java.util.ArrayList;

import dessert.models.Member;
import dessert.models.MemberPasswd;
import dessert.models.Recharge;

public interface MemberDao {
	
	public Member find(String memberId);
	
	public ArrayList<Member> find(String[] columns,String[] values);
	
	public ArrayList<Member> findAll();
	
	public boolean addMember(Member member);
	
	public boolean updateMember(Member member);
	
	public MemberPasswd findMemberPasswd(String memberId);
	
	public boolean saveMemberPasswd(MemberPasswd passwd);
	
	public boolean updateMemberPasswd(MemberPasswd passwd);
	
	public boolean saveRecharge(Recharge recharge);
	
	public ArrayList<Recharge> getRecharge(String memberId);

	public ArrayList<Recharge> getAllRecharge();
	
}
