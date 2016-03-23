package dessert.action.branchAttendant;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Bill;
import dessert.models.Store;
import dessert.models.StoreUser;
import dessert.remoteService.memberManage.MemberManageService;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.utility.DayTransformer;

@Controller
public class BillCheckAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberManageService memberManage;
	@Autowired
	private SalespersonManageService salespersonManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			Store store = (Store) session.get("store");
			if(store==null){
				store = salespersonManage.getSalespersonStore(((StoreUser) session.get("user")).getUserId());
				session.put("store", store);
			}
			String dateString = request.getParameter("dateInput");
			Date date;
			if(dateString!=null){
				date = DayTransformer.transform(dateString);
			}
			else{
				date = new Date(System.currentTimeMillis());
			}
			ArrayList<Bill> billList = memberManage.checkBill(store.getStoreId(),date);
			ListBean billListBean = new ListBean();
			billListBean.setListBean(billList);
			request.setAttribute("billList", billListBean);
			request.setAttribute("date", dateString);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
