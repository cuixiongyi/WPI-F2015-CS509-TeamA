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

public class NodeManageDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;


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
	public NodeManageDialog (UserScreen frame) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
			
		}
	}
	
	public void setFieldTitle(int xPos,int yPos){
		  xPosField.setText(String.valueOf(xPos));
		  yPosField.setText(String.valueOf(yPos));
	}

	
		@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equals("Cancel")) 
			 NodeManageDialog.this.setVisible(false);
		 if(e.getActionCommand().equals("OK")){
                 // not finished yet

              //this.dispose();
         }    
			
	}
		
		
	
	
}
	