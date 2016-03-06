package com.wpi.cs509.teamA.persistence.dao;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.exception.PwdIncorrectException;
import com.wpi.cs509.teamA.exception.UserAccountNotFoundException;
import com.wpi.cs509.teamA.persistence.bean.History;
import com.wpi.cs509.teamA.persistence.bean.UserAccount;

public interface UserAccountDao {

	/**
	 * get all user accounts in the database
	 * 
	 * @return a list of user accounts
	 * 
	 */
	public List<UserAccount> getAllUserAccounts();

	/**
	 * get all history for a user and return a HashMap including searching
	 * string and count
	 * 
	 * @return a HashMap<String, Integer>
	 * 
	 */
	public List<History> getAllHistoryForUser(int user_id);

	/**
	 * save all user history back to database
	 * that includes delete all existing searching data for this user 
	 * and also insert all searching data in
	 * 
	 * @param user
	 */
	public void saveSearchHistoryToDatabase(UserAccount user);
	
	/**
	 * insert user account into the database
	 * 
	 * @return a HashMap<String, Integer>
	 * 
	 */
	
	public boolean addAccountToDatabase(UserAccount add_user);

	/**
	 * 
	 * @param username
	 * @return true if user exists, false otherwise
	 */
	public boolean checkUserNameInDatabase(String username);

	/**
	 * 
	 * TODO: Do not throw exception here
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UserAccountNotFoundException
	 * @throws PwdIncorrectException
	 */
	public UserAccount loginAuthorization(String username, String password)
			throws UserAccountNotFoundException, PwdIncorrectException;

}