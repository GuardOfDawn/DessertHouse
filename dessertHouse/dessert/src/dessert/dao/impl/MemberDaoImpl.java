package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dessert.dao.BaseDao;
import dessert.dao.MemberDao;
import dessert.models.Member;
import dessert.models.MemberPasswd;
import dessert.models.Recharge;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private BaseDao baseDao;
	private Session session;

	@SuppressWarnings("rawtypes")
	public Member find(String memberId) {
		session = baseDao.getNewSession();
		Query query = session.createQuery("select m from Member m where m.memberId=?");
		query.setString(0, memberId);
		List list = query.list();
		session.close();
		if(list!=null&&list.size()==1){
			return (Member) list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Member> find(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select m from Member m where ");
			for(index=0;index<columns.length;index++){
				if(columns[index].equals("loginPassword")||columns[index].equals("payPassword")){
					ql.append("m.passwd.").append(columns[index]).append("=").append("?")
						.append(" and ");
				}
				else{
					ql.append("m.").append(columns[index]).append("=").append("?")
						.append(" and ");
				}
			}
			ql.append("m.memberId<>?");
			session = baseDao.getNewSession();
			Query query = session.createQuery(ql.toString());
			for(int i=0;i<values.length;i++){
				query.setString(i, values[i]);
			}
			query.setString(values.length, "0000000");
			List list = query.list();
//			session.close();
			return (ArrayList<Member>) list;
		}
		else{
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<Member> findAll(){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select m from Member m where m.memberId<>?");
		query.setString(0, "0000000");
		List list = query.list();
//		session.close();
		return (ArrayList<Member>) list;
	}

	public boolean addMember(Member member) {
		try {
			baseDao.save(member);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateMember(Member member) {
		try{
			baseDao.update(member);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public MemberPasswd findMemberPasswd(String memberId) {
		try {
			session = baseDao.getNewSession();
			Query query = session.createQuery("select mp from MemberPasswd mp where mp.member.memberId=?");
			query.setString(0, memberId);
			List list = query.list();
			session.close();
			if(list!=null&&list.size()==1){
				return (MemberPasswd) list.get(0);
			}
			else{
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveMemberPasswd(MemberPasswd passwd){
		try {
			baseDao.save(passwd);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateMemberPasswd(MemberPasswd passwd){
		try {
			baseDao.update(passwd);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveRecharge(Recharge recharge) {
		try {
			baseDao.save(recharge);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Recharge> getRecharge(String memberId){
		try {
			session = baseDao.getNewSession();
			Query query = session.createQuery("select r from Recharge r where r.member.memberId=?");
			query.setString(0, memberId);
			List list = query.list();
			session.close();
			if(list!=null){
				return (ArrayList<Recharge>) list;
			}
			else{
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Recharge> getAllRecharge(){
		session = baseDao.getNewSession();
		Query query = session.createQuery("select r from Recharge r");
		List list = query.list();
		session.close();
		if(list!=null){
			return (ArrayList<Recharge>) list;
		}
		else{
			return null;
		}
	}

}
