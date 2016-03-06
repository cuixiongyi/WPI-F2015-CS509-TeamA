package com.wpi.cs509.teamA.model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionState;

/**
 * This model listens to the mouse action changes specifically.
 * 
 * Different permissions will give different mouse operation.
 * 
 * It is part of the state pattern implementation.
 * 
 * @author CS 509-Team A
 *
 */

public class StateContextModel extends BaseModel {

	/**
	 * The current state
	 */
	private MouseActionState myState;

	/**
	 * Constructor. Initialize a default state.
	 */
	protected StateContextModel() {
		myState = null;

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
		modelChanged();

	}

	public boolean execute(MouseEvent e) {
		boolean ret = myState.execute(e);
		modelChanged();
		return ret;
	}

	public boolean cleanup() {
		boolean ret = myState.cleanup();
		modelChanged();
		return ret;
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