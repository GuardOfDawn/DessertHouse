package dao;

import java.util.ArrayList;

import javax.ejb.Local;

import models.Salesperson;

@Local
public interface SalespersonDao {

	public boolean saveSalesperson(Salesperson salesperson);
	
	public boolean removeSalesperson(String[] columns,String[] values);
	
	public ArrayList<Salesperson> findSalesperson(String[] columns,String[] values);
	
	public ArrayList<Salesperson> findAllSalesperson();
	
	public boolean updateSalesperson(Salesperson salesperson);
	
}
