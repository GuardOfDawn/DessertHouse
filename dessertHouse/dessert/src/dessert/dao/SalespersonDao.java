package dessert.dao;

import java.util.ArrayList;

import dessert.models.Salesperson;
import dessert.models.Store;

public interface SalespersonDao {

	public boolean saveSalesperson(Salesperson salesperson);
	
	public boolean removeSalesperson(String[] columns,String[] values);
	
	public Salesperson findSalesperson(String salespersonId);
	
	public ArrayList<Salesperson> findSalesperson(String[] columns,String[] values);

	public Store findSalesperosnStore(String salespersonId);
	
	public ArrayList<Salesperson> findAllSalesperson();
	
	public boolean updateSalesperson(Salesperson salesperson);
	
}
