package models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="saleperson")
public class Salesperson implements Serializable{
	
	private String salespersonId;
	private String salespersonName;
	private int salespersonAge;
	private int salespersonLevel;
	private Store store;
	
	
	@Id
	public String getSalespersonId() {
		return salespersonId;
	}
	public void setSalespersonId(String salespersonId) {
		this.salespersonId = salespersonId;
	}
	
	@Column
	public String getSalespersonName() {
		return salespersonName;
	}
	public void setSalespersonName(String salespersonName) {
		this.salespersonName = salespersonName;
	}
	
	@Column
	public int getSalespersonAge() {
		return salespersonAge;
	}
	public void setSalespersonAge(int salespersonAge) {
		this.salespersonAge = salespersonAge;
	}
	
	@Column
	public int getSalespersonLevel() {
		return salespersonLevel;
	}
	public void setSalespersonLevel(int salespersonLevel) {
		this.salespersonLevel = salespersonLevel;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="storeId")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}

}
