package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class MemberModifyAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("member")!=null){
			String memberId = request.getParameter("memberId");
			String memberName = request.getParameter("memberName");
			if(!memberManage.findMemberName(memberId,memberName)){
				String memberTel = request.getParameter("memberTel");
				if(!memberManage.findMemberTel(memberId,memberTel)){
					Member member = memberManage.checkMemberInfo(memberId);
					member.setMemberName(memberName);
					member.setMemberGender(Integer.parseInt(request.getParameter("memberGender")));
					member.setMemberAge(Integer.parseInt(request.getParameter("memberAge")));
					member.setMemberTel(memberTel);
					member.setMemberLoc(request.getParameter("memberLoc"));
					memberManage.modifyRegisterInfo(member);
					session.put("member", member);
					return SUCCESS;
				}
				else{
					request.setAttribute("errorType", "telExist");
					request.setAttribute("value", memberTel);
					return "failure";
				}
			}
			else{
				request.setAttribute("errorType", "nameExist");
				request.setAttribute("value", memberName);
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
