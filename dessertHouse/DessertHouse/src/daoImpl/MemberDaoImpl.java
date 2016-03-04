package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.MemberDao;
import models.Member;
import models.Recharge;

@Stateless
public class MemberDaoImpl implements MemberDao{
	
	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("rawtypes")
	public Member find(String memberId) {
		Query query = em.createQuery("select m from Member m where m.memberId=?1");
		query.setParameter(1, memberId);
		List list = query.getResultList();
		if(list!=null&&list.size()==1){
			return (Member) list.get(0);
		}
		else{
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Member> find(String[] columns, String[] values) {
		if(columns!=null&&columns.length!=0){
			int index = 1;
			StringBuffer ql = new StringBuffer();
			ql.append("select m from Member m where ");
			for(index=1;index<=columns.length;index++){
				if(columns[index-1].equals("loginPassword")||columns[index-1].equals("payPassword")){
					ql.append("m.passwd.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
				}
				else{
					ql.append("m.").append(columns[index-1]).append("=").append("?").append(index)
						.append(" and ");
				}
			}
			ql.delete(ql.length()-5, ql.length());
			Query query = em.createQuery(ql.toString());
			for(int i=1;i<=index;i++){
				query.setParameter(i, values[i-1]);
			}
			List list = query.getResultList();
			return (ArrayList<Member>) list;
		}
		else{
			return null;
		}
	}

	public boolean addMember(Member member) {
		try {
			em.persist(member);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateMember(Member member) {
		try{
			em.merge(member);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveRecharge(Recharge recharge) {
		try {
			em.persist(recharge);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
