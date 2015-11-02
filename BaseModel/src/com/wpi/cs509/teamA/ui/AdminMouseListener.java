package com.wpi.cs509.teamA.ui;

import java.awt.Dialog.ModalityType;
import java.lang.Math;

import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * The admin mouse listener implementation.
 * 
 * @author CS 509-Team A
 *
 */
public class AdminMouseListener implements MouseListener {

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
	public AdminMouseListener() {

	}

	/**
	 * Constructor.
	 * 
	 * @param imagePanel
	 *            the image component that the listener will be added to
	 */
	public AdminMouseListener(ImageComponent imagePanel) {
		System.out.println("init AdminMouseListener.. this should happen only once..");
		this.imagePanel = imagePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1){
			xPos = e.getX();
			yPos = e.getY();
	
			System.out.println("This click from admin user..");
			System.out.println(xPos);
			System.out.println(yPos);
	
			imagePanel.setxPos(xPos);
			imagePanel.setyPos(yPos);
	
			imagePanel.repaint();
		} else if(e.getButton()==MouseEvent.BUTTON3){
			boolean tooclose=false;
			int closeRange = 10; 
			for(int i=0;i<imagePanel.getCoorList().size();i++)
			{
//				System.out.println("nodelist loop");
//				System.out.println("xpos"+xPos);
//				System.out.println("list"+imagePanel.getCoorList().get(i).getX());
				if(Math.abs(e.getX()-imagePanel.getCoorList().get(i).getX())<closeRange 
						|| Math.abs(e.getY()-imagePanel.getCoorList().get(i).getY())<closeRange)
				{	
					tooclose=true;
				}

			}
			if(tooclose){
				JOptionPane.showMessageDialog(null,"Too close from another node.");	
			}
			else{
			
				NodeManageDialog nodeManageDialog = new NodeManageDialog(imagePanel, e.getX(), e.getY());
		    	nodeManageDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		    	nodeManageDialog.setVisible(nodeManageDialog.isFocusable());
			}
		}
		

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
