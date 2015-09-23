package com.wpi.cs509.teamA.misc;

import java.util.List;
import java.util.Set;

public class UserCookies {

	private String userId;
	private List<String> userHistory;
	private Set<String> userPrefer;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getUserHistory() {
		return userHistory;
	}
	public void setUserHistory(List<String> userHistory) {
		this.userHistory = userHistory;
	}
	public Set<String> getUserPrefer() {
		return userPrefer;
	}
	public void setUserPrefer(Set<String> userPrefer) {
		this.userPrefer = userPrefer;
	}
	
}
