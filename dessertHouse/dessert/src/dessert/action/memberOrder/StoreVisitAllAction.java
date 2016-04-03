package dessert.action.memberOrder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.ListBean;
import dessert.models.Store;
import dessert.remoteService.storeManage.StoreManageService;

@Controller
public class StoreVisitAllAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StoreManageService storeManage;
	
	public String execute(){
		ArrayList<Store> storeList = storeManage.getAllStore();
		ListBean storeListBean = new ListBean();
		storeListBean.setListBean(storeList);
		request.setAttribute("storeList", storeListBean);
		return SUCCESS;
	}

}
