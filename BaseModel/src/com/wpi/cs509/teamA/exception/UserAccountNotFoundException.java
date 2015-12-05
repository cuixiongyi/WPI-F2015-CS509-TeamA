package com.wpi.cs509.teamA.exception;

public class UserAccountNotFoundException extends Exception {
	public UserAccountNotFoundException(){
		System.out.println("Cannot find this user in the database");
	}
}
