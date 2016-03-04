package service;

import java.sql.Date;
import java.util.ArrayList;

import models.ScheduleDetail;
import models.Store;
import models.WeekSchedule;

public interface SaleScheduleService {
	
	/**
	 * 查看某个店铺某时间段内的销售计划
	 * @param storeId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveWeekSchedule(String storeId,Date startTime,Date endTime);

	/**
	 * 根据Id获得销售计划
	 * @param scheduleId
	 * @return
	 */
	public WeekSchedule getWeekSchedule(String scheduleId);
	
	/**
	 * 开始为某个店铺制定下一周的销售计划
	 * @param store
	 */
	public void startSchedule(Store store);
	
	/**
	 * 设置销售计划起始时间
	 * @param startTime
	 */
	public void setStartTime(Date startTime);
	
	/**
	 * 设置销售计划终止时间
	 * @param endTime
	 */
	public void setEndTime(Date endTime);
	
	/**
	 * 添加销售计划子条目
	 * @param item
	 */
	public void addScheduleItem(ScheduleDetail item);
	
	/**
	 * 删去某个销售计划子条目
	 * @param item
	 */
	public void deleteScheduleItem(ScheduleDetail item);
	
	/**
	 * 销售计划填写完毕，确认保存
	 * @return
	 */
	public boolean ensureSchedule();
	
	/**
	 * 取消销售计划的制定
	 */
	public void cancelSchedule();
	
	/**
	 * 查看待批准的销售计划
	 * @return
	 */
	public ArrayList<WeekSchedule> retrieveScheduleForApprove();
	
	/**
	 * 批准一个销售计划
	 * @param schedule
	 */
	public void approveSchedule(WeekSchedule schedule);
	
	/**
	 * 某个销售计划未通过批准
	 * @param schedule
	 */
	public void disApproveSchedule(WeekSchedule schedule);
	
}
