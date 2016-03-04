package memberOperation;

import java.sql.Date;

import javax.ejb.EJB;

import models.Member;
import models.MemberPasswd;
import remoteService.MemberManageService;

public class MemberRegister {
	
	@EJB MemberManageService memberManage;
	
	public MemberRegister(){
		
	}
	
	private Member member;
	private String verifyCode;
	
	/**
	 * 检查待注册的会员名是否存在
	 * @param memberName
	 * @return
	 */
	public boolean checkRegisterName(String memberName){
		boolean res = memberManage.findMemberName(memberName);
		if(res){
			member.setMemberName(memberName);
		}
		return res;
	}
	
	/**
	 * 用户输入手机号码获取注册验证码，系统生成验证码后，发送到该手机号码上
	 * @param phoneNumber
	 */
	public boolean verifyByPhone(String phoneNumber){
		boolean res = memberManage.findMemberTel(phoneNumber);
		if(!res){
			verifyCode = memberManage.verifyByPhone(phoneNumber);
			member.setMemberTel(phoneNumber);
		}
		return !res;
	}
	
	/**
	 * 用户输入验证码，系统返回验证结果
	 * @param verifyCode
	 * @return
	 */
	public boolean checkVerifyCode(String verifyCode){
		boolean res = this.verifyCode.equals(verifyCode);
		if(!res){
			member.setMemberTel(null);
		}
		return res;
	}
	
	/**
	 * 验证后注册，系统生成会员号码返回
	 * @param memberName
	 * @param password
	 * @return
	 */
	public Member register(String memberName,String password){
		Member m = memberManage.register(memberName, member.getMemberTel(), password);
		member = null;
		return m;
	}
	
}
