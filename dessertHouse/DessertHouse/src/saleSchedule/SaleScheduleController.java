package saleSchedule;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import models.ScheduleDetail;
import models.Store;
import models.WeekSchedule;
import service.SaleScheduleService;

@Service
public class SaleScheduleController implements SaleScheduleService{
	
	private MakeSaleSchedule makeSchedule;
	private RetrieveSaleSchedule retrieveSchedule = new RetrieveSaleSchedule();
	private ApproveSaleSchedule approveSchedule = new ApproveSaleSchedule();

	
	public ArrayList<WeekSchedule> retrieveWeekSchedule(String storeId, Date startTime, Date endTime) {
		return retrieveSchedule.retrieveWeekSchedule(storeId, startTime, endTime);
	}

	public WeekSchedule getWeekSchedule(String scheduleId){
		return retrieveSchedule.getWeekSchedule(scheduleId);
	}

	public void startSchedule(Store store) {
		makeSchedule = new MakeSaleSchedule();
		makeSchedule.setStore(store);
	}

	public void setStartTime(Date startTime){
		makeSchedule.setStartTime(startTime);
	}
	
	public void setEndTime(Date endTime){
		makeSchedule.setEndTime(endTime);
	}
	
	public void addScheduleItem(ScheduleDetail item) {
		makeSchedule.addScheduleItem(item);
	}

	public void deleteScheduleItem(ScheduleDetail item){
		makeSchedule.deleteScheduleItem(item);
	}

	public boolean ensureSchedule() {
		return makeSchedule.ensureSchedule();
	}

	public void cancelSchedule() {
		makeSchedule.cancelSchedule();
	}
	
	public ArrayList<WeekSchedule> retrieveScheduleForApprove(){
		return retrieveSchedule.retrieveScheduleForApprove();
	}
	
	public void approveSchedule(WeekSchedule schedule){
		approveSchedule.approveSchedule(schedule);
	}
	
	public void disApproveSchedule(WeekSchedule schedule){
		approveSchedule.disApproveSchedule(schedule);
	}
	
}
