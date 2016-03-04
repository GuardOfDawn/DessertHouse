package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import utility.StoreuserType;

@SuppressWarnings("serial")
@Entity
@Table(name="storeuser")
public class StoreUser implements Serializable{
	
	private String userId;
	private String userName;
	private StoreuserType userType;
	private String lastLogoutTime;
	private StoreuserPasswd passwd;
	

	@Id
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column
	public StoreuserType getUserType() {
		return userType;
	}
	public void setUserType(StoreuserType userType) {
		this.userType = userType;
	}
	
	@Column
	public String getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(String lastLoginTime) {
		this.lastLogoutTime = lastLoginTime;
	}
	
	@OneToOne
	@JoinColumn(name="userId",insertable=true,unique=true)
	public StoreuserPasswd getPasswd() {
		return passwd;
	}
	public void setPasswd(StoreuserPasswd passwd) {
		this.passwd = passwd;
	}

}
