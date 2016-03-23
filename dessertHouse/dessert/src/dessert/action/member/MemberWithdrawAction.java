package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class MemberWithdrawAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		if(session.get("member")!=null){
			String memberId = request.getParameter("memberId");
			String cardState = request.getParameter("cardState");
			if(cardState!=null){
				memberManage.withdrawCardInfo(memberId);
				session.clear();
				request.setAttribute("withdrawRes", "1");
				return SUCCESS;
			}
			else{
				String paypasswd = request.getParameter("paypasswd");
				boolean res = memberManage.matchMemberAndPayPassword(memberId, paypasswd);
				if(res){
					memberManage.withdrawCardInfo(memberId);
					session.clear();
					request.setAttribute("withdrawRes", "1");
					return SUCCESS;
				}
				else{
					request.setAttribute("withdrawFailed", "1");
					return "failure";
				}
			}
		}
		else{
			return INPUT;
		}
	}

}
