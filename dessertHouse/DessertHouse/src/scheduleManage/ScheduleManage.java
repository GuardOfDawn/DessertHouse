package scheduleManage;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.ScheduleDao;
import models.WeekSchedule;
import remoteService.ScheduleManageService;

@Stateless
public class ScheduleManage implements ScheduleManageService{
	
	@EJB ScheduleDao scheduleDao;

	public boolean addSchedule(WeekSchedule schedule) {
		return scheduleDao.saveSchedule(schedule);
	}

	public WeekSchedule getScheduleInfo(String scheduleId) {
		return scheduleDao.findSchedule(scheduleId);
	}

	public ArrayList<WeekSchedule> retrieveSchedule(String storeId, Date startTime, Date endTime) {
		if(storeId!=null){
			if(startTime==null||endTime==null){
				return scheduleDao.retrieveScheduleForStore(storeId);
			}
			else{
				Date[] period = {startTime,endTime};
				return scheduleDao.retrieveScheduleForStore(storeId, period);
			}
		}
		else{
			return null;
		}
	}

	public ArrayList<WeekSchedule> retrieveScheduleForApprove() {
		int forApprove = 0;
		return scheduleDao.retrieveScheduleByState(forApprove);
	}

	public void approveSchedule(WeekSchedule schedule) {
		scheduleDao.updateSchedule(schedule);
	}

}
