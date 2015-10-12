package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * This class is the implementation of the
 * 
 * @author CS 509-Team A
 *
 */
public class StateNormalUser implements StateMouseListener {

	@Override
	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent,
			MouseListener normalUserMouseListener, MouseListener adminMouseListener) {
		// TODO Auto-generated method stub
		System.out.println("normal user mouse event.. switch to admin user...");
		imageComponent.removeMouseListener(normalUserMouseListener);
		imageComponent.addMouseListener(adminMouseListener);
		stateContext.setState(new StateAdminUser());
	}

}
