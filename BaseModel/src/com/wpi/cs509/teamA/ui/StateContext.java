package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
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
