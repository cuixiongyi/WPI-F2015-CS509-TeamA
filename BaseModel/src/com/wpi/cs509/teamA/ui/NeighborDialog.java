package com.wpi.cs509.teamA.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;

/**
 * This is the class that administrators uses to input edges
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class NeighborDialog extends JDialog implements ActionListener, FocusListener {

	private JButton saveButton;
	private JButton cancelButton;

	private JPanel pairPanel;
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
	private JTextField textFieldNodePair1;

	private JTextField getCoordinateTextField = null;

	private final String SAVE = "Save";
	private final String CANCEL = "Cancel";
	private final String NEIGHBOR = "Neighbor Pairs";

	/**
	 * Create the dialog.
	 */
	public NeighborDialog() {
		setTitle(NEIGHBOR);
		setBounds(100, 100, 450, 736);
		getContentPane().setLayout(null);

		// set up SAVE and CANCEL button
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 641, 428, 39);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		saveButton = new JButton(SAVE);
		saveButton.setActionCommand(SAVE);
		saveButton.addActionListener(this);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);

		cancelButton = new JButton(CANCEL);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);

		// Edge pair block. 20 textFieldNodePairs show coordinate of node.
		pairPanel = new JPanel();
		pairPanel.setBounds(0, 0, 428, 641);
		getContentPane().add(pairPanel);
		pairPanel.setLayout(new GridLayout(0, 3, 0, 0));

		lbPair_1 = new JLabel("Edge 1");
		pairPanel.add(lbPair_1);

		textFieldNodePair1 = new JTextField();
		pairPanel.add(textFieldNodePair1);
		textFieldNodePair1.addFocusListener(this);
		textFieldNodePair1.setColumns(10);

		textFieldNodePair2 = new JTextField();
		pairPanel.add(textFieldNodePair2);
		textFieldNodePair2.addFocusListener(this);
		textFieldNodePair2.setColumns(10);

		lbEdge = new JLabel("Edge 2");
		pairPanel.add(lbEdge);

		textFieldNodePair3 = new JTextField();
		textFieldNodePair3.setColumns(10);
		textFieldNodePair3.addFocusListener(this);
		pairPanel.add(textFieldNodePair3);

		textFieldNodePair4 = new JTextField();
		textFieldNodePair4.setColumns(10);
		textFieldNodePair4.addFocusListener(this);
		pairPanel.add(textFieldNodePair4);

		lbEdge_1 = new JLabel("Edge 3");
		pairPanel.add(lbEdge_1);

		textFieldNodePair5 = new JTextField();
		textFieldNodePair5.setColumns(10);
		textFieldNodePair5.addFocusListener(this);
		pairPanel.add(textFieldNodePair5);

		textFieldNodePair6 = new JTextField();
		textFieldNodePair6.setColumns(10);
		textFieldNodePair6.addFocusListener(this);
		pairPanel.add(textFieldNodePair6);

		lbEdge_2 = new JLabel("Edge 4");
		pairPanel.add(lbEdge_2);

		textFieldNodePair7 = new JTextField();
		textFieldNodePair7.setColumns(10);
		textFieldNodePair7.addFocusListener(this);
		pairPanel.add(textFieldNodePair7);

		textFieldNodePair8 = new JTextField();
		textFieldNodePair8.setColumns(10);
		textFieldNodePair8.addFocusListener(this);
		pairPanel.add(textFieldNodePair8);

		lbEdge_3 = new JLabel("Edge 5");
		pairPanel.add(lbEdge_3);

		textFieldNodePair9 = new JTextField();
		textFieldNodePair9.setColumns(10);
		textFieldNodePair9.addFocusListener(this);
		pairPanel.add(textFieldNodePair9);

		textFieldNodePair10 = new JTextField();
		textFieldNodePair10.setColumns(10);
		textFieldNodePair10.addFocusListener(this);
		pairPanel.add(textFieldNodePair10);

		lbEdge_4 = new JLabel("Edge 6");
		pairPanel.add(lbEdge_4);

		textFieldNodePair11 = new JTextField();
		textFieldNodePair11.setColumns(10);
		textFieldNodePair11.addFocusListener(this);
		pairPanel.add(textFieldNodePair11);

		textFieldNodePair12 = new JTextField();
		textFieldNodePair12.setColumns(10);
		textFieldNodePair12.addFocusListener(this);
		pairPanel.add(textFieldNodePair12);

		lbEdge_5 = new JLabel("Edge 7");
		pairPanel.add(lbEdge_5);

		textFieldNodePair13 = new JTextField();
		textFieldNodePair13.setColumns(10);
		textFieldNodePair13.addFocusListener(this);
		pairPanel.add(textFieldNodePair13);

		textFieldNodePair14 = new JTextField();
		textFieldNodePair14.setColumns(10);
		textFieldNodePair14.addFocusListener(this);
		pairPanel.add(textFieldNodePair14);

		lbEdge_6 = new JLabel("Edge 8");
		pairPanel.add(lbEdge_6);

		textFieldNodePair15 = new JTextField();
		textFieldNodePair15.setColumns(10);
		textFieldNodePair15.addFocusListener(this);
		pairPanel.add(textFieldNodePair15);

		textFieldNodePair16 = new JTextField();
		textFieldNodePair16.setColumns(10);
		textFieldNodePair16.addFocusListener(this);
		pairPanel.add(textFieldNodePair16);

		lbPair_2 = new JLabel("Edge 9");
		pairPanel.add(lbPair_2);

		textFieldNodePair17 = new JTextField();
		textFieldNodePair17.setColumns(10);
		textFieldNodePair17.addFocusListener(this);
		pairPanel.add(textFieldNodePair17);

		textFieldNodePair18 = new JTextField();
		textFieldNodePair18.setColumns(10);
		textFieldNodePair18.addFocusListener(this);
		pairPanel.add(textFieldNodePair18);

		lbEdge_7 = new JLabel("Edge 10");
		pairPanel.add(lbEdge_7);

		textFieldNodePair19 = new JTextField();
		pairPanel.add(textFieldNodePair19);
		textFieldNodePair19.addFocusListener(this);
		textFieldNodePair19.setColumns(10);

		textFieldNodePair20 = new JTextField();
		pairPanel.add(textFieldNodePair20);
		textFieldNodePair20.addFocusListener(this);
		textFieldNodePair20.setColumns(10);

	}

	public void setFieldTitle(int xPos, int yPos) {
		this.getCoordinateTextField.setText(String.valueOf(xPos) + " , " + String.valueOf(yPos));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(CANCEL))
			NeighborDialog.this.setVisible(false);

		if (e.getActionCommand().equals(SAVE)) {

			this.setVisible(false);

			// database

		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		getCoordinateTextField = (JTextField) e.getComponent();

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}
}
