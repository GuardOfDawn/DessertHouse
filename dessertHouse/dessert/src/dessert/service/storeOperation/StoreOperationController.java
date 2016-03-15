package dessert.service.storeOperation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.models.Store;
import dessert.remoteService.storeManage.StoreManageService;

@Service
public class StoreOperationController implements StoreOpService{

	@Autowired
	private StoreManageService storeManage;
	
	public StoreOperationController(){
		
	}

	public boolean addStore(Store store) {
		return storeManage.addStore(store);
	}

	public boolean deleteStore(String storeId) {
		return storeManage.deleteStore(storeId);
	}

	public ArrayList<Store> getAllStore() {
		return storeManage.getAllStore();
	}

	public ArrayList<String> getAllStoreId(){
		ArrayList<Store> storeList = storeManage.getAllStore();
		ArrayList<String> storeIdList = new ArrayList<String>();
		for(Store s:storeList){
			storeIdList.add(s.getStoreId());
		}
		return storeIdList;
	}

	public Store getStoreInfo(String storeId) {
		return storeManage.getStoreInfo(storeId);
	}

	public ArrayList<Store> retrieveStore(String province, String city) {
		return storeManage.retrieveStore(province, city);
	}

	public ArrayList<String> getAllProvince() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllCity(String province) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modifyStore(Store modifiedStore){
		return storeManage.modifyStore(modifiedStore);
	}

}
