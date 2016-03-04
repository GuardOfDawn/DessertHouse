package saleSchedule;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;

import models.ScheduleDetail;
import models.Store;
import models.WeekSchedule;
import remoteService.ScheduleManageService;

public class MakeSaleSchedule {
	
	@EJB ScheduleManageService scheduleManage;
	
	private WeekSchedule schedule;
	
	public MakeSaleSchedule(){
		schedule = new WeekSchedule();
	}
	
	public void setStore(Store store){
		schedule.setStore(store);
	}
	
	public void setStartTime(Date startTime){
		schedule.setStartTime(startTime);
	}
	
	public void setEndTime(Date endTime){
		schedule.setEndTime(endTime);
	}
	
	public void addScheduleItem(ScheduleDetail item) {
		Set<ScheduleDetail> itemList = schedule.getScheduleDetailList();
		if(itemList==null){
			itemList = new HashSet<ScheduleDetail>();
		}
		itemList.add(item);
		schedule.setScheduleDetailList(itemList);
	}
	
	public void deleteScheduleItem(ScheduleDetail item){
		Set<ScheduleDetail> itemList = schedule.getScheduleDetailList();
		ScheduleDetail forRemove = null;
		for(ScheduleDetail detail:itemList){
			if(detail.getProductId().equals(item.getProductId())){
				forRemove = detail;
				break;
			}
		}
		if(forRemove!=null){
			itemList.remove(forRemove);
			schedule.setScheduleDetailList(itemList);
		}
	}
	
	public boolean ensureSchedule(){
		int forApprove = 0;
		schedule.setScheduleState(forApprove);
		//TODO 发送给经理批准
		
		
		return scheduleManage.addSchedule(schedule);
	}
	
	public void cancelSchedule(){
		schedule = null;
	}
	
}
