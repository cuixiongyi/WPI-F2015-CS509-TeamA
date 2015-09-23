package com.wpi.cs509.teamA.entities;

public class UserAccount {

	private int id;
	private String username;
	private String password;
	private int userType;
	
	public UserAccount(){
		
	}
	
	public UserAccount(int id, String username, String password, int userType){
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
