package remoteService;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.Salesperson;

@Remote
public interface SalespersonManageService {

	public boolean addSalesperson(Salesperson salesperson);
	
	public boolean deleteSalesperson(String salespersonId);
	
	public Salesperson getSalespersonInfo(String salespersonId);

	public ArrayList<Salesperson> getAllSalesperson();
	
	public ArrayList<Salesperson> retrieveSalesperson(String storeId);
	
	public boolean modifySalesperson(Salesperson salesperson);
	
}
