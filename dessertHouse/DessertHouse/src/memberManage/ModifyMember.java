package memberManage;

import java.sql.Date;

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
	
	public void modifyLoginPassword(String memberId,String password){
		Member member = memberDao.find(memberId);
		if(member!=null){
			member.getPasswd().setLoginPassword(password);
			memberDao.updateMember(member);
		}
	}
	
	public void modifyResidual(String memberId, String bankCard, double money){
		bankCardDao.modifyResidualForCard(bankCard, String.valueOf(money));
		Member member = memberDao.find(memberId);
		double newResidual = member.getResidual() + money;
		member.setResidual(newResidual);
		memberDao.updateMember(member);
		Recharge recharge = new Recharge();
		recharge.setRechargeId("");//TODO
		recharge.setMemberId(memberId);
		recharge.setRechargeAmount(money);
		recharge.setRechargeTime(new Date(System.currentTimeMillis()));
		memberDao.saveRecharge(recharge);
	}
	
	public boolean modifyRegisterInfo(Member memberInfo) {
		if(memberInfo!=null){
			return memberDao.updateMember(memberInfo);
		}
		else{
			return false;
		}
	}
	
}
