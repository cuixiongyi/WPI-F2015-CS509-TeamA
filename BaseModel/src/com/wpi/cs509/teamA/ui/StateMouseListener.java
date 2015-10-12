package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * The state interface and two implementations. The state’s method has a
 * reference to the context object and is able to change its state.
 * 
 * @author CS 509-Team A
 *
 */
public interface StateMouseListener {

	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent,  MouseListener normalUserMouseListener, MouseListener adminMouseListener);

}
