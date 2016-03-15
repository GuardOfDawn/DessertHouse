package dessert.business;

import java.io.Serializable;
import java.util.List;

import dessert.models.Salesperson;

public class SalespersonListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List listSalesperson;

	@SuppressWarnings("rawtypes")
	public List getListSalesperson() {
		return listSalesperson;
	}

	@SuppressWarnings("rawtypes")
	public void setListSalesperson(List listSalesperson) {
		this.listSalesperson = listSalesperson;
	}
	
	public Salesperson getSalesperson(int index){
		return (Salesperson) listSalesperson.get(index);
	}

}
