package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NormalUserMouseListener implements MouseListener {
	
	private int xPos;
	private int yPos;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		xPos = e.getX();
		yPos = e.getY();

		System.out.println("This click from normal user..");
		System.out.println(xPos);
		System.out.println(yPos);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
