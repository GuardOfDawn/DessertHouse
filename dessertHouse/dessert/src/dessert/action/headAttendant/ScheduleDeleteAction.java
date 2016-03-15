package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;

@Controller
public class ScheduleDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String deleteId = request.getParameter("scheduleTodelete");
			boolean res = scheduleManage.deleteSchedule(deleteId);
			if(res){
				ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
				ListBean undealtScheduleBean = new ListBean();
				undealtScheduleBean.setListBean(undealtScheduleList);
				session.put("undealtSchedule", undealtScheduleBean);
				return SUCCESS;
			}
			else{
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}
		
}
