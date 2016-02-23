package storeOperation;

import java.util.ArrayList;

import models.Store;

public class StoreOperationController implements StoreOpService{
	
	private AddStore addStore;
	private DeleteStore deleteStore;
	private ModifyStore modifyStore;
	private RetrieveStore retrieveStore;
	
	public StoreOperationController(){
		addStore = new AddStore();
		deleteStore = new DeleteStore();
		modifyStore = new ModifyStore();
		retrieveStore = new RetrieveStore();
	}

	public boolean addStore(Store store) {
		return addStore.addStore(store);
	}

	public boolean deleteStore(String storeId) {
		return deleteStore.deleteStore(storeId);
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

	public ArrayList<String> getAllProvince() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllCity(String province) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modifyStore(String storeId,Store modifiedStore){
		return modifyStore.modifyStore(storeId, modifiedStore);
	}

}
