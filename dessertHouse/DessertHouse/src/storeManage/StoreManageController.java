package storeManage;

import java.util.ArrayList;

import javax.ejb.Stateless;

import models.Store;
import remoteService.StoreManageService;

@Stateless
public class StoreManageController implements StoreManageService{

	private static StoreManageController storeManageController = new StoreManageController();
	private AddStore addStore;
	private DeleteStore deleteStore;
	private ModifyStore modifyStore;
	private RetrieveStore retrieveStore;
	
	private StoreManageController(){
		addStore = AddStore.getInstance();
		deleteStore = DeleteStore.getInstance();
		modifyStore = ModifyStore.getInstance();
		retrieveStore = RetrieveStore.getInstance();
	}
	
	public static StoreManageController getInstance(){
		return storeManageController;
	}
	
	public boolean addStore(Store store) {
		return addStore.addStore(store);
	}

	public boolean deleteStore(String storeId) {
		Store s = retrieveStore.getStoreInfo(storeId);
		if(s!=null){
			return deleteStore.deleteStore(storeId);
		}
		else{
			return false;
		}
	}

	public boolean modifyStore(Store store){
		return modifyStore.modifyStore(store);
	}

	public ArrayList<Store> getAllStore() {
		return retrieveStore.getAllStore();
	}

	public Store getStoreInfo(String storeId) {
		return retrieveStore.getStoreInfo(storeId);
	}

	public ArrayList<Store> retrieveStore(String province, String city) {
		return retrieveStore.retrieveStore(province, city);
	}

}
