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
import java.awt.GridLayout;


public class NeighborDialog extends JDialog implements ActionListener {
	
	private JButton okButton;
    private JButton cancelButton;
    private static int edgeNumber = 0;
   
    private JPanel pairPanel;
    private JLabel lbPair;
    private JTextField textField;
    private JTextField textFieldNodePair1;
    private JLabel lbPair_1;
    private JTextField textFieldNodePair2;
    private JTextField textFieldNodePair3;
    private JLabel lbEdge;
    private JLabel lbEdge_1;
    private JLabel lbEdge_2;
    private JTextField textFieldNodePair4;
    private JTextField textFieldNodePair5;
    private JTextField textFieldNodePair6;
    private JTextField textFieldNodePair7;
    private JTextField textFieldNodePair8;
    private JTextField textFieldNodePair9;
    private JLabel lbEdge_3;
    private JTextField textFieldNodePair10;
    private JTextField textFieldNodePair11;
    private JLabel lbEdge_4;
    private JTextField textFieldNodePair12;
    private JTextField textFieldNodePair13;
    private JLabel lbEdge_5;
    private JTextField textFieldNodePair14;
    private JTextField textFieldNodePair15;
    private JLabel lbEdge_6;
    private JTextField textFieldNodePair16;
    private JTextField textFieldNodePair17;
    private JLabel lbEdge_7;
    private JTextField textFieldNodePair18;
    private JTextField textFieldNodePair19;
    private JLabel lbPair_2;
    private JTextField textFieldNodePair20;
    private JTextField textFieldNodePair21;

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
		setBounds(100, 100, 450, 736);
		getContentPane().setLayout(null);
	
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 641, 428, 39);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);
		
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		
		pairPanel = new JPanel();
		pairPanel.setBounds(0, 0, 428, 641);
		getContentPane().add(pairPanel);
		pairPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		lbPair_1 = new JLabel("Edge 1");
		pairPanel.add(lbPair_1);
		
		textFieldNodePair2 = new JTextField();
		pairPanel.add(textFieldNodePair2);
		textFieldNodePair2.setColumns(10);
		
		textFieldNodePair3 = new JTextField();
		pairPanel.add(textFieldNodePair3);
		textFieldNodePair3.setColumns(10);
		
		lbEdge = new JLabel("Edge 2");
		pairPanel.add(lbEdge);
		
		textFieldNodePair4 = new JTextField();
		textFieldNodePair4.setColumns(10);
		pairPanel.add(textFieldNodePair4);
		
		textFieldNodePair5 = new JTextField();
		textFieldNodePair5.setColumns(10);
		pairPanel.add(textFieldNodePair5);
		
		lbEdge_1 = new JLabel("Edge 3");
		pairPanel.add(lbEdge_1);
		
		textFieldNodePair6 = new JTextField();
		textFieldNodePair6.setColumns(10);
		pairPanel.add(textFieldNodePair6);
		
		textFieldNodePair7 = new JTextField();
		textFieldNodePair7.setColumns(10);
		pairPanel.add(textFieldNodePair7);
		
		lbEdge_2 = new JLabel("Edge 4");
		pairPanel.add(lbEdge_2);
		
		textFieldNodePair8 = new JTextField();
		textFieldNodePair8.setColumns(10);
		pairPanel.add(textFieldNodePair8);
		
		textFieldNodePair9 = new JTextField();
		textFieldNodePair9.setColumns(10);
		pairPanel.add(textFieldNodePair9);
		
		lbEdge_3 = new JLabel("Edge 5");
		pairPanel.add(lbEdge_3);
		
		textFieldNodePair10 = new JTextField();
		textFieldNodePair10.setColumns(10);
		pairPanel.add(textFieldNodePair10);
		
		textFieldNodePair11 = new JTextField();
		textFieldNodePair11.setColumns(10);
		pairPanel.add(textFieldNodePair11);
		
		lbEdge_4 = new JLabel("Edge 6");
		pairPanel.add(lbEdge_4);
		
		textFieldNodePair12 = new JTextField();
		textFieldNodePair12.setColumns(10);
		pairPanel.add(textFieldNodePair12);
		
		textFieldNodePair13 = new JTextField();
		textFieldNodePair13.setColumns(10);
		pairPanel.add(textFieldNodePair13);
		
		lbEdge_5 = new JLabel("Edge 7");
		pairPanel.add(lbEdge_5);
		
		textFieldNodePair14 = new JTextField();
		textFieldNodePair14.setColumns(10);
		pairPanel.add(textFieldNodePair14);
		
		textFieldNodePair15 = new JTextField();
		textFieldNodePair15.setColumns(10);
		pairPanel.add(textFieldNodePair15);
		
		lbEdge_6 = new JLabel("Edge 8");
		pairPanel.add(lbEdge_6);
		
		textFieldNodePair16 = new JTextField();
		textFieldNodePair16.setColumns(10);
		pairPanel.add(textFieldNodePair16);
		
		textFieldNodePair17 = new JTextField();
		textFieldNodePair17.setColumns(10);
		pairPanel.add(textFieldNodePair17);
		
		lbPair_2 = new JLabel("Edge 9");
		pairPanel.add(lbPair_2);
		
		textFieldNodePair18 = new JTextField();
		textFieldNodePair18.setColumns(10);
		pairPanel.add(textFieldNodePair18);
		
		textFieldNodePair19 = new JTextField();
		textFieldNodePair19.setColumns(10);
		pairPanel.add(textFieldNodePair19);
		
		lbEdge_7 = new JLabel("Edge 10");
		pairPanel.add(lbEdge_7);
		
		textFieldNodePair20 = new JTextField();
		pairPanel.add(textFieldNodePair20);
		textFieldNodePair20.setColumns(10);
		
		textFieldNodePair21 = new JTextField();
		pairPanel.add(textFieldNodePair21);
		textFieldNodePair21.setColumns(10);
		
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Cancel")) 
			 NeighborDialog.this.setVisible(false);
		
		 if(e.getActionCommand().equals("OK")){
			 
		 }
		
	}
}
