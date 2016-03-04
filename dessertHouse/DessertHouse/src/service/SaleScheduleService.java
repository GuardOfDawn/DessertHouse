package service;

import java.sql.Date;
import java.util.ArrayList;

import models.ScheduleDetail;
import models.Store;
import models.WeekSchedule;

public interface SaleScheduleService {
	
	/**
	 * �鿴ĳ������ĳʱ����ڵ����ۼƻ�
	 * @param storeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveWeekSchedule(String storeId,Date startTime,Date endTime);

	/**
	 * ����Id������ۼƻ�
	 * @param scheduleId
	 * @return
	 */
	public WeekSchedule getWeekSchedule(String scheduleId);
	
	/**
	 * ��ʼΪĳ�������ƶ���һ�ܵ����ۼƻ�
	 * @param store
	 */
	public void startSchedule(Store store);
	
	/**
	 * �������ۼƻ���ʼʱ��
	 * @param startTime
	 */
	public void setStartTime(Date startTime);
	
	/**
	 * �������ۼƻ���ֹʱ��
	 * @param endTime
	 */
	public void setEndTime(Date endTime);
	
	/**
	 * ������ۼƻ�����Ŀ
	 * @param item
	 */
	public void addScheduleItem(ScheduleDetail item);
	
	/**
	 * ɾȥĳ�����ۼƻ�����Ŀ
	 * @param item
	 */
	public void deleteScheduleItem(ScheduleDetail item);
	
	/**
	 * ���ۼƻ���д��ϣ�ȷ�ϱ���
	 * @return
	 */
	public boolean ensureSchedule();
	
	/**
	 * ȡ�����ۼƻ����ƶ�
	 */
	public void cancelSchedule();
	
	/**
	 * �鿴����׼�����ۼƻ�
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveScheduleForApprove();
	
	/**
	 * ��׼һ�����ۼƻ�
	 * @param schedule
	 */
	public void approveSchedule(WeekSchedule schedule);
	
	/**
	 * ĳ�����ۼƻ�δͨ����׼
	 * @param schedule
	 */
	public void disApproveSchedule(WeekSchedule schedule);
	
}
