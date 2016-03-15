package dessert.remoteService.salespersonManage;

import java.util.ArrayList;

import dessert.models.Salesperson;

public interface SalespersonManageService {

	public boolean addSalesperson(Salesperson salesperson);
	
	public boolean deleteSalesperson(String salespersonId);
	
	public Salesperson getSalespersonInfo(String salespersonId);

	public ArrayList<Salesperson> getAllSalesperson();
	
	public ArrayList<Salesperson> retrieveSalesperson(String storeId);
	
	public boolean modifySalesperson(Salesperson salesperson);
	
}
