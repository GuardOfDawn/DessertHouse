package memberManage;

import java.util.ArrayList;

import models.Bill;
import models.Member;
import service.MemberManageService;
import verification.VerifyService;

public class MemberManageController implements MemberManageService{
	
	private static MemberManageController memberManageController = new MemberManageController();
	private static AddMember addMember;
	private static ModifyMember modifyMember;
	private static RetrieveMember retrieveMember;
	private static VerifyService verify;
	
	private MemberManageController(){
		addMember = AddMember.getInstance();
		modifyMember = ModifyMember.getInstance();
		retrieveMember = RetrieveMember.getInstance();
	}
	
	public static MemberManageController getInstance(){
		return memberManageController;
	}

	public boolean findMemberName(String memberName) {
		return retrieveMember.findMemberName(memberName);
	}

	public boolean findMemberTel(String tel) {
		return retrieveMember.findMemberTel(tel);
	}

	public boolean matchMemberAndTel(String memberId, String tel) {
		return retrieveMember.matchMemberAndTel(memberId, tel);
	}

	public String verifyByPhone(String phoneNumber) {
		return verify.sendVerifyCode(phoneNumber);
	}

	public String register(String memberName, String memberTel, String password) {
		return addMember.register(memberName, memberTel, password);
	}

	public boolean login(String member, String password) {
		return retrieveMember.matchMemberAndPassword(member, password);
	}

	public void newPassword(String memberId, String newPassword) {
		modifyMember.modifyPassword(memberId, newPassword);
	}

	public boolean checkBankCard(String bankCard) {
		return verify.checkBankCard(bankCard);
	}

	public void setPayPassword(String memberId, String payPassword) {
		addMember.setPayPassword(memberId, payPassword);
	}

	public boolean rechargeToMemberCard(String memberId, String bankCard, String payPassword, double money) {
		if(retrieveMember.matchMemberAndPayPassword(memberId, payPassword)){
			if(verify.checkBankResidual(bankCard,money)){
				modifyMember.modifyResidual(memberId, bankCard, payPassword, money);
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

	public Member checkMemberInfo(String memberId) {
		return retrieveMember.checkMemberInfo(memberId);
	}

	public ArrayList<Bill> checkPayment(String memberId) {
		return retrieveMember.checkPayment(memberId);
	}

	public boolean modifyRegisterInfo(Member memberInfo) {
		return modifyMember.modifyRegisterInfo(memberInfo);
	}

}
