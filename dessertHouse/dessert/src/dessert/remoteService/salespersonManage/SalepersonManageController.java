package dessert.remoteService.salespersonManage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.SalespersonDao;
import dessert.dao.StoreUserDao;
import dessert.models.Salesperson;
import dessert.models.StoreUser;

@Service
public class SalepersonManageController implements SalespersonManageService{

	@Autowired
	SalespersonDao salespersonDao;
	@Autowired
	StoreUserDao storeUserDao;
	
	private static SalepersonManageController salepersonManageController = new SalepersonManageController();
	
	private SalepersonManageController(){

	}
	
	public static SalepersonManageController getInstance(){
		return salepersonManageController;
	}
	
	public boolean addSalesperson(Salesperson salesperson) {
		//add a user for salesperson
		StoreUser user = new StoreUser();
		user.setUserId(salesperson.getSalespersonId());
		user.setUserName(salesperson.getSalespersonName());
		String userType = (salesperson.getSalespersonLevel()==0)?"HeadAttendent":"BranchAttendent";
		user.setUserType(userType);
		user.setPassword("1234");
		storeUserDao.save(user);
		//add salesperson
		return salespersonDao.saveSalesperson(salesperson);
	}

	public boolean deleteSalesperson(String salespersonId) {
		//delete user the salesperson used
		storeUserDao.deleteUser(salespersonId);
		//delete salesperson
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
		//update user for salesperson
		StoreUser user = storeUserDao.findUser(salesperson.getSalespersonId());
		user.setUserName(salesperson.getSalespersonName());
		String userType = (salesperson.getSalespersonLevel()==0)?"HeadAttendant":"BranchAttendant";
		user.setUserType(userType);
		storeUserDao.updateUser(user);
		//update salesperson
		return salespersonDao.updateSalesperson(salesperson);
	}


}
