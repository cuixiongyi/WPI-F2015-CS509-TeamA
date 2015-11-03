package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Dialog.ModalityType;

/**
 * This is the class that administrators uses to add nodes to database
 * 
 * @author ZYang
 *
 */

@SuppressWarnings("serial")
public class NodeManageDialog extends JDialog implements ActionListener {

	private JPanel contentPanel = new JPanel();
	private JButton btnCancel;
	private JButton btnAddNode;
	private JButton btnEditNode;
	private JButton btnDeleteNode;
	private int xPos;
	private int yPos;
	private ImageComponent imagePanel;

	private final String ADD = "Add";
	private final String DELETE = "Delete";
	private final String EDIT = "Edit";
	private final String CANCEL = "Cancel";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the dialog.
	 */
	public NodeManageDialog(ImageComponent imageComponent, int xPosition, int yPosition) {
		xPos = xPosition;
		yPos = yPosition;
		imagePanel = imageComponent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// set up ADD, DELETE, EDIT buttons
		btnAddNode = new JButton(ADD);
		btnAddNode.setBounds(112, 15, 216, 29);
		btnAddNode.setActionCommand(ADD);
		btnAddNode.addActionListener(this);
		contentPanel.add(btnAddNode);

		btnDeleteNode = new JButton(DELETE);
		btnDeleteNode.setBounds(112, 60, 216, 29);
		btnDeleteNode.setActionCommand(DELETE);
		btnDeleteNode.addActionListener(this);
		contentPanel.add(btnDeleteNode);

		btnEditNode = new JButton(EDIT);
		btnEditNode.setBounds(112, 105, 216, 29);
		btnEditNode.setActionCommand(EDIT);
		btnEditNode.addActionListener(this);
		contentPanel.add(btnEditNode);

		// set up CANCEL button
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnCancel = new JButton(CANCEL);
		btnCancel.setActionCommand(CANCEL);
		btnCancel.addActionListener(this);
		buttonPane.add(btnCancel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(CANCEL)) {
			NodeManageDialog.this.setVisible(false);
		}
		if (e.getActionCommand().equals(ADD)) {
			
			// Create a NodeInformationDialog
			NodeInformationDialog nodeInfo = new NodeInformationDialog(imagePanel, xPos, yPos);
			nodeInfo.setModalityType(ModalityType.APPLICATION_MODAL);
			nodeInfo.setVisible(nodeInfo.isFocusable());

		}
		if (e.getActionCommand().equals(DELETE)) {
			// TODO: Show a dialog to ask the user if they really want to delete
			// TODO: Database
		}
		if (e.getActionCommand().equals(EDIT)) {

		}

	}
}
