package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminMouseListener implements MouseListener {
	
	private int xPos;
	private int yPos;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		xPos = e.getX();
		yPos = e.getY();

		System.out.println("This click from admin user..");
		System.out.println(xPos);
		System.out.println(yPos);


	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
