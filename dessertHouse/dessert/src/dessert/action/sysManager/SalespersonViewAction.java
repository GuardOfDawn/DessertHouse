package dessert.action.sysManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.business.SalespersonListBean;
import dessert.models.Salesperson;
import dessert.models.StoreUser;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.remoteService.storeManage.StoreManageService;

@Controller
public class SalespersonViewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SalespersonManageService salespersonManage;
	@Autowired
	private StoreManageService storeManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			StoreUser user = (StoreUser)session.get("user");
			if(user.getUserType().equals("SystemManager")){
				ArrayList<Salesperson> salespersonList = salespersonManage.getAllSalesperson();
				SalespersonListBean salespersonListBean = new SalespersonListBean();
				salespersonListBean.setListSalesperson(salespersonList);
				session.put("salespersonList", salespersonListBean);
				ArrayList<String> storeIdList = storeManage.getAllStoreId();
				ListBean storeIdListBean = new ListBean();
				storeIdListBean.setListBean(storeIdList);
				session.put("storeIdList", storeIdListBean);
				return SUCCESS;
			}
			else{
				//user is not a system manager
				return ERROR;
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
