package com.wpi.cs509.teamA.entities;

import com.wpi.cs509.teamA.misc.UserCookies;

public class UserPreference extends UserAccount{

	private UserCookies userCookies;

	public UserCookies getUserCookies() {
		return userCookies;
	}

	public void setUserCookies(UserCookies userCookies) {
		this.userCookies = userCookies;
	}
	
}
