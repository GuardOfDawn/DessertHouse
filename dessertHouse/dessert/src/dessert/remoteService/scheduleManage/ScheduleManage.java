package dessert.remoteService.scheduleManage;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.ScheduleDao;
import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;

@Service
public class ScheduleManage implements ScheduleManageService{
	
	@Autowired
	private ScheduleDao scheduleDao;

	public boolean addSchedule(WeekSchedule schedule) {
		return scheduleDao.saveSchedule(schedule);
	}
	
	public boolean deleteSchedule(String scheduleId){
		WeekSchedule sc = getScheduleInfo(scheduleId);
		if(sc!=null){
			boolean res = false;
			res = scheduleDao.removeScheduleDetail(scheduleId);
			if(res){
				return scheduleDao.removeSchedule(scheduleId);
			}
			else{
				return false;
			}
		}
		else{return false;}
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

	public ArrayList<WeekSchedule> retrieveApprovedSchedule(){
		int approved = 1;
		return scheduleDao.retrieveScheduleByState(approved);
	}

	public ArrayList<WeekSchedule> retrieveDisapprovedSchedule(){
		int disapproved = 2;
		return scheduleDao.retrieveScheduleByState(disapproved);
	}

	public void approveSchedule(WeekSchedule schedule) {
		scheduleDao.updateSchedule(schedule);
	}
	
	public boolean addScheduleDetail(ScheduleDetail scheduleDetail){
		return scheduleDao.saveScheduleDetail(scheduleDetail);
	}

}