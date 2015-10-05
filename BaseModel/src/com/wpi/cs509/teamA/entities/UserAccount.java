package com.wpi.cs509.teamA.entities;

/**
 * user account definition
 * @author CS 509-Team A 
 * @version Oct 5th
*/
public class UserAccount {

	/**account ID */
	private int id;
	/**username of the account*/
	private String username;
	/**pass word of the account*/
	private String password;
	/**user type:admin or regular user */
	int userType;
	
	/**default constructor*/
	public UserAccount(){
		
	}
	
	/**constructor with information
	 * @param id account id
	 * @param username
	 * @param password
	 * @param userType*/
	public UserAccount(int id, String username, String password, int userType){
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	/**id getter*/
	public int getId() {
		return id;
	}
	
	/**id setter*/
	public void setId(int id) {
		this.id = id;
	}
	
	/**userType getter*/
	public int getUserType() {
		return userType;
	}
	
	/**userType setter*/
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	/**username getter*/
	public String getUsername() {
		return username;
	}

	/**user setter*/
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**password getter*/
	public String getPassword() {
		return password;
	}

	/**password setter*/
	public void setPassword(String password) {
		this.password = password;
	}

}
