package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NormalUserMouseListener implements MouseListener {
	
	private int xPos;
	private int yPos;
	private ImageComponent imagePanel;
	
	public NormalUserMouseListener(){
		
	}
	
	public NormalUserMouseListener(ImageComponent imagePanel){
		System.out.println("init NormalUserMouseListener.. this should happen only once..");
		this.imagePanel = imagePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		xPos = e.getX();
		yPos = e.getY();

		System.out.println("This click from normal user..");
		System.out.println(xPos);
		System.out.println(yPos);
		
		imagePanel.setxPos(xPos);
		imagePanel.setyPos(yPos);
		
		imagePanel.repaint();
		
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

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
