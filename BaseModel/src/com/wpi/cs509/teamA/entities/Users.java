package com.wpi.cs509.teamA.entities;

import com.wpi.cs509.teamA.misc.UserCookies;

public class Users extends UserLogin{

	private UserCookies userCookies;

	public UserCookies getUserCookies() {
		return userCookies;
	}

	public void setUserCookies(UserCookies userCookies) {
		this.userCookies = userCookies;
	}
	
}
