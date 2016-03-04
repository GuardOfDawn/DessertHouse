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
	 * �������ۼƻ�
	 * @param storeId
	 * @param startTime ��Ϊnullʱ�������õ���ʷ�������ۼƻ�
	 * @param endTime
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveSchedule(String storeId, Date startTime, Date endTime);
	
	public ArrayList<WeekSchedule> retrieveScheduleForApprove();
	
	public void approveSchedule(WeekSchedule schedule);
	
}
