package dessert.service.salespersonOperation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.models.Salesperson;
import dessert.remoteService.salespersonManage.SalespersonManageService;
import dessert.remoteService.storeManage.StoreManageService;

@Service
public class SalespersonOperation implements SalespersonOpService{

	@Autowired 
	SalespersonManageService salespersonManage;

	@Autowired 
	StoreManageService storeManage;
	
	public SalespersonOperation(){
		
	}

	public boolean addSalesperson(Salesperson salesperson) {
		return salespersonManage.addSalesperson(salesperson);
	}

	public boolean deleteSalesperson(String salespersonId) {
		return salespersonManage.deleteSalesperson(salespersonId);
	}

	public boolean modifySalesperson(Salesperson salesperson) {
		return salespersonManage.modifySalesperson(salesperson);
	}

	public Salesperson getSalespersonInfo(String salespersonId) {
		return salespersonManage.getSalespersonInfo(salespersonId);
	}

	@Override
	public ArrayList<Salesperson> getAllSalesperson() {
		return salespersonManage.getAllSalesperson();
	}

	public ArrayList<Salesperson> retrieveSalesperson(String storeId) {
		return salespersonManage.retrieveSalesperson(storeId);
	}
	
}
