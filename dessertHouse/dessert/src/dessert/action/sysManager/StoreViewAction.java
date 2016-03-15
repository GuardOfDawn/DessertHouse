package dessert.action.sysManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.StoreListBean;
import dessert.models.Store;
import dessert.models.StoreUser;
import dessert.service.storeOperation.StoreOpService;

@Controller
public class StoreViewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private StoreOpService storeManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			StoreUser user = (StoreUser)session.get("user");
			if(user.getUserType().equals("SystemManager")){
				ArrayList<Store> storeList = storeManage.getAllStore();
				StoreListBean storeListBean = new StoreListBean();
				storeListBean.setListStore(storeList);
				session.put("storeList", storeListBean);
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
