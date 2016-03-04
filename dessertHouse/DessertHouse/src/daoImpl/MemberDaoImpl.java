package daoImpl;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.MemberDao;
import models.Member;
import models.Recharge;

@Stateless
public class MemberDaoImpl implements MemberDao{
	
	@PersistenceContext
	protected EntityManager em;

	public Member find(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Member> find(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Member> find(String[] columns, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkLogin(String member, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public String addMember(String[] columns, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public String modifyMember(String memberId, String[] columns, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveRecharge(Recharge recharge) {
		// TODO Auto-generated method stub
		
	}
	
	
}
