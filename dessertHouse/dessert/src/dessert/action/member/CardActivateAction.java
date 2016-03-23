package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class CardActivateAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("member")!=null){
			Member m = (Member) session.get("member");
			String memberId = m.getMemberId();
			String bankCard = request.getParameter("bankCard");
			double recharge = Double.parseDouble(request.getParameter("rechargeAmount"));
			String paypasswd = request.getParameter("payPasswdInput");
			boolean res = memberManage.rechargeToMemberCard(memberId, bankCard, paypasswd, recharge);
			if(res){
				Member member = memberManage.checkMemberInfo(memberId);
				int activated = 1;
				member.setCardState(activated);
				memberManage.modifyRegisterInfo(member);
				session.put("member", member);
				return SUCCESS;
			}
			else{
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}
	
}
