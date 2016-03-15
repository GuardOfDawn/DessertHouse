package dessert.service.salespersonOperation;

import java.util.ArrayList;

import dessert.models.Salesperson;

public interface SalespersonOpService {

	public boolean addSalesperson(Salesperson salesperson);
	
	public boolean deleteSalesperson(String salespersonId);
	
	public boolean modifySalesperson(Salesperson salesperson);
	
	public Salesperson getSalespersonInfo(String salespersonId);
	
	public ArrayList<Salesperson> getAllSalesperson();
	
	public ArrayList<Salesperson> retrieveSalesperson(String storeId);
	
}
