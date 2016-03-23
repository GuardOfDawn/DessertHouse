package dessert.action.branchAttendant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.models.StoreUser;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.utility.MemberLevelUtility;

@Controller
public class SalememberInputAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		StoreUser user = (StoreUser) session.get("user");
		if(user!=null){
			String memberId = request.getParameter("memberId");
			Member member = memberManage.checkMemberInfo(memberId);
			if(member!=null){
				request.setAttribute("memberCheckRes", "find");
				request.setAttribute("memberId", memberId);
				request.setAttribute("member", member);
				request.setAttribute("favorRate", MemberLevelUtility.getInstance().getFavorRate(member.getMemberLevel()));
				return SUCCESS;
			}
			else{
				request.setAttribute("memberCheckRes", "fail");
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
