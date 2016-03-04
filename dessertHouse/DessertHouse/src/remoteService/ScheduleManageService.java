package remoteService;

import java.sql.Date;
import java.util.ArrayList;

import javax.ejb.Remote;

import models.WeekSchedule;

@Remote
public interface ScheduleManageService {

	public boolean addSchedule(WeekSchedule schedule);
	
	public WeekSchedule getScheduleInfo(String scheduleId);
	
	/**
	 * 搜索销售计划
	 * @param storeId
	 * @param startTime 当为null时，搜索该店历史所有销售计划
	 * @param endTime
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveSchedule(String storeId, Date startTime, Date endTime);
	
	public ArrayList<WeekSchedule> retrieveScheduleForApprove();
	
	public void approveSchedule(WeekSchedule schedule);
	
}
