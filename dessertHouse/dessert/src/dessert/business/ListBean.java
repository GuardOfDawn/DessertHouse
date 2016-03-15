package dessert.business;

import java.io.Serializable;
import java.util.List;

public class ListBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List listBean;

	@SuppressWarnings("rawtypes")
	public List getListBean() {
		return listBean;
	}

	@SuppressWarnings("rawtypes")
	public void setListBean(List listBean) {
		this.listBean = listBean;
	}
	
	public Object getBean(int index){
		return listBean.get(index);
	}

}
