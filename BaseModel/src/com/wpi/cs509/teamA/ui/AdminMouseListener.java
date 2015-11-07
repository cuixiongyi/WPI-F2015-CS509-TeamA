package com.wpi.cs509.teamA.ui;

import java.lang.Math;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.UIDataBuffer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The admin mouse listener implementation.
 * 
 * @author CS 509-Team A
 *
 */
public class AdminMouseListener implements MouseListener {

	private final static int closeRange = 5;

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
	 * Component for entering edge
	 */
	private NeighborDialog neighborDialog;

	private JButton btnNeighborManage;

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
	public AdminMouseListener(final ImageComponent imagePanel) {
		this.imagePanel = imagePanel;
		this.btnNeighborManage = imagePanel.getInputPanel().getBtnNeighborManage();
		this.btnNeighborManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neighborDialog = new NeighborDialog(imagePanel);
				neighborDialog.setVisible(true);
				neighborDialog.setAlwaysOnTop(true);

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// we have to minus 5 to correct deviation
		xPos = e.getX();
		yPos = e.getY();
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1 && neighborDialog != null && neighborDialog.isVisible()) {
			neighborDialog.setFieldTitle(xPos, yPos);
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			//
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			boolean tooClose = false;

			Map<Integer, List<Node>> allNodesNow = UIDataBuffer.getAllNodes();
			List<Node> temp = allNodesNow.get(1);
			for (int i = 0; i < temp.size(); i++) {
				if (Math.abs(xPos - temp.get(i).getLocation().getX()) < closeRange
						&& Math.abs(yPos - temp.get(i).getLocation().getY()) < closeRange) {
					tooClose = true;
				}
			}
			if (tooClose) {
				JOptionPane.showMessageDialog(null, "Too close from another node.");
			} else {
				NodeManageMenu nodeManageMenu = new NodeManageMenu(imagePanel, xPos, yPos);
				nodeManageMenu.show(e.getComponent(), xPos, yPos);
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
