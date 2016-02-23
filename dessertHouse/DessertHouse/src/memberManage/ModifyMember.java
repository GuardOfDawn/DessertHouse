package memberManage;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;

import dao.BankCardDao;
import dao.MemberDao;
import models.Member;
import models.Recharge;

public class ModifyMember {

	private static ModifyMember modifyMember = new ModifyMember();
	
	private ModifyMember(){
		
	}
	
	public static ModifyMember getInstance(){
		return modifyMember;
	}
	
	@EJB MemberDao memberDao;
	@EJB BankCardDao bankCardDao;
	
	public void modifyPassword(String memberId,String password){
		String[] columns = {"loginPassword"};
		String[] values = new String[1];
		values[0] = password;
		memberDao.modifyMember(memberId, columns, values);
	}
	
	public void modifyResidual(String memberId, String bankCard, String payPassword, double money){
		bankCardDao.modifyResidualForCard(bankCard, String.valueOf(money));
		Member member = memberDao.find(memberId);
		double newResidual = member.getResidual() + money;
		String[] columns = {"residual"};
		String[] values = new String[1];
		values[0] = String.valueOf(newResidual);
		memberDao.modifyMember(memberId, columns, values);
		Recharge recharge = new Recharge();
		recharge.setRechargeId("");//TODO
		recharge.setMemberId(memberId);
		recharge.setRechargeAmount(money);
		recharge.setRechargeTime(new Date(System.currentTimeMillis()));
		memberDao.saveRecharge(recharge);
	}
	
	public boolean modifyRegisterInfo(Member memberInfo) {
		if(memberInfo.getMemberId()!=null){
			ArrayList<Member> m = memberDao.find("memberId", memberInfo.getMemberId());
			if(m!=null&&m.size()>0){
				int count = 0;
				if(memberInfo.getMemberName()!=null)
					count++;
				if(memberInfo.getMemberAge()!=0)
					count++;
				if(memberInfo.getMemberTel()!=null)
					count++;
				if(memberInfo.getMemberLoc()!=null)
					count++;
				String[] columns = new String[count];
				String[] values = new String[count];
				count = 0;
				if(memberInfo.getMemberName()!=null){
					columns[count] = "memberName";
					values[count] = memberInfo.getMemberName();
					count++;
				}
				if(memberInfo.getMemberAge()!=0){
					columns[count] = "memberAge";
					values[count] = String.valueOf(memberInfo.getMemberAge());
					count++;
				}
				if(memberInfo.getMemberTel()!=null){
					columns[count] = "memberTel";
					values[count] = String.valueOf(memberInfo.getMemberTel());
					count++;
				}
				if(memberInfo.getMemberLoc()!=null){
					columns[count] = "memberLoc";
					values[count] = String.valueOf(memberInfo.getMemberLoc());
					count++;
				}
				memberDao.modifyMember(memberInfo.getMemberId(), columns, values);
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
}
