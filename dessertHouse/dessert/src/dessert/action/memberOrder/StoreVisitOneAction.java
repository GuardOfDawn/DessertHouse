package dessert.action.memberOrder;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.business.ProductExtend;
import dessert.models.Product;
import dessert.models.ScheduleDetail;
import dessert.models.Store;
import dessert.models.WeekSchedule;
import dessert.remoteService.productManage.ProductManageService;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.remoteService.storeManage.StoreManageService;
import dessert.utility.DayTransformer;

@Controller
public class StoreVisitOneAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StoreManageService storeManage;
	@Autowired
	private ScheduleManageService scheduleManage;
	@Autowired
	private ProductManageService productManage;
	
	public String execute(){
		String storeId = request.getParameter("storeId");
		Store store = storeManage.getStoreInfo(storeId);
		if(store!=null){
			String targetDateString = request.getParameter("targetDate");
			Date targetDate = null;
			if(targetDateString==null||targetDateString.equals("null")){
				targetDate = new Date(System.currentTimeMillis());
				targetDate = DayTransformer.transform(DayTransformer.transform(targetDate));
			}
			else{
				targetDate = DayTransformer.transform(targetDateString);
			}
			ArrayList<WeekSchedule> scheduleList = scheduleManage.retrieveSchedule(storeId, targetDate, targetDate);
			ArrayList<ProductExtend> sellingProductList = new ArrayList<ProductExtend>();
			if(scheduleList!=null&&scheduleList.size()>0){
				for(WeekSchedule schedule:scheduleList){
					ArrayList<ScheduleDetail> temp = scheduleManage.retrieveScheduleDetail(schedule.getScheduleId());
					for(ScheduleDetail item:temp){
						Date d = item.getScheduleDate();
						if(DayTransformer.transform(d).equals(DayTransformer.transform(targetDate))){
							Product p = productManage.findProduct(item.getProductId());
							ProductExtend productItem = new ProductExtend();
							productItem.setProductId(p.getProductId());
							productItem.setProductName(p.getProductName());
							productItem.setProductType(p.getProductType());
							productItem.setImagePath(p.getImagePath());
							productItem.setSellingPrice(item.getSellingPrice());
							productItem.setRemainingCount(item.getRemainingCount());
							sellingProductList.add(productItem);
						}
					}
				}
			}
			ListBean sellingProductListBean = new ListBean();
			sellingProductListBean.setListBean(sellingProductList);
			request.setAttribute("targetDate", targetDateString);
			request.setAttribute("store", store);
			request.setAttribute("sellingProductToday", sellingProductListBean);
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
}
