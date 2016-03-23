package dessert.action.headAttendant;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.ScheduleDetail;
import dessert.models.Store;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.service.storeOperation.StoreOpService;
import dessert.utility.DayTransformer;
import dessert.utility.IDProducer;

@Controller
public class ScheduleAddAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StoreOpService storeManage;
	
	@Autowired
	private ScheduleManageService scheduleManage;

	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			WeekSchedule weekSchedule = new WeekSchedule();
			weekSchedule.setScheduleId(IDProducer.getInstance().produceScheduleId());
			String storeId = request.getParameter("scheduleStoreId");
			Store store = storeManage.getStoreInfo(storeId);
			weekSchedule.setStore(store);
			String startTime = request.getParameter("scheduleStartTime");
			Date startDate = DayTransformer.transform(startTime);
			weekSchedule.setStartTime(startDate);
			Date endDate = DayTransformer.getNewDate(startDate, 6);
			weekSchedule.setEndTime(endDate);
			int forApprove = 0;
			weekSchedule.setScheduleState(forApprove);
			scheduleManage.addSchedule(weekSchedule);
			//set detail
			String scheduleString = request.getParameter("newschedule");
			String[] scheduleArray = scheduleString.split(";");
			for(String schedule:scheduleArray){
				String[] scheduleDetail = schedule.split(",");
				ScheduleDetail detail = new ScheduleDetail();
				int day = Integer.parseInt(scheduleDetail[0]);
				Date date = DayTransformer.getNewDate(startDate, day);
				detail.setScheduleDetailId(IDProducer.getInstance().produceScheduleDetailId());
				detail.setScheduleDate(date);
				detail.setProductId(scheduleDetail[1]);
				detail.setSellingPrice(Double.parseDouble(scheduleDetail[2]));
				detail.setSellingCount(Integer.parseInt(scheduleDetail[3]));
				detail.setWeekSchedule(weekSchedule);
				scheduleManage.addScheduleDetail(detail);
			}
			ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
			ListBean undealtScheduleBean = new ListBean();
			undealtScheduleBean.setListBean(undealtScheduleList);
			session.put("undealtSchedule", undealtScheduleBean);
			return SUCCESS;
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
