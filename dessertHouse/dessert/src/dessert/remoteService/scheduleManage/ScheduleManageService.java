package dessert.remoteService.scheduleManage;

import java.sql.Date;
import java.util.ArrayList;

import dessert.models.ScheduleDetail;
import dessert.models.WeekSchedule;

public interface ScheduleManageService {

	public boolean addSchedule(WeekSchedule schedule);
	
	public boolean deleteSchedule(String scheduleId);
	
	public WeekSchedule getScheduleInfo(String scheduleId);

	public ArrayList<WeekSchedule> retrieveApprovedSchedule();
	
	/**
	 * 搜索销售计划
	 * @param storeId
	 * @param startTime 当为null时，搜索该店历史所有销售计划
	 * @param endTime
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveSchedule(String storeId, Date startTime, Date endTime);

	public WeekSchedule retrieveSchedule(String storeId, Date date);

	public ArrayList<ScheduleDetail> retrieveScheduleDetail(String scheduleId);

	public ArrayList<ScheduleDetail> retrieveScheduleDetail(String scheduleId,Date date);

	public ArrayList<WeekSchedule> retrieveDisapprovedSchedule();
	
	public ArrayList<WeekSchedule> retrieveScheduleForApprove();
	
	public void approveSchedule(WeekSchedule schedule);

	public void updateSchedule(WeekSchedule schedule);
	
	public boolean addScheduleDetail(ScheduleDetail scheduleDetail);
	
	public boolean updateScheduleDetail(ScheduleDetail scheduleDetail);
	
}
