package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * 
 * @author CS 509-Team A
 *
 */
// TODO: make this class singleton
public class StateContext {

	private StateMouseListener mouseListenerState;

	public StateContext() {
		System.out.println("initilizing state context.. Should be only initialize once..");
		this.setState(new StateNormalUser());
	}

	public void setState(StateMouseListener newState) {
		System.out.println("set a new state now..");
		this.mouseListenerState = newState;
	}

	public void switchState(ImageComponent imageComponent, MouseListener normalUserMouseListener,
			MouseListener adminMouseListener) {
		mouseListenerState.switchMouseListener(this, imageComponent, normalUserMouseListener, adminMouseListener);
	}

}
