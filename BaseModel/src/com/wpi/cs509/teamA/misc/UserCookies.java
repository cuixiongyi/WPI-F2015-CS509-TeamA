package com.wpi.cs509.teamA.misc;

import java.util.List;
import java.util.Set;

/**
 * user cookie definition
 * @author CS 509-Team A 
 * @version Oct 5th
*/  
public class UserCookies {

	/** User's unique identification string */
	private String userId;
	/** List of previously searched locations */
	private List<String> userHistory;
	/** @todo fill in this variable comment */
	private Set<String> userPrefer;
	
	/**
	 * Gets the user's ID
	 * @return The user's ID
	 */ 
	public String getUserId() {
		return userId;
	}
	
	/** Sets the user's ID
	 * @param userId The user's ID
	 */ 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Gets the user's search history
	 * @return The list of previously searched locations
	 */ 
	public List<String> getUserHistory() {
		return userHistory;
	}
	
	/**
	 * Sets the user's history to a new history list
	 * @ param userHistory New user history
	 */ 
	public void setUserHistory(List<String> userHistory) {
		this.userHistory = userHistory;
	}
	
	/**
	 * @todo: write function header comments
	 */ 
	public Set<String> getUserPrefer() {
		return userPrefer;
	}
	
	/**
	 * @todo: write function header comments
	 */ 
	public void setUserPrefer(Set<String> userPrefer) {
		this.userPrefer = userPrefer;
	}
	
}
