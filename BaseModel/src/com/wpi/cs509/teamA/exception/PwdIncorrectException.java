package com.wpi.cs509.teamA.exception;

public class PwdIncorrectException extends Exception {
	public PwdIncorrectException(){
		System.out.println("Password incorrect, please try again~");
	}
}
