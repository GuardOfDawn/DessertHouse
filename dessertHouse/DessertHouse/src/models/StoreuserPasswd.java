package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="storeuserpassword")
public class StoreuserPasswd implements Serializable{

	private String Id;
	private String password;
	private StoreUser storeuser;
	

	@Id
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne(mappedBy="passwd")
	public StoreUser getStoreuser() {
		return storeuser;
	}

	public void setStoreuser(StoreUser storeuser) {
		this.storeuser = storeuser;
	}
	
}
