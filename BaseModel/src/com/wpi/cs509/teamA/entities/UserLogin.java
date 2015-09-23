package com.wpi.cs509.teamA.entities;

public class UserLogin {
	
	private String userId;
	private int UserType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return UserType;
	}
	public void setUserType(int userType) {
		UserType = userType;
	}

}
