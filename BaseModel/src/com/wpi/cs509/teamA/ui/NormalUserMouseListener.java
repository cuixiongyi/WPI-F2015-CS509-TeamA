package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The normal user mouse listener implementation.
 * 
 * @author CS 509-Team A
 *
 */
public class NormalUserMouseListener implements MouseListener {

	/**
	 * The x position that the user clicked
	 */
	private int xPos;
	/**
	 * The y position that the user clicked
	 */
	private int yPos;
	/**
	 * The image component that the listener will be added to
	 */
	private ImageComponent imagePanel;

	/**
	 * Default constructor
	 */
	public NormalUserMouseListener() {

	}

	/**
	 * Constructor.
	 * 
	 * @param imagePanel
	 *            the image component that the listener will be added to
	 */
	public NormalUserMouseListener(ImageComponent imagePanel) {
		this.imagePanel = imagePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1) {
		imagePanel.setxPos(e.getX());
		imagePanel.setyPos(e.getY());
		imagePanel.repaint();
		}


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
	 * @param xPos
	 *            the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos
	 *            the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}
