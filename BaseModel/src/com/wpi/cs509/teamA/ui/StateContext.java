package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * Instead of using a lot of if and else statements to capture the state of an
 * object, a state machine design pattern is a better solution that’s more
 * powerful and expandable. The state of the control class is tracked by the
 * state machine and the user input will cause a state change, with all the
 * related parameters stored in the control class. States changes will occur
 * based on the state of the user. This means that the application will respond
 * to user input differently depending on whether the user is an administrator,
 * regular user, or not logged in. The will prove the be very powerful when
 * implementing new features.
 * 
 * @author CS 509-Team A
 *
 */
// TODO: make this class singleton
public class StateContext {

	/**
	 * The state of the context.
	 */
	private StateMouseListener mouseListenerState;

	/**
	 * Constructor. Initialize a default state.
	 */
	public StateContext() {
		System.out.println("initilizing state context.. Should be only initialize once..");
		this.setState(new StateNormalUser());
	}

	/**
	 * Set a new state.
	 * 
	 * @param newState
	 *            the new state the context will be.
	 */
	public void setState(StateMouseListener newState) {
		System.out.println("set a new state now..");
		this.mouseListenerState = newState;
	}

	/**
	 * Method to change the state.
	 * 
	 * @param imageComponent
	 *            the component that we want to add mouse listener on.
	 * @param normalUserMouseListener
	 *            the listener for the normal user.
	 * @param adminMouseListener
	 *            the listener for the admin user.
	 */
	public void switchState(ImageComponent imageComponent, MouseListener normalUserMouseListener,
			MouseListener adminMouseListener) {
		mouseListenerState.switchMouseListener(this, imageComponent, normalUserMouseListener, adminMouseListener);
	}

}
