package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.utility.FormulationNumber;

@Controller
public class CardRegainAction extends BaseAction{

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
			Double regainAmount = Double.parseDouble(request.getParameter("regainAmount"));
			String paypasswd = request.getParameter("paypasswd");
			boolean res = memberManage.rechargeToMemberCard(m.getMemberId(), m.getBankCardId(), paypasswd, regainAmount);
			if(res){
				Member member = memberManage.checkMemberInfo(m.getMemberId());
				member.setCardState(FormulationNumber.cardActive);
				member.setSuspendTime(null);
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
