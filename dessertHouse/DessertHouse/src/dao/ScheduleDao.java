package dao;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.Local;

import models.WeekSchedule;

@Local
public interface ScheduleDao {
	
	public boolean saveSchedule(WeekSchedule schedule);
	
	public WeekSchedule findSchedule(String scheduleId);
	
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId);

	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId,Date[] period);
	
	public ArrayList<WeekSchedule> retrieveScheduleByState(int scheduleState);
	
	public boolean updateSchedule(WeekSchedule schedule);

}
