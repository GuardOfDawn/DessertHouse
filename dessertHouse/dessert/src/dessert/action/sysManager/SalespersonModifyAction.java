package dessert.action.sysManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.models.Salesperson;
import dessert.models.Store;
import dessert.service.salespersonOperation.SalespersonOpService;
import dessert.service.storeOperation.StoreOpService;

@Controller
public class SalespersonModifyAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SalespersonOpService salespersonManage;
	@Autowired
	private StoreOpService storeManage;
	
	public String execute(){
		if(session.get("userId")!=null){
			String modifiedId = request.getParameter("modifiedId");
			Salesperson salesperson = salespersonManage.getSalespersonInfo(modifiedId);
			salesperson.setSalespersonAge(Integer.parseInt(request.getParameter("salespersonAge")));
			salesperson.setSalespersonLevel(Integer.parseInt(request.getParameter("salespersonType")));
			String storeId = String.valueOf(request.getParameter("salespersonStore"));
			if(!storeId.equals(salesperson.getStore().getStoreId())){
				Store store = storeManage.getStoreInfo(storeId);
				salesperson.setStore(store);
			}
			salespersonManage.modifySalesperson(salesperson);
			request.setAttribute("modifiedSalesperson", salesperson);
			return SUCCESS;
		}
		else{
			return INPUT;
		}
	}

}
