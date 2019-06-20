package com.ai.paas.util.domain;

import java.io.Serializable;

import com.ai.paas.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1404901421429180867L;
	private String userId;

	private String userName;

	public User() {

	}

	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	@JsonProperty("label")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("value")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static void main(String[] args) throws Exception {
		User user = new User("test", "123456");
		System.out.println(JsonUtil.toJson(user));
		String json = "{label:\"test111\",value:\"123456789\"}";

		User user1 = JsonUtil.fromJson(json, User.class);
		System.out.println(user1.getUserId() + "==" + user1.getUserName());
	}
}
