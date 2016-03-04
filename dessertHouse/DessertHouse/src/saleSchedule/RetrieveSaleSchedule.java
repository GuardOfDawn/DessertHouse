package saleSchedule;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;

import models.WeekSchedule;
import remoteService.ScheduleManageService;

public class RetrieveSaleSchedule {
	
	@EJB ScheduleManageService scheduleManage;
	
	public ArrayList<WeekSchedule> retrieveWeekSchedule(String storeId, Date startTime, Date endTime) {
		return scheduleManage.retrieveSchedule(storeId, startTime, endTime);
	}
	

	public WeekSchedule getWeekSchedule(String scheduleId){
		return scheduleManage.getScheduleInfo(scheduleId);
	}
	
	public ArrayList<WeekSchedule> retrieveScheduleForApprove(){
		return scheduleManage.retrieveScheduleForApprove();
	}

}
