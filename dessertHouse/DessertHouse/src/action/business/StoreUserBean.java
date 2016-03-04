package action.business;

import utility.StoreuserType;

public class StoreUserBean {

	private String userId;
	private String userName;
	private StoreuserType userType;
	private String lastLogoutTime;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public StoreuserType getUserType() {
		return userType;
	}
	public void setUserType(StoreuserType userType) {
		this.userType = userType;
	}
	public String getLastLogoutTime() {
		return lastLogoutTime;
	}
	public void setLastLogoutTime(String lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	
}
