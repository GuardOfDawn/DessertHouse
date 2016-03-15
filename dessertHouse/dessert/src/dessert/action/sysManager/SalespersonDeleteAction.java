package dessert.action.sysManager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.business.SalespersonListBean;
import dessert.models.Salesperson;
import dessert.service.salespersonOperation.SalespersonOpService;

@Controller
public class SalespersonDeleteAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SalespersonOpService salespersonManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("userId")!=null){
			String salespersonId = (String)request.getParameter("salespersonId");
			boolean delRes = salespersonManage.deleteSalesperson(salespersonId);
			request.setAttribute("deleteRes", String.valueOf(delRes));
			request.setAttribute("deleteSalespersonId", salespersonId);
			Object salesperson = null;
        	for(Object o:session.keySet()){
        		if(o.equals("salespersonList")){
        			salesperson = o;
        		}
        	}
        	session.remove(salesperson);
        	ArrayList<Salesperson> salespersonList = salespersonManage.getAllSalesperson();
			SalespersonListBean salespersonListBean = new SalespersonListBean();
			salespersonListBean.setListSalesperson(salespersonList);
			session.put("salespersonList", salespersonListBean);
			if(delRes){
				return SUCCESS;
			}
			else {
				return "failure";
			}
		}
		else{
			return INPUT;
		}
	}
	
}
