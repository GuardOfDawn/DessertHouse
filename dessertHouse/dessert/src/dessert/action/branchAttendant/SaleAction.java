package dessert.action.branchAttendant;

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
import dessert.models.StoreUser;
import dessert.models.WeekSchedule;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.service.productOperation.ProductOpService;
import dessert.utility.DayTransformer;

@Controller
public class SaleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SalespersonManageService salespersonManage;
	@Autowired
	private ScheduleManageService scheduleManage;
	@Autowired
	private ProductOpService productManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		StoreUser user = (StoreUser) session.get("user");
		if(user!=null){
			String salespersonId = user.getUserId();
			Store store = salespersonManage.getSalespersonStore(salespersonId);
			Date curDate = new Date(System.currentTimeMillis());
			curDate = DayTransformer.transform(DayTransformer.transform(curDate));
			ArrayList<WeekSchedule> scheduleList = scheduleManage.retrieveSchedule(store.getStoreId(), curDate, curDate);
			ArrayList<ProductExtend> sellingProductList = new ArrayList<ProductExtend>();
			if(scheduleList!=null&&scheduleList.size()>0){
				for(WeekSchedule schedule:scheduleList){
					ArrayList<ScheduleDetail> temp = scheduleManage.retrieveScheduleDetail(schedule.getScheduleId());
					for(ScheduleDetail item:temp){
						Date d = item.getScheduleDate();
						if(DayTransformer.transform(d).equals(DayTransformer.transform(curDate))){
							Product p = productManage.getProductInfo(item.getProductId());
							ProductExtend productItem = new ProductExtend();
							productItem.setProductId(p.getProductId());
							productItem.setProductName(p.getProductName());
							productItem.setProductType(p.getProductType());
							productItem.setImagePath(p.getImagePath());
							productItem.setSellingPrice(item.getSellingPrice());
							productItem.setSellingCount(item.getSellingCount());
							sellingProductList.add(productItem);
						}
					}
				}
			}
			ListBean sellingProductListBean = new ListBean();
			sellingProductListBean.setListBean(sellingProductList);
			session.put("store", store);
			session.put("sellingProductToday", sellingProductListBean);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
