package com.wpi.cs509.teamA.bean;

import java.util.HashMap;

/**
 * this class represents all the users, if this user is an admin
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class UserAccount {

	/** account ID */
	private int id;
	/** username of the account */
	private String username;
	/** pass word of the account */
	private String password;
	/** email of the user*/
	private String email;
	/**
	 * if this account is an admin
	 */
	private boolean isAdmin;

	private HashMap<String, Integer> history;
	
	
	/** default constructor */
	public UserAccount() {
		this.id = -1;
		this.username = "Guest User";
		this.email = "";
		this.password = "";
		this.isAdmin = false;

	}

	

	/**
	 * constructor with information
	 * 
	 * @param id
	 *            account id
	 * @param username
	 *            the name of the user
	 * @param password
	 *            user password
	 */
	public UserAccount(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;

	}

	/** id getter */
	public int getId() {
		return id;
	}

	/** id setter */
	public void setId(int id) {
		this.id = id;
	}

	/** username getter */
	public String getUsername() {
		return username;
	}

	/** user setter */
	public void setUsername(String username) {
		this.username = username;
	}

	/** password getter */
	public String getPassword() {
		return password;
	}

	/** password setter */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public HashMap<String, Integer> getHistory() {
		return history;
	}

	public void setHistory(HashMap<String, Integer> history) {
		this.history = history;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
