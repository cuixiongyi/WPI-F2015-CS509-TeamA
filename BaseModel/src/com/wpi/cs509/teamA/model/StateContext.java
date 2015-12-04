package com.wpi.cs509.teamA.model;

import java.awt.*;
import java.awt.event.MouseEvent;

import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;

/**
 * Instead of using a lot of if and else statements to capture the state of an
 * object, a state machine design pattern is a better solution that's more
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
	 * The current state
	 */
	private MouseActionState myState;

	/**
	 * Constructor. Initialize a default state.
	 */
	public StateContext() {


		myState = new MouseActionSelectNode(this);

		this.switchToState(new MouseActionSelectNode(this));
	}

	/**
	 * Set a new state.
	 *
	 * @param newState
	 *            the new state the context will be.
	 */
	public void switchToState(MouseActionState newState) {
		if (null != myState) {
			myState.cleanup();
		}
		this.myState = newState;
    }


	public boolean execute(MouseEvent e) {
		boolean ret =  myState.execute(e);
		return ret;
	}
	
	public boolean cleanup(){
		return myState.cleanup();
	}

    public void paintOnImage(Graphics2D g2) {
		myState.paintOnImage(g2);
    }



	/**
	 * setter and getter
	 */

	public MouseActionState getMyState() {
		return myState;
	}

}
