package com.wpi.cs509.teamA.model;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Observable;

import com.wpi.cs509.teamA.ui.controller.mousestatepattern.MouseActionState;

/**
 * TODO: extends this class from a base observable class and this should also be
 * a model for the state
 * 
 * The state change will be reflected in this class, and based on different
 * state, this class will behave differently
 * 
 * @author CS 509-Team A
 *
 */

public class StateContextModel extends Observable {

	/**
	 * The current model state
	 * 
	 * MouseActionState is an abstract class that provides a uniform interface
	 */
	private MouseActionState myState;

	/**
	 * Constructor. Initialize a default state.
	 */
	protected StateContextModel() {

		myState = null;

	}

	/**
	 * update the state of the current model
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

	/**
	 * 
	 * implementation of the mouse action
	 * 
	 * this execute has nothing related to myState.execute
	 * 
	 * @param e
	 * @return
	 */
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
