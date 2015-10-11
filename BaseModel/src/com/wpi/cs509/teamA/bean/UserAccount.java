package com.wpi.cs509.teamA.bean;

/**
 * user account definition
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

	/** default constructor */
	public UserAccount() {

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

}
