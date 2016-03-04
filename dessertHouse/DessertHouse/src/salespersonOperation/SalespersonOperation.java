package salespersonOperation;

import java.util.ArrayList;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import models.Salesperson;
import models.Store;
import remoteService.SalespersonManageService;
import remoteService.StoreManageService;
import service.SalespersonOpService;

@Service
public class SalespersonOperation implements SalespersonOpService{

	@EJB SalespersonManageService salespersonManage;
	@EJB StoreManageService storeManage;
	
	public SalespersonOperation(){
		
	}

	public boolean addSalesperson(Salesperson salesperson) {
		return salespersonManage.addSalesperson(salesperson);
	}

	public boolean deleteSalesperson(String salespersonId) {
		return salespersonManage.deleteSalesperson(salespersonId);
	}

	public boolean modifySalesperson(String salespersonId, Salesperson salesperson) {
		Salesperson person = salespersonManage.getSalespersonInfo(salespersonId);
		if(person!=null){
			person.setSalespersonName(salesperson.getSalespersonName());
			person.setSalespersonAge(salesperson.getSalespersonAge());
			person.setSalespersonLevel(salesperson.getSalespersonLevel());
			if(!person.getStore().getStoreId().equals(salesperson.getStore().getStoreId())){
				Store store = storeManage.getStoreInfo(salesperson.getStore().getStoreId());
				person.setStore(store);
			}
			return salespersonManage.modifySalesperson(person);
		}
		else{
			return false;
		}
	}

	public Salesperson getSalespersonInfo(String salespersonId) {
		return salespersonManage.getSalespersonInfo(salespersonId);
	}

	@Override
	public ArrayList<Salesperson> getAllSalesperson(String salespersonId) {
		return salespersonManage.getAllSalesperson();
	}

	public ArrayList<Salesperson> retrieveSalesperson(String storeId) {
		return salespersonManage.retrieveSalesperson(storeId);
	}
	
}
