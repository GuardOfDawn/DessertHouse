package saleSchedule;

import javax.ejb.EJB;

import models.WeekSchedule;
import remoteService.ScheduleManageService;

public class ApproveSaleSchedule {
	
	@EJB ScheduleManageService scheduleManage;

	public void disApproveSchedule(WeekSchedule schedule){
		//TODO 通知总店服务员修改
		
	}
	
	public void approveSchedule(WeekSchedule schedule){
		int approved = 1;
		schedule.setScheduleState(approved);
		scheduleManage.addSchedule(schedule);
	}
	
}
