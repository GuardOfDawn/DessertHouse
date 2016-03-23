package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class MemberLoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		Member m = (Member) session.get("member");
		if(m==null){
			String member = request.getParameter("member");
			String passwd = request.getParameter("password");
			boolean res = memberManage.login(member, passwd);
			if(res){
				session.put("member", memberManage.checkMemberInfo(member));
				return SUCCESS;
			}
			else{
				request.setAttribute("loginFailed", "1");
				return "failure";
			}
		}
		else{
			String member = m.getMemberId();
			String passwd = memberManage.checkMemberInfo(member).getPasswd().getLoginPassword();
			boolean res = memberManage.login(member, passwd);
			if(res){
				session.put("member", memberManage.checkMemberInfo(m.getMemberId()));
				return SUCCESS;
			}
			else{
				session.clear();
				return "failure";
			}
		}
	}

}
