package models;

public class Salesperson {
	
	private String salespersonId;
	private String salespersonName;
	private int salespersonAge;
	private int salespersonLevel;
	private String storeId;
	
	
	public String getSalespersonId() {
		return salespersonId;
	}
	public void setSalespersonId(String salespersonId) {
		this.salespersonId = salespersonId;
	}
	public String getSalespersonName() {
		return salespersonName;
	}
	public void setSalespersonName(String salespersonName) {
		this.salespersonName = salespersonName;
	}
	public int getSalespersonAge() {
		return salespersonAge;
	}
	public void setSalespersonAge(int salespersonAge) {
		this.salespersonAge = salespersonAge;
	}
	public int getSalespersonLevel() {
		return salespersonLevel;
	}
	public void setSalespersonLevel(int salespersonLevel) {
		this.salespersonLevel = salespersonLevel;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
