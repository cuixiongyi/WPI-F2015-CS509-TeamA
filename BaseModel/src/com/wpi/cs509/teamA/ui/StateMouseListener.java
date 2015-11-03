package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * The state interface has two implementations. All the methods have a reference
 * to the context object and is able to change its state.
 * 
 * @author CS 509-Team A
 *
 */
public interface StateMouseListener {

	/**
	 * Method for different state implementation class.
	 * 
	 * @param stateContext
	 *            context of the state.
	 * @param imageComponent
	 *            the component that we want to add mouse listener on.
	 * @param normalUserMouseListener
	 *            the listener for the normal user.
	 * @param adminMouseListener
	 *            the listener for the admin user.
	 */
	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent,
			MouseListener normalUserMouseListener, MouseListener adminMouseListener);

}
