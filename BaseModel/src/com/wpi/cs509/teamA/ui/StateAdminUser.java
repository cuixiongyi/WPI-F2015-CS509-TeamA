package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseListener;

/**
 * 
 * @author CS 509-Team A
 *
 */
public class StateAdminUser implements StateMouseListener {

	@Override
	public void switchMouseListener(StateContext stateContext, ImageComponent imageComponent, MouseListener normalUserMouseListener, MouseListener adminMouseListener) {
		// TODO Auto-generated method stub
		System.out.println("admin mouse event.. switch to normal user..");
		imageComponent.removeMouseListener(adminMouseListener);
		imageComponent.addMouseListener(normalUserMouseListener);
		stateContext.setState(new StateNormalUser());
	}

}
