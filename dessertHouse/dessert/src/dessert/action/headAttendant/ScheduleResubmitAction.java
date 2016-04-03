package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.utility.FormulationNumber;

@Controller
public class ScheduleResubmitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String scheduleTosubmit = request.getParameter("scheduleTosubmit");
			WeekSchedule schedule = scheduleManage.getScheduleInfo(scheduleTosubmit);
			schedule.setScheduleState(FormulationNumber.scheduleMade);
			scheduleManage.updateSchedule(schedule);
			request.setAttribute("resubmit", "1");
			ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
			ArrayList<WeekSchedule> disapprovedScheduleList = scheduleManage.retrieveDisapprovedSchedule();
			ListBean undealtScheduleBean = new ListBean();
			undealtScheduleBean.setListBean(undealtScheduleList);
			ListBean disapprovedScheduleBean = new ListBean();
			disapprovedScheduleBean.setListBean(disapprovedScheduleList);
			session.put("undealtSchedule", undealtScheduleBean);
			session.put("disapprovedSchedule", disapprovedScheduleBean);
			return SUCCESS;
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
