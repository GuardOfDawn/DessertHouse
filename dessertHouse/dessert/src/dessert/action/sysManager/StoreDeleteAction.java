package dessert.action.sysManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.StoreListBean;
import dessert.models.Store;
import dessert.remoteService.storeManage.StoreManageService;

@Controller
public class StoreDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StoreManageService storeManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String storeId = (String) request.getParameter("storeId");
			boolean delRes = storeManage.deleteStore(storeId);
			request.setAttribute("deleteRes", String.valueOf(delRes));
			request.setAttribute("deleteStoreId", storeId);
			if(delRes){
				Object store = null;
	        	for(Object o:session.keySet()){
	        		if(o.equals("storeList")){
	        			store = o;
	        		}
	        	}
	        	session.remove(store);
	        	ArrayList<Store> storeList = storeManage.getAllStore();
				StoreListBean storeListBean = new StoreListBean();
				storeListBean.setListStore(storeList);
				session.put("storeList", storeListBean);
				return SUCCESS;
			}
			else {
				return "failure";
			}
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}

}
