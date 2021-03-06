package dessert.dao;

import java.sql.Date;
import java.util.ArrayList;

import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;

public interface ScheduleDao {
	
	public boolean saveSchedule(WeekSchedule schedule);
	
	public WeekSchedule findSchedule(String scheduleId);
	
	public ArrayList<WeekSchedule> retrieveAllSchedule();
	
	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId,int state);

	public ArrayList<WeekSchedule> retrieveScheduleForStore(String storeId,Date[] period,int state);
	
	public ArrayList<WeekSchedule> retrieveScheduleByState(int scheduleState);
	
	public boolean updateSchedule(WeekSchedule schedule);
	
	public boolean deleteSchedule(WeekSchedule schedule);
	
	public boolean removeSchedule(String scheduleId);
	
	public ArrayList<ScheduleDetail> findScheduleDetail(String scheduleId);

	public boolean removeScheduleDetail(String scheduleId);

	public boolean saveScheduleDetail(ScheduleDetail scheduleDetail);

	public boolean updateScheduleDetail(ScheduleDetail scheduleDetail);

}
