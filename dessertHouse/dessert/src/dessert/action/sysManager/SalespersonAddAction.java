package dessert.action.sysManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Salesperson;
import dessert.models.Store;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.remoteService.storeManage.StoreManageService;
import dessert.utility.IDProducer;

@Controller
public class SalespersonAddAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SalespersonManageService salespersonManage;
	@Autowired
	private StoreManageService storeManage;

	public String execute(){
		if(session.get("userId")!=null){
			Salesperson salesperson = new Salesperson();
			salesperson.setSalespersonId(IDProducer.getInstance().produceSalespersonId());
			salesperson.setSalespersonName(String.valueOf(request.getParameter("salespersonName")));
			salesperson.setSalespersonGender(Integer.parseInt(request.getParameter("salespersonGender")));
			salesperson.setSalespersonAge(Integer.parseInt(request.getParameter("salespersonAge")));
			salesperson.setSalespersonLevel(Integer.parseInt(request.getParameter("salespersonType")));
			String storeId = String.valueOf(request.getParameter("salespersonStore"));
			Store store = storeManage.getStoreInfo(storeId);
			salesperson.setStore(store);
			salespersonManage.addSalesperson(salesperson);
			request.setAttribute("newSalesperson", salesperson);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
