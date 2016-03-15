package dessert.action.storeManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.StoreUser;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;

@Controller
public class ScheduleMessageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			StoreUser user = (StoreUser)session.get("user");
			if(user.getUserType().equals("StoreManager")){
				//get schedule
				ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
				ListBean undealtScheduleBean = new ListBean();
				undealtScheduleBean.setListBean(undealtScheduleList);
				session.put("undealtSchedule", undealtScheduleBean);
				return SUCCESS;
			}
			else{
				return ERROR;
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}
	
}
