package dessert.remoteService.storeManage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.SalespersonDao;
import dessert.dao.StoreDao;
import dessert.models.Salesperson;
import dessert.models.Store;

@Service
public class StoreManageController implements StoreManageService{

	@Autowired
	private StoreDao storeDao;
	@Autowired
	SalespersonDao salespersonDao;
	
	public boolean addStore(Store store) {
		return storeDao.saveStore(store);
	}

	public boolean deleteStore(String storeId) {
		Store s = getStoreInfo(storeId);
		if(s!=null){
			String[] columns = {"storeId"};
			String[] values = new String[1];
			values[0] = storeId;
			ArrayList<Salesperson> list = salespersonDao.findSalesperson(columns, values);
			if(list!=null&&list.size()>0){
				return false;
			}
			else{
				return storeDao.deleteStore(s);
			}
		}
		else{
			return false;
		}
	}

	public boolean modifyStore(Store store){
		return storeDao.updateStore(store);
	}

	public ArrayList<Store> getAllStore() {
		return storeDao.findAllStore();
	}

	public Store getStoreInfo(String storeId) {
		String[] columns = {"storeId"};
		String[] values = new String[1];
		values[0] = storeId;
		ArrayList<Store> s = storeDao.findStore(columns, values);
		if(s!=null&&s.size()==1){
			return s.get(0);
		}
		else{
			return null;
		}
	}

	public ArrayList<Store> retrieveStore(String province, String city) {
		if(province!=null){
			if(city!=null){
				String[] columns = {"province","city"};
				String[] values = new String[2];
				values[0] = province;
				values[1] = city;
				return storeDao.findStore(columns, values);
			}
			else{
				String[] columns = {"province"};
				String[] values = new String[1];
				values[0] = province;
				return storeDao.findStore(columns, values);
			}
		}
		else{
			if(city!=null){
				String[] columns = {"city"};
				String[] values = new String[1];
				values[0] = city;
				return storeDao.findStore(columns, values);
			}
			else{
				return getAllStore();
			}
		}
	}

}
