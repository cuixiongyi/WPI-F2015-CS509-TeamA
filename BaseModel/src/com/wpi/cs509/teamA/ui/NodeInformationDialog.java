package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.NodeType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * This is the class that administrators uses to input information of nodes
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class NodeInformationDialog extends JDialog implements ActionListener {

	private JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JButton cancelButton;
	private JLabel lbCoordinate;
	private JTextField xPosField;
	private JTextField yPosField;
	private JTextField typeTextField;
	private JTextField nameTextField;
	private JTextField mapidTextField;
	private int xPos;
	private int yPos;
	private ImageComponent imagePanel;

	private final static String COORDINATE = "Node Coordinate";
	private final static String TYPE = "Node Type";
	private final static String NAME = "Node Name";
	private final static String SAVE = "SAVE";
	private final static String ID = "Map ID";
	private final static String CANCEL = "Cancel";

	/**
	 * Create the dialog.
	 */
	public NodeInformationDialog(ImageComponent imageComponent, int xPosition, int yPosition) {
		xPos = xPosition;
		yPos = yPosition;
		imagePanel = imageComponent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Node information block
		lbCoordinate = new JLabel(COORDINATE);
		lbCoordinate.setFont(new Font("Arial", Font.PLAIN, 18));
		lbCoordinate.setBounds(15, 15, 147, 34);
		contentPanel.add(lbCoordinate);

		xPosField = new JTextField();
		xPosField.setEditable(false);
		xPosField.setFont(new Font("Arial", Font.PLAIN, 18));
		xPosField.setBounds(192, 18, 96, 27);
		xPosField.setText(String.valueOf(xPos));
		contentPanel.add(xPosField);
		xPosField.setColumns(10);

		yPosField = new JTextField();
		yPosField.setEditable(false);
		yPosField.setFont(new Font("Arial", Font.PLAIN, 18));
		yPosField.setBounds(303, 18, 96, 27);
		yPosField.setText(String.valueOf(yPos));
		contentPanel.add(yPosField);
		yPosField.setColumns(10);

		JLabel lbType = new JLabel(TYPE);
		lbType.setFont(new Font("Arial", Font.PLAIN, 18));
		lbType.setBounds(15, 64, 119, 21);
		contentPanel.add(lbType);

		typeTextField = new JTextField();
		typeTextField.setBounds(192, 60, 96, 27);
		typeTextField.setText("Campus");
		contentPanel.add(typeTextField);
		typeTextField.setColumns(10);

		JLabel lbName = new JLabel(NAME);
		lbName.setFont(new Font("Arial", Font.PLAIN, 18));
		lbName.setBounds(15, 106, 109, 21);
		contentPanel.add(lbName);

		nameTextField = new JTextField();
		nameTextField.setBounds(192, 102, 96, 27);
		nameTextField.setText("Location");
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblMapId = new JLabel(ID);
		lblMapId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMapId.setBounds(15, 149, 81, 21);
		contentPanel.add(lblMapId);

		mapidTextField = new JTextField();
		mapidTextField.setBounds(192, 144, 96, 27);
		mapidTextField.setText("1");
		contentPanel.add(mapidTextField);
		mapidTextField.setColumns(10);

		// SAVE and CANCEL button
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		saveButton = new JButton(SAVE);
		saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
		saveButton.setActionCommand(SAVE);
		saveButton.addActionListener(this);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);

		cancelButton = new JButton(CANCEL);
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(CANCEL))
			NodeInformationDialog.this.setVisible(false);
		if (e.getActionCommand().equals(SAVE)) {
			// Check if fields are filled
			if (typeTextField.getText().trim().equals("") || nameTextField.getText().trim().equals("")
					|| mapidTextField.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "Please fill all fields.");
			} else if (!mapidTextField.getText().matches("[1-4]")) {
				// There are only four maps
				JOptionPane.showMessageDialog(null, "Invalid input.");
			} else {
				// Save node information
				Node node = new Node();
				Coordinate coordinate = new Coordinate();
				coordinate.setX(xPos);
				coordinate.setY(yPos);
				node.setLocation(coordinate);
				node.setMapId(Integer.parseInt(mapidTextField.getText()));
				node.setName(nameTextField.getText());
				// TODO: get string from the front end then we can process it
				// for now we only store undefined..
				node.setNodeType(NodeType.UNDEFINED);

				// call database save function..
				// TODO: Maybe we can use mutlti-thread here..
				node.saveNode();
				
				// show what we have saved..
				imagePanel.addNodeList(xPos, yPos);
				imagePanel.repaint();
				this.setVisible(false);
			}
		}

	}
}
