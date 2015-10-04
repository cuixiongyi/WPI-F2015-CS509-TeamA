package com.wpi.cs509.teamA.entities;

/**
 * tool to record the map
 * @version Oct 4th
 * @author CS509-Team A
*/
public class AdminAccount extends UserAccount{
	
	/**
	 * default constructor
	 * @author CS509-Team A
	 */
	public AdminAccount(){
		
	}
	
	/**
	 * constructor
	 * @param id id of the user
	 * @param username username of the user
	 * @param password password of the user
	 * @param userType type of the user
	 * @author CS509-Team A
	 */
	public AdminAccount(int id, String username, String password, int userType){
		
		super(id, username, password, userType);
		
	}
	
	/**
	 * Add activity to one location
	 * @author CS509-Team A
	 */
	public void addActivity(){
		
	}
	
	/**
	 * delete an activity of one location
	 * @author CS509-Team A
	 */
	public void deleteActivity(){
		
	}
	
	/**
	 * get id of the user
	 * @return id of the user
	 * @author CS509-Team A
	 */
	public int getId() {
		return this.getId();
	}

	/**
	 * set id of the user
	 * @param id of the user
	 * @author CS509-Team A
	 */
	public void setId(int id) {
		this.setId(id);
	}

	/**
	 * get type of the user
	 * @return type of the user
	 * @author CS509-Team A
	 */
	public int getUserType() {
		return this.getUserType();
	}

	/**
	 * set type of the user
	 * @param type of the user
	 * @author CS509-Team A
	 */
	public void setUserType(int userType) {
		this.setUserType(userType);
	}

	/**
	 * get username of the user
	 * @return username of the user
	 * @author CS509-Team A
	 */
	public String getUsername() {
		return this.getUsername();
	}

	/**
	 * set username of the user
	 * @param username of the user
	 * @author CS509-Team A
	 */
	public void setUsername(String username) {
		this.setUsername(username);
	}

	/**
	 * get password of the user
	 * @return password of the user
	 * @author CS509-Team A
	 */
	public String getPassword() {
		return this.getPassword();
	}

	/**
	 * set password of the user
	 * @param password of the user
	 * @author CS509-Team A
	 */
	public void setPassword(String password) {
		this.setPassword(password);
	}

	
	/**
	 * call methods from Node class, update nodes
	 * @param nodeId id of the node that needs update
	 * @author CS509-Team A
	 */
	public void updateNode(String nodeId){
		
	}
	
	/**
	 * call methods from Node class, delete nodes
	 * @param nodeId id of the node that needs delete
	 * @author CS509-Team A
	 */
	public void deleteNode(String nodeId){
		
	}
	
	/**
	 * save all nodes to a file
	 * @author CS509-Team A
	 */
	public void saveNodeToFile(){
		
	}
	
}
