package com.wpi.cs509.teamA.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.util.Coordinate;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import com.wpi.cs509.teamA.util.UIDataBuffer;

/**
 * This is the class that administrators uses to input edges
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class NeighborDialog extends JDialog implements ActionListener, FocusListener {

	private final static int nodeRange = 10;

	private JButton saveButton;
	private JButton cancelButton;

	private JPanel pairPanel;
	// Label and Text Field
	private JLabel[] lbPair = new JLabel[10];
	private JTextField[] textFieldNodePair = new JTextField[20];
	private JTextField getCoordinateTextField = null;

//	private List<Coordinate> coordinateList = new ArrayList<Coordinate>();
	private ImageComponent imageComponent;

	private final static String SAVE = "Save";
	private final static String CANCEL = "Cancel";
	private final static String NEIGHBOR = "Neighbor Pairs";

	/**
	 * Create the dialog.
	 */
	public NeighborDialog(ImageComponent imageComponent) {
		this.imageComponent = imageComponent;
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

		// Edge Pairs
		pairPanel = new JPanel();
		pairPanel.setBounds(0, 0, 428, 641);
		getContentPane().add(pairPanel);
		pairPanel.setLayout(new GridLayout(0, 3, 0, 0));

		for (int i = 0; i < 10; i++) {

			// pair
			String edgeName = "Edge " + (i + 1);
			lbPair[i] = new JLabel(edgeName);
			pairPanel.add(lbPair[i]);

			for (int j = 2 * i, num = 2 * i + 1; j <= num; j++) {

				// must initialize like this, cannot use other reference
				textFieldNodePair[j] = new JTextField();
				pairPanel.add(textFieldNodePair[j]);
				textFieldNodePair[j].addFocusListener(this);
				textFieldNodePair[j].setColumns(10);
			}
		}
	}

	public void setFieldTitle(int xPos, int yPos) {

		Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();
		
		
		
		boolean sameNode = false;
		for (int i = 0; i < allNodes.get(1).size(); i++) {
			if (Math.abs(xPos - allNodes.get(1).get(i).getLocation().getX()) < nodeRange
					|| Math.abs(yPos - allNodes.get(1).get(i).getLocation().getY()) < nodeRange) {
				sameNode = true;
				xPos = allNodes.get(1).get(i).getLocation().getX();
				yPos = allNodes.get(1).get(i).getLocation().getY();
			}
		}
		if (sameNode) {
			this.getCoordinateTextField.setText(String.valueOf(xPos) + " , " + String.valueOf(yPos));
		}
		

	}

	/**
	 * @param List<Coordinate>
	 *            coordinateList return the nodelist
	 */
//	public List<Coordinate> getCoorList() {
//		return coordinateList;
//	}
//
//	public void addNodeList(int x, int y) {
//		Coordinate coor = new Coordinate(x, y);
//		coordinateList.add(coor);
//	}

	private NodeRelation getEdgeToSave(JTextField tf1, JTextField tf2) {

		if (tf1 == null || tf2 == null) {
			return null;
		}

		Coordinate startCorrdinate;
		Coordinate endCorrdinate;

		startCorrdinate = this.getCoordinate(tf1);
		endCorrdinate = this.getCoordinate(tf2);

		if ((startCorrdinate != null) && (endCorrdinate != null)) {
			NodeRelation nodeRelation = new NodeRelation();
			nodeRelation.setFirstNodeCoordinate(startCorrdinate);
			nodeRelation.setSecondNodeCoordinate(endCorrdinate);
			return nodeRelation;
		}

		return null;
	}

	private Coordinate getCoordinate(JTextField textField) {
		Coordinate resCorrdinate = new Coordinate();
		String[] corrdinate = (textField.getText()).split(",");
		if (corrdinate.length == 2) {
			resCorrdinate.setX(Integer.valueOf(corrdinate[0].trim()));
			resCorrdinate.setY(Integer.valueOf(corrdinate[1].trim()));

			return resCorrdinate;
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(CANCEL))
			NeighborDialog.this.setVisible(false);

		if (e.getActionCommand().equals(SAVE)) {

			this.setVisible(false);

			Set<NodeRelation> edgesToSave = new HashSet<NodeRelation>();
			for (int i = 0; i < 10; i++) {
				NodeRelation edge = this.getEdgeToSave(textFieldNodePair[2 * i], textFieldNodePair[2 * i + 1]);
				if (edge != null) {
					edgesToSave.add(edge);
				}
			}

			// database..
			NodeRelationDao nrd = new NodeRelationDaoImpl();
			nrd.insertMultipleEdges(edgesToSave);
			imageComponent.repaint();

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
