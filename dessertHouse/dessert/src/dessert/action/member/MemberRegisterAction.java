package dessert.action.member;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.models.MemberPasswd;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.utility.IDProducer;

@Controller
public class MemberRegisterAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		String errorType = null;
		String memberName = request.getParameter("memberName");
		if(memberManage.findMemberName(memberName)){
			errorType = "nameExist";
		}
		String memberTel = request.getParameter("memberTel");
		if(memberManage.findMemberTel(memberTel)){
			errorType = "telExist";
		}
		Member member = new Member();
		member.setMemberName(memberName);
		member.setMemberGender(Integer.parseInt(request.getParameter("memberGender")));
		member.setMemberAge(Integer.parseInt(request.getParameter("memberAge")));
		member.setMemberTel(memberTel);
		member.setMemberLoc(request.getParameter("memberLoc"));
		
		if(errorType==null){
			member.setMemberId(IDProducer.getInstance().produceMemberId());
			int unactivated = 0;
			member.setCardState(unactivated);
			member.setRegisterTime(new Date(System.currentTimeMillis()));
			memberManage.register(member);
			
			Member m = memberManage.checkMemberInfo(member.getMemberId());
			MemberPasswd passwd = new MemberPasswd();
			passwd.setId(IDProducer.getInstance().produceMemberPasswdId());
			passwd.setLoginPassword(request.getParameter("password"));
			passwd.setMember(m);
			memberManage.setLoginPassword(passwd);
			session.put("member", m);
			return SUCCESS;
		}
		else{
			request.setAttribute("errorType", errorType);
			request.setAttribute("memberInput", member);
			return errorType;
		}
	}

	
}
