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
 * @author ZYang
 *
 */

@SuppressWarnings("serial")
public class NodeManageDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancel;
    private JButton btnAddNode;
    private JButton btnEditNode;
    private JButton btnDeleteNode;
    private int xPos;
    private int yPos;
    private ImageComponent imagePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
//			MapDialog dialog = new MapDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the dialog.
	 */
	public NodeManageDialog (ImageComponent imageComponent, int xPosition, int yPosition) {
		xPos = xPosition;
		yPos = yPosition;
		imagePanel=imageComponent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnAddNode = new JButton("Add");
		btnAddNode.setBounds(112, 15, 216, 29);
		btnAddNode.setActionCommand("Add");
		btnAddNode.addActionListener(this);
		contentPanel.add(btnAddNode);
		
		btnDeleteNode = new JButton("Delete");
		btnDeleteNode.setBounds(112, 60, 216, 29);
		btnDeleteNode.setActionCommand("Delete");
		btnDeleteNode.addActionListener(this);
		contentPanel.add(btnDeleteNode);
		
		btnEditNode = new JButton("Edit");
		btnEditNode.setBounds(112, 105, 216, 29);
		btnEditNode.setActionCommand("Edit");
		btnEditNode.addActionListener(this);
		contentPanel.add(btnEditNode);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(this);
		buttonPane.add(btnCancel);
	
		
		
	}
	


	
		@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("Cancel")){
			 NodeManageDialog.this.setVisible(false);
		 }	 
		 if(e.getActionCommand().equals("Add")){
             NodeInformationDialog nodeInfo = new NodeInformationDialog(imagePanel,xPos, yPos);
             nodeInfo.setModalityType(ModalityType.APPLICATION_MODAL);
             nodeInfo.setVisible(nodeInfo.isFocusable());
             
         } 
		 if(e.getActionCommand().equals("Delete")){
             //Database
         } 
		 if(e.getActionCommand().equals("Edit")){
             
         } 

			
	}
}
	