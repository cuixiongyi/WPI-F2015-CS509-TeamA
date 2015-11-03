package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * Normal user state implementation.
 * 
 * @author CS 509-Team A
 *
 */
public class StateNormalUser implements StateMouseListener {

	/**
	 * This method will remove the old mouse listener and add a listener that
	 * specific to the normal user operation.
	 */
	@Override
	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent,
			MouseListener normalUserMouseListener, MouseListener adminMouseListener) {
		// TODO Auto-generated method stub
		imageComponent.removeMouseListener(normalUserMouseListener);
		imageComponent.addMouseListener(adminMouseListener);
		stateContext.setState(new StateAdminUser());
	}

}
