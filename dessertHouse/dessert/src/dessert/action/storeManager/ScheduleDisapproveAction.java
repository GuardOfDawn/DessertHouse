package dessert.action.storeManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.utility.FormulationNumber;

@Controller
public class ScheduleDisapproveAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String scheduleApproveId = request.getParameter("scheduleId");
			WeekSchedule schedule = scheduleManage.getScheduleInfo(scheduleApproveId);
			boolean disapproveRes = false;
			if(schedule!=null){
				schedule.setScheduleState(FormulationNumber.scheduleDisapproved);
				scheduleManage.approveSchedule(schedule);
				disapproveRes = true;
				ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
				ListBean undealtScheduleBean = new ListBean();
				undealtScheduleBean.setListBean(undealtScheduleList);
				session.put("undealtSchedule", undealtScheduleBean);
				request.setAttribute("disapproveRes", String.valueOf(disapproveRes));
				request.setAttribute("disapprovedId", scheduleApproveId);
				return SUCCESS;
			}
			else{
				request.setAttribute("disapproveRes", String.valueOf(disapproveRes));
				request.setAttribute("disapprovedId", scheduleApproveId);
				return "failure";
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
