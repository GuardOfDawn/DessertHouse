package dessert.action.branchAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Recharge;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class RechargeCheckAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		if(session.get("userId")!=null){
			String memberId = request.getParameter("memberId");
			ArrayList<Recharge> rechargeList;
			if(memberId==null){
				rechargeList = memberManage.checkAllRecharge();
			}
			else{
				rechargeList = memberManage.checkRecharge(memberId);
			}
			ListBean rechargeListBean = new ListBean();
			rechargeListBean.setListBean(rechargeList);
			request.setAttribute("rechargeList", rechargeListBean);
			if(memberId!=null){
				request.setAttribute("memberId", memberId);
			}
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
