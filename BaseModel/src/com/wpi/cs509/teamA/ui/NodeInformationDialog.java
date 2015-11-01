package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class NodeInformationDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton saveButton;
    private JButton cancelButton;
    private JLabel lbCoordinate;
    private JTextField xPosField;
    private JTextField yPosField;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField mapidTextField;
    private int xPos;
    private int yPos;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the dialog.
	 */
	public NodeInformationDialog(int xPosition, int yPosition) {
		xPos = xPosition;
		yPos = yPosition;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbCoordinate = new JLabel("Node Coordinate");
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
		
		JLabel lbID = new JLabel("Node ID");
		lbID.setFont(new Font("Arial", Font.PLAIN, 18));
		lbID.setBounds(15, 64, 81, 21);
		contentPanel.add(lbID);
		
		idTextField = new JTextField();
		idTextField.setBounds(192, 60, 96, 27);
		contentPanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel lbName = new JLabel("Node Name");
		lbName.setFont(new Font("Arial", Font.PLAIN, 18));
		lbName.setBounds(15, 106, 109, 21);
		contentPanel.add(lbName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(192, 102, 96, 27);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblMapId = new JLabel("Map ID");
		lblMapId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMapId.setBounds(15, 149, 81, 21);
		contentPanel.add(lblMapId);
		
		mapidTextField = new JTextField();
		mapidTextField.setBounds(192, 144, 96, 27);
		mapidTextField.getText().matches("[1-4]");
		contentPanel.add(mapidTextField);
		mapidTextField.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
		saveButton.setActionCommand("Save");
		saveButton.addActionListener(this);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
	
	
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);				
	}
	

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getActionCommand().equals("Cancel")) 
			 NodeInformationDialog.this.setVisible(false);
		 if(e.getActionCommand().equals("Save")){
			 System.out.println("hhe");
			 if(idTextField.getText().trim().equals("")||
					 nameTextField.getText().trim().equals("")||
					 	mapidTextField.getText().trim().equals("")){
				 
				 JOptionPane.showMessageDialog(null,
			                "Please fill all fields.");		 
			 }else if(!mapidTextField.getText().matches("[1-4]")){
				 JOptionPane.showMessageDialog(null,
			                "Invalid input.");	 
			 }else{ 
				 Node node = new Node();
					node.setId(Integer.parseInt(idTextField.getText()));
					node.setLocation(new Coordinate(xPos, yPos));
					node.setMapId(Integer.parseInt(mapidTextField.getText()));
					node.setName(nameTextField.getText());
					node.saveNode();
			 }
                

             //this.dispose();
        }    
		
	}
}
