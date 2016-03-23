package dessert.action.memberOrder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Member;
import dessert.models.Recharge;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class RechargeViewAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		if(session.get("member")!=null){
			Member m = (Member) session.get("member");
			ArrayList<Recharge> rechargeList = memberManage.checkRecharge(m.getMemberId());
			ListBean rechargeListBean = new ListBean();
			rechargeListBean.setListBean(rechargeList);
			request.setAttribute("rechargeList", rechargeListBean);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
