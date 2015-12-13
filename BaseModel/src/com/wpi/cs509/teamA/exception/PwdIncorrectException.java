package com.wpi.cs509.teamA.exception;

public class PwdIncorrectException extends Exception {
	public PwdIncorrectException() {
		// TODO: this should feedback to the UI
		System.out.println("Password incorrect, please try again~");
	}
}
