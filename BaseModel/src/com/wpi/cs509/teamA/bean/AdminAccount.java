package com.wpi.cs509.teamA.bean;

/**
 * tool to record the map
 * 
 * @version Oct 4th
 * @author CS509-Team A
 */
public class AdminAccount extends UserAccount {

	/**
	 * default constructor
	 * 
	 * @author CS509-Team A
	 */
	public AdminAccount() {

	}

	/**
	 * constructor
	 * 
	 * @param id
	 *            id of the user
	 * @param username
	 *            username of the user
	 * @param password
	 *            password of the user
	 * @author CS509-Team A
	 */
	public AdminAccount(int id, String username, String password) {

		super(id, username, password);

	}

	/**
	 * get id of the user
	 * 
	 * @return id of the user
	 * @author CS509-Team A
	 */
	public int getId() {
		return this.getId();
	}

	/**
	 * set id of the user
	 * 
	 * @param id
	 *            of the user
	 * @author CS509-Team A
	 */
	public void setId(int id) {
		this.setId(id);
	}

	/**
	 * get username of the user
	 * 
	 * @return username of the user
	 * @author CS509-Team A
	 */
	public String getUsername() {
		return this.getUsername();
	}

	/**
	 * set username of the user
	 * 
	 * @param username
	 *            of the user
	 * @author CS509-Team A
	 */
	public void setUsername(String username) {
		this.setUsername(username);
	}

	/**
	 * get password of the user
	 * 
	 * @return password of the user
	 * @author CS509-Team A
	 */
	public String getPassword() {
		return this.getPassword();
	}

	/**
	 * set password of the user
	 * 
	 * @param password
	 *            of the user
	 * @author CS509-Team A
	 */
	public void setPassword(String password) {
		this.setPassword(password);
	}

}
