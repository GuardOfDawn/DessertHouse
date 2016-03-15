package dessert.action.headAttendant;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Product;
import dessert.models.Store;
import dessert.models.StoreUser;
import dessert.models.WeekSchedule;
import dessert.remoteService.scheduleManage.ScheduleManageService;
import dessert.service.productOperation.ProductOpService;
import dessert.service.storeOperation.StoreOpService;

@Controller
public class CurrentScheduleViewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductOpService productManage;
	@Autowired
	private StoreOpService storeManage;
	@Autowired
	private ScheduleManageService scheduleManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			StoreUser user = (StoreUser)session.get("user");
			if(user.getUserType().equals("HeadAttendant")){
				//get product list from database
				if(session.get("productList")==null){
					ArrayList<Product> productList = productManage.getAllProduct();
					ListBean productListBean = new ListBean();
					productListBean.setListBean(productList);
					session.put("productList", productListBean);
				}
				//get storeId list from database
				if(session.get("storeList")==null){
					ArrayList<Store> storeList = storeManage.getAllStore();
					ListBean storeListBean = new ListBean();
					storeListBean.setListBean(storeList);
					session.put("storeList", storeListBean);
				}
				//get schedule
				ArrayList<WeekSchedule> undealtScheduleList = scheduleManage.retrieveScheduleForApprove();
				ArrayList<WeekSchedule> disapprovedScheduleList = scheduleManage.retrieveDisapprovedSchedule();
				ListBean undealtScheduleBean = new ListBean();
				undealtScheduleBean.setListBean(undealtScheduleList);
				ListBean disapprovedScheduleBean = new ListBean();
				disapprovedScheduleBean.setListBean(disapprovedScheduleList);
				session.put("undealtSchedule", undealtScheduleBean);
				session.put("disapprovedSchedule", disapprovedScheduleBean);
				return SUCCESS;
			}
			else{
				return ERROR;
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}
	
}
