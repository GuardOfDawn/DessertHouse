package dessert.action.memberOrder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Bill;
import dessert.models.Member;
import dessert.remoteService.memberManage.MemberManageService;

@Controller
public class BillViewAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	
	public String execute(){
		if(session.get("member")!=null){
			Member m = (Member) session.get("member");
			ArrayList<Bill> billList = memberManage.checkBillInfo(m.getMemberId());
			ListBean billListBean = new ListBean();
			billListBean.setListBean(billList);
			request.setAttribute("billList", billListBean);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
