package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.UIDataBuffer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import java.awt.Dialog.ModalityType;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
				stateContext.switchToState(new MouseActionAddNode(stateContext));

		} else if (e.getSource() == mntmDelete) {
			// TODO: Show a dialog to ask the user if they really want to delete
			// TODO: Database
		} else if (e.getSource() == mntmEdit) {

		}

	}
}
