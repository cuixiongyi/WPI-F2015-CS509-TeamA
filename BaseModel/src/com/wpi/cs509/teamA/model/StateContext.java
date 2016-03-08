package com.wpi.cs509.teamA.model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Observable;

import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionState;

/**
 * TODO: extends this class from a base observable class and this should also be
 * a model for the state
 * 
 * @author CS 509-Team A
 *
 */

public class StateContext extends Observable {

	/**
	 * The current state
	 */
	private MouseActionState myState;

	/**
	 * Constructor. Initialize a default state.
	 */
	protected StateContext() {
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

	public void modelChanged() {
		setChanged();
		notifyObservers();
	}

}
