package com.wpi.cs509.teamA.ui;

import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

/**
 * This is the class that administrators uses to add nodes to database
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class NodeManageMenu extends JPopupMenu implements ActionListener {
	private int xPos;
	private int yPos;
	private ImageComponent imagePanel;
	private JMenuItem mntmAdd;
	private JMenuItem mntmDelete;
	private JMenuItem mntmEdit;
	private StateContext stateContext;

	private final static String ADD = "Add Node";
	private final static String DELETE = "Delete Node";
	private final static String EDIT = "Edit Node";
	private final static int closeRange = 5;

	/**
	 * Create the Menu.
	 */
	public NodeManageMenu(ImageComponent imageComponent, int xPosition, int yPosition, StateContext pStateContext) {
		xPos = xPosition;
		yPos = yPosition;
		imagePanel = imageComponent;
		setBounds(100, 100, 450, 300);

		// set up menu item
		mntmAdd = new JMenuItem(ADD);
		mntmAdd.addActionListener(this);
		add(mntmAdd);

		mntmDelete = new JMenuItem(DELETE);
		add(mntmDelete);
		mntmDelete.addActionListener(this);

		mntmEdit = new JMenuItem(EDIT);
		add(mntmEdit);
		mntmEdit.addActionListener(this);


		this.stateContext = pStateContext;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmAdd) {
				stateContext.switchToState(new MouseActionEditNode(stateContext));

		} else if (e.getSource() == mntmDelete) {
			// TODO: Show a dialog to ask the user if they really want to delete
			// TODO: Database
		} else if (e.getSource() == mntmEdit) {

		}

	}
}
