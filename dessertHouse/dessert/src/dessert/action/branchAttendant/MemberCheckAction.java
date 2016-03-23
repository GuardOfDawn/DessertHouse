package dessert.action.branchAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class MemberCheckAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		if(session.get("userId")!=null){
			ArrayList<Member> memberList = memberManage.checkAllMember();
			ListBean memberListBean = new ListBean();
			memberListBean.setListBean(memberList);
			request.setAttribute("memberList", memberListBean);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
