package dessert.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class BandBankCardAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("member")!=null){
			String bankCard = request.getParameter("bankCard");
			if(memberManage.checkBankCard(bankCard)){
				Member m = (Member) session.get("member");
				String memberId = m.getMemberId();
				String paypasswd = request.getParameter("paypasswd");
				boolean res = memberManage.setPayPassword(memberId, paypasswd);
				if(res){
					m.setBankCardId(bankCard);
					memberManage.modifyRegisterInfo(m);
					session.put("member", m);
					request.setAttribute("continueActivate", "bandSuccess");
					return SUCCESS;
				}
				else{
					request.setAttribute("continueActivate", "bandFailure");
					return "failure";
				}
			}
			else{
				request.setAttribute("continueActivate", "bandFailure");
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}

}
