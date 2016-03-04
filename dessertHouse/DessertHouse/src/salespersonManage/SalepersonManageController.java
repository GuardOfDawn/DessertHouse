package salespersonManage;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.SalespersonDao;
import models.Salesperson;
import models.Store;
import remoteService.SalespersonManageService;

@Stateless
public class SalepersonManageController implements SalespersonManageService{

	@EJB SalespersonDao salespersonDao;
	
	private static SalepersonManageController salepersonManageController = new SalepersonManageController();
	
	private SalepersonManageController(){

	}
	
	public static SalepersonManageController getInstance(){
		return salepersonManageController;
	}
	
	public boolean addSalesperson(Salesperson salesperson) {
		return salespersonDao.saveSalesperson(salesperson);
	}

	public boolean deleteSalesperson(String salespersonId) {
		String[] columns = {"salespersonId"};
		String[] values = new String[1];
		values[0] = salespersonId;
		return salespersonDao.removeSalesperson(columns, values);
	}

	public Salesperson getSalespersonInfo(String salespersonId) {
		String[] columns = {"salespersonId"};
		String[] values = new String[1];
		values[0] = salespersonId;
		ArrayList<Salesperson> s = salespersonDao.findSalesperson(columns, values);
		if(s!=null&&s.size()==1){
			return s.get(0);
		}
		else{
			return null;
		}
	}

	public ArrayList<Salesperson> getAllSalesperson() {
		return salespersonDao.findAllSalesperson();
	}

	public ArrayList<Salesperson> retrieveSalesperson(String storeId) {
		String[] columns = {"storeId"};
		String[] values = new String[1];
		values[0] = storeId;
		ArrayList<Salesperson> s = salespersonDao.findSalesperson(columns, values);
		return s;
	}

	public boolean modifySalesperson(Salesperson salesperson) {
		return salespersonDao.updateSalesperson(salesperson);
	}


}
