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
public class ScheduleHistoryAction extends BaseAction{

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
				//get approved schedule
				ArrayList<WeekSchedule> approvedScheduleList = scheduleManage.retrieveApprovedSchedule();
				ListBean approvedScheduleBean = new ListBean();
				approvedScheduleBean.setListBean(approvedScheduleList);
				session.put("approvedSchedule", approvedScheduleBean);

				//get disapproved schedule
				ArrayList<WeekSchedule> disapprovedScheduleList = scheduleManage.retrieveDisapprovedSchedule();
				ListBean disapprovedScheduleBean = new ListBean();
				disapprovedScheduleBean.setListBean(disapprovedScheduleList);
				session.put("disapprovedSchedule", disapprovedScheduleBean);
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
