package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.misc.UserCookies;
/**
 * Details of user preference, get from cookies
 * @author CS 509-Team A 
 * @version Oct 5th
*/

public class UserPreference extends UserAccount{
	
	/**the user cookies*/
	private UserCookies userCookies;

	/**user cookies getter*/
	public UserCookies getUserCookies() {
		return userCookies;
	}

	/**user cookies setter*/
	public void setUserCookies(UserCookies userCookies) {
		this.userCookies = userCookies;
	}
	
}
