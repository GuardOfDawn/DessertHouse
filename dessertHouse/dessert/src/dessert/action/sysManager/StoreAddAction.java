package dessert.action.sysManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Store;
import dessert.remoteService.storeManage.StoreManageService;
import dessert.utility.IDProducer;

@Controller
public class StoreAddAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StoreManageService storeManage;

	private Store store;
	
	public String execute(){
		if(session.get("userId")!=null){
			Store newStore = new Store();
			newStore.setStoreId(IDProducer.getInstance().produceStoreId());
			newStore.setStoreName(String.valueOf(request.getParameter("storeName")));
			newStore.setStoreTel(String.valueOf(request.getParameter("storeTel")));
			newStore.setProvince(String.valueOf(request.getParameter("province")));
			newStore.setCity(String.valueOf(request.getParameter("city")));
			newStore.setStoreLoc(String.valueOf(request.getParameter("storeLoc")));
			String imageName = (request.getParameter("imagePath")==null)?"default.jpg":request.getParameter("imagePath");
			String imagePath = "/upload/" + imageName;
			newStore.setImagePath(imagePath);
			storeManage.addStore(newStore);
			request.setAttribute("newStore", newStore);
			return SUCCESS;
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}
	
	public void setStore(Store store){
		this.store = store;
	}
	
	public Store getStore(){
		return this.store;
	}
}
