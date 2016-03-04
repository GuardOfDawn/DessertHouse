package memberOperation;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import models.Bill;
import models.Member;
import service.MemberActivateService;
import service.MemberInfoService;
import service.MemberLoginService;
import service.MemberRegisterService;

@Service
public class MemberOperationController implements MemberRegisterService
		,MemberLoginService,MemberActivateService,MemberInfoService{
	
	private MemberRegister memberRegister;
	private MemberLogin memberLogin;
	private MemberInfoCheck memberInfoCheck;
	
	public MemberOperationController(){
		memberRegister = new MemberRegister();
		memberLogin = new MemberLogin();
		memberInfoCheck = new MemberInfoCheck();
	}

	public Member checkMemberInfo(String memberId) {
		return memberInfoCheck.checkMemberInfo(memberId);
	}

	public ArrayList<Bill> checkPayment(String memberId) {
		return memberInfoCheck.checkPayment(memberId);
	}

	public boolean modifyRegisterInfo(Member memberInfo) {
		return memberInfoCheck.modifyRegisterInfo(memberInfo);
	}

	public boolean login(String member, String password) {
		return memberLogin.login(member, password);
	}

	public boolean checkBankCard(String bankCard) {
		return memberLogin.checkBankCard(bankCard);
	}

	public void setPayPassword(String memberId, String payPassword) {
		memberLogin.setPayPassword(memberId, payPassword);
	}

	public boolean rechargeToMemberCard(String memberId, String bankCard, String payPassword, double money) {
		return memberLogin.rechargeToMemberCard(memberId, bankCard, payPassword, money);
	}

	public void findPassword(String memberName, String memberTel) {
		memberLogin.findPassword(memberName, memberTel);
	}

	public boolean checkVerify(String verifyCode) {
		return memberLogin.checkVerify(verifyCode);
	}

	public void newPassword(String memberId, String newPassword) {
		memberLogin.newPassword(memberId, newPassword);
	}

	public boolean checkRegisterName(String memberName) {
		return memberRegister.checkRegisterName(memberName);
	}

	public void verifyByPhone(String phoneNumber) {
		memberRegister.verifyByPhone(phoneNumber);
	}

	public boolean checkVerifyCode(String verifyCode) {
		return memberRegister.checkVerifyCode(verifyCode);
	}

	public Member register(String memberName, String password) {
		return memberRegister.register(memberName, password);
	}

}
