package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class MemberBonusConvertAction extends BaseAction {

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
			int bonusUsed = Integer.parseInt(request.getParameter("bonusUsed"));
			int bonusOwn = m.getBonusPoint();
			if(bonusOwn>=bonusUsed){
				m.setBonusPoint(bonusOwn-bonusUsed);
				double bonusRate = 0.1;
				m.setResidual(m.getResidual()+bonusUsed*bonusRate);
				memberManage.modifyRegisterInfo(m);
				session.put("member", m);
				request.setAttribute("bonusConvertRes", "1");
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
