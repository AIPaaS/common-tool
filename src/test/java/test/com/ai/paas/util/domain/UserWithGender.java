package test.com.ai.paas.util.domain;

import java.io.Serializable;

public class UserWithGender implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -691825015751801713L;
	private String userId;
	private String userName;
	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
