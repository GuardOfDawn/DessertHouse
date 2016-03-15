package dessert.business;

import java.io.Serializable;
import java.util.List;

import dessert.models.Store;

public class StoreListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List listStore;

	@SuppressWarnings("rawtypes")
	public List getListStore() {
		return listStore;
	}

	@SuppressWarnings("rawtypes")
	public void setListStore(List listStore) {
		this.listStore = listStore;
	}
	
	public Store getStore(int index){
		return (Store) listStore.get(index);
	}
	
}
