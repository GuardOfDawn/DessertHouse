package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;

@Controller
public class ScheduleViewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			ArrayList<WeekSchedule> scheduleList = scheduleManage.retrieveApprovedSchedule();
			ListBean allScheduleBean = new ListBean();
			allScheduleBean.setListBean(scheduleList);
			session.put("approvedSchedule", allScheduleBean);
			return SUCCESS;
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
