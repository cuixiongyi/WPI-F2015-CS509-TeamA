package com.wpi.cs509.teamA.entities;

// this is the tool to record the map
public class AdminAccount extends UserAccount{
	
	public AdminAccount(){
		
	}
	
	public AdminAccount(int id, String username, String password, int userType){
		
		super(id, username, password, userType);
		
	}
	
	// admin can manage the activity
	public void addActivity(){
		
	}
	
	public void deleteActivity(){
		
	}
	
	public int getId() {
		return this.getId();
	}

	public void setId(int id) {
		this.setId(id);
	}

	public int getUserType() {
		return this.getUserType();
	}

	public void setUserType(int userType) {
		this.setUserType(userType);
	}

	public String getUsername() {
		return this.getUsername();
	}

	public void setUsername(String username) {
		this.setUsername(username);
	}

	public String getPassword() {
		return this.getPassword();
	}

	public void setPassword(String password) {
		this.setPassword(password);
	}


	// call methods from Node class
	
	public void updateNode(String nodeId){
		
	}
	
	public void deleteNode(String nodeId){
		
	}
	
	// save the node to a file?
	public void saveNodeToFile(){
		
	}
	
}
