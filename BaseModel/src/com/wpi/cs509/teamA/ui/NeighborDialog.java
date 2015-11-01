package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class NeighborDialog extends JDialog implements ActionListener {
	
	private JButton okButton;
    private JButton cancelButton;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 */
	public NeighborDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnNewPair = new JButton("New Pair");
		btnNewPair.setActionCommand("New Pair");
		btnNewPair.addActionListener(this);
		btnNewPair.setBounds(290, 161, 123, 29);
		contentPanel.add(btnNewPair);
	
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Cancel")) 
			 NeighborDialog.this.setVisible(false);
		 if(e.getActionCommand().equals("New Pair")){
			 
			 
		 }
		 if(e.getActionCommand().equals("OK")){
			 
		 }
		
	}

}
