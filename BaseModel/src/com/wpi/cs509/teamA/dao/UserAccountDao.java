package com.wpi.cs509.teamA.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.History;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.exception.PwdIncorrectException;
import com.wpi.cs509.teamA.exception.UserAccountNotFoundException;

public interface UserAccountDao {

	/**
	 * get all user accounts in the database
	 * 
	 * @return a list of user accounts
	 * 
	 */
	public List<UserAccount> getAllUserAccounts();	
	
	/**
	 * get all history for a user and return a HashMap including searching string and count
	 * 
	 * @return a HashMap<String, Integer>
	 * 
	 */
	public List<History> getAllHistoryForUser(int user_id);
	
	/**
	 * insert user account into the database
	 * 
	 * @return a HashMap<String, Integer>
	 * 
	 */
	public boolean addAccountToDatabase(UserAccount add_user);
	
	public boolean checkUserNameInDatabase(String username);
	
	public UserAccount loginAuthorization(String username, String password) throws UserAccountNotFoundException,PwdIncorrectException;
	
}