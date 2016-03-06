package com.wpi.cs509.teamA.ui.Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wpi.cs509.teamA.model.DataModel;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * This is the class that administrators uses to input information of nodes
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class NodeEditDialog extends JDialog implements ActionListener {

	private JPanel contentPanel = new JPanel();
	private JButton saveButton;
	private JButton cancelButton;
	private JButton deleteButton = null;
	private JLabel lbCoordinate;
	private JTextField xPosField;
	private JTextField yPosField;
	private JTextField nameTextField;
	private JTextField mapidTextField;
	private int xPos;
	private int yPos;

	private Node existingNode = null;

	private ImageComponent imagePanel;

	private final static String COORDINATE = "Node Coordinate";
	private final static String TYPE = "Node Type";
	private final static String NAME = "Node Name";
	private final static String SAVE = "SAVE";
	private final static String ID = "Map ID";
	private final static String CANCEL = "Cancel";
	private JComboBox<String> comboBoxType;
	private DataModel model = null;

	/**
	 * Create the dialog.
	 */
	public NodeEditDialog(ImageComponent imageComponent,
						  DataModel pModel,
						  int xPosition, int yPosition) {
		xPos = xPosition;
		yPos = yPosition;
		model = pModel;
		imagePanel = imageComponent;
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Node information block
		lbCoordinate = new JLabel(COORDINATE);
		lbCoordinate.setFont(new Font("Arial", Font.PLAIN, 15));
		lbCoordinate.setBounds(45, 15, 147, 34);
		contentPanel.add(lbCoordinate);

		xPosField = new JTextField();
		xPosField.setEditable(false);
		xPosField.setFont(new Font("Arial", Font.PLAIN, 15));
		xPosField.setBounds(192, 18, 96, 27);
		xPosField.setText(String.valueOf(xPos));
		contentPanel.add(xPosField);
		xPosField.setColumns(10);

		yPosField = new JTextField();
		yPosField.setEditable(false);
		yPosField.setFont(new Font("Arial", Font.PLAIN, 15));
		yPosField.setBounds(303, 18, 96, 27);
		yPosField.setText(String.valueOf(yPos));
		contentPanel.add(yPosField);
		yPosField.setColumns(10);

		JLabel lbType = new JLabel(TYPE);
		lbType.setFont(new Font("Arial", Font.PLAIN, 15));
		lbType.setBounds(45, 62, 119, 21);
		contentPanel.add(lbType);

		JLabel lbName = new JLabel(NAME);
		lbName.setFont(new Font("Arial", Font.PLAIN, 15));
		lbName.setBounds(45, 106, 109, 21);
		contentPanel.add(lbName);

		nameTextField = new JTextField();
		nameTextField.setBounds(192, 102, 96, 27);
		nameTextField.setText("Location");
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblMapId = new JLabel(ID);
		lblMapId.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMapId.setBounds(45, 148, 81, 21);
		contentPanel.add(lblMapId);

		mapidTextField = new JTextField();
		mapidTextField.setBounds(192, 144, 96, 27);
		mapidTextField.setText(String.valueOf(model.getCurrentMapID()));
		contentPanel.add(mapidTextField);
		mapidTextField.setColumns(10);
		mapidTextField.setEnabled(false);
		mapidTextField.setDisabledTextColor(new Color(255,255,255));

		comboBoxType = new JComboBox<String>();
		comboBoxType.setBounds(192, 60, 207, 27);

		for (NodeType type : NodeType.values()) {
			comboBoxType.addItem(type.toString());

		}
		comboBoxType.setMaximumRowCount(5);
		contentPanel.add(comboBoxType);

		// SAVE and CANCEL button
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Arial", Font.PLAIN, 15));
		deleteButton.setActionCommand("Delete");
		deleteButton.addActionListener(this);
		buttonPane.add(deleteButton);
		deleteButton.setEnabled(false);
		deleteButton.setVisible(true);

		cancelButton = new JButton(CANCEL);
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 15));
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);


		saveButton = new JButton(SAVE);
		saveButton.setFont(new Font("Arial", Font.PLAIN, 15));
		saveButton.setActionCommand(SAVE);
		saveButton.addActionListener(this);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);


	}

	public NodeEditDialog(ImageComponent imageComponent,
						  DataModel pModel,
						  Node pNode) {
		this( imageComponent, pModel, pNode.getLocation().getX(), pNode.getLocation().getY());
		if (null == pNode) {
			return;
		}
		existingNode = pNode;
		deleteButton.setEnabled(true);
		comboBoxType.setSelectedItem(existingNode.getNodeType().toString());
		nameTextField.setText(existingNode.getName());
		mapidTextField.setText((new Integer(existingNode.getMap().getMapId())).toString());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(CANCEL))
			NodeEditDialog.this.setVisible(false);
		if (e.getActionCommand().equals(SAVE)) {
			// Check if fields are filled
			if (comboBoxType.getSelectedItem().toString().trim().equals("") || nameTextField.getText().trim().equals("")
					|| mapidTextField.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Please fill all fields.");
				return;
			}
			if ( existingNode == null){
				// Save node information
				Node node = new Node();
				Coordinate coordinate = new Coordinate(this.xPos, this.yPos);
				node.setLocation(coordinate);
				node.setMap(model.getCurrentMap());
				node.setName(nameTextField.getText());
				node.setNodeType(NodeType.valueOf(comboBoxType.getSelectedItem().toString()));

				// call database save function..
				// TODO: Maybe we can use mutlti-thread here..
				model.saveNode(node);

			}
			else {
                existingNode.setName(nameTextField.getText());
                existingNode.setNodeType(NodeType.valueOf(comboBoxType.getSelectedItem().toString()));
                model.editNode(existingNode);

			}
		}
		if (e.getActionCommand().equals("Delete")) {
            if (null != existingNode) {
                NodeDaoImpl dao = new NodeDaoImpl();
                dao.deleteNode(existingNode);
                Database.InitFromDatabase();

            }

        }
        this.setVisible(false);

	}

}
