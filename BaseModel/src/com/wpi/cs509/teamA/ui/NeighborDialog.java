package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class NeighborDialog extends JDialog implements ActionListener {
	
	private JButton okButton;
    private JButton cancelButton;
    private static int edgeNumber = 0;
    private JScrollPane scrollPane;
   
    private JPanel pairPanel;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 */
	public NeighborDialog() {
		setTitle("Neighbor Pairs");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 205, 428, 39);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);
		
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		
		JButton btnNewPair = new JButton("New Pair");
		buttonPane.add(btnNewPair);
		btnNewPair.setActionCommand("New Pair");
		btnNewPair.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 428, 205);
		getContentPane().add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pairPanel = new JPanel();
		scrollPane.setViewportView(pairPanel);
		pairPanel.setLayout(null);
		
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Cancel")) 
			 NeighborDialog.this.setVisible(false);
		 if(e.getActionCommand().equals("New Pair")){
			 String node1 = new String("node1_d%"+edgeNumber);
			 String node2 = new String("node2_d%"+edgeNumber);
			 String nodePair = new String("Edge + d%"+edgeNumber);
			 System.out.println("hehe");
			 lblNewLabel = new JLabel(nodePair);
			 lblNewLabel.setBounds(15, 15, 81, 21);
			 pairPanel.add(lblNewLabel);
			
			 textField = new JTextField();
			 textField.setBounds(132, 15, 96, 27);
			 pairPanel.add(textField);
			 textField.setColumns(10);
			
			 textField_1.setBounds(260, 15, 96, 27);
			 pairPanel.add(new JTextField());
			 textField_1.setColumns(10);
			 
		 }
		 if(e.getActionCommand().equals("OK")){
			 
		 }
		
	}
}
