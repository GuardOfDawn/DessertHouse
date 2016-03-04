package memberOperation;

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
	 * ����ע��Ļ�Ա���Ƿ����
	 * @param memberName
	 * @return
	 */
	public boolean checkRegisterName(String memberName){
		boolean res = memberManage.findMemberName(memberName);
		if(res){
			member = new Member();
			member.setMemberName(memberName);
		}
		return res;
	}
	
	/**
	 * �û������ֻ������ȡע����֤�룬ϵͳ������֤��󣬷��͵����ֻ�������
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
	 * �û�������֤�룬ϵͳ������֤���
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
	 * ��֤��ע�ᣬϵͳ���ɻ�Ա���뷵��
	 * @param memberName
	 * @param password
	 * @return
	 */
	public Member register(String memberName,String password){
		MemberPasswd passwd = new MemberPasswd();
		passwd.setId("");//TODO
		passwd.setLoginPassword(password);
		member.setPasswd(passwd);
		//TODO
		return member;
	}
	
}
