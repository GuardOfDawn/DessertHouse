package dessert.dao;

import java.util.ArrayList;

import dessert.models.Salesperson;

public interface SalespersonDao {

	public boolean saveSalesperson(Salesperson salesperson);
	
	public boolean removeSalesperson(String[] columns,String[] values);
	
	public Salesperson findSalesperson(String salespersonId);
	
	public ArrayList<Salesperson> findSalesperson(String[] columns,String[] values);
	
	public ArrayList<Salesperson> findAllSalesperson();
	
	public boolean updateSalesperson(Salesperson salesperson);
	
}
