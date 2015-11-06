package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * Admin state implementation.
 * 
 * @author CS 509-Team A
 *
 */
public class StateAdminUser implements StateMouseListener {

	/**
	 * This method will remove the old mouse listener and add a listener that
	 * specific to the normal user operation.
	 */
	@Override
	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent,
			MouseListener normalUserMouseListener, MouseListener adminMouseListener) {
		// TODO Auto-generated method stub
		imageComponent.removeMouseListener(adminMouseListener);
		imageComponent.addMouseListener(normalUserMouseListener);
		stateContext.setState(new StateNormalUser());
	}

}
