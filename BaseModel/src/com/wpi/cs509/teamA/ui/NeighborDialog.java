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

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.util.Coordinate;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;

import com.wpi.cs509.teamA.util.Database;
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

	private ImageComponent imageComponent;

	private final static String SAVE = "Save";
	private final static String CANCEL = "Cancel";
	private final static String NEIGHBOR = "Neighbor Pairs";

	public void setStateContext(StateContext stateContext) {
		this.stateContext = stateContext;
	}

	private StateContext stateContext;
	/**
	 * Create the dialog.
	 */
	public NeighborDialog(ImageComponent imageComponent) {
		this.imageComponent = imageComponent;
		setTitle(NEIGHBOR);
		setBounds(100, 100, 250, 380);
		getContentPane().setLayout(null);

		// set up SAVE and CANCEL button
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 320, 220, 35);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		saveButton = new JButton(SAVE);
		saveButton.setActionCommand(SAVE);
		saveButton.addActionListener(this);
		saveButton.setSize(80, 35);
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);

		cancelButton = new JButton(CANCEL);
		cancelButton.setActionCommand(CANCEL);
		cancelButton.addActionListener(this);
		cancelButton.setSize(70, 35);
		buttonPane.add(cancelButton);

		// Edge Pairs
		pairPanel = new JPanel();
		pairPanel.setBounds(0, 0, 200, 320);
		getContentPane().add(pairPanel);
		pairPanel.setLayout(new GridLayout(0, 3, 0, 0));

		for (int i = 0; i < 10; i++) {

			// pair
			String edgeName = "Edge " + (i + 1);
			lbPair[i] = new JLabel(edgeName);
			lbPair[i].setHorizontalAlignment(SwingConstants.RIGHT);
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

		Node tmp = Database.getNodeFromCoordinate(new Coordinate(xPos, yPos), stateContext.getCurrentMap().getMapId());
		if (null != tmp) {
			xPos = tmp.getLocation().getX();
			yPos = tmp.getLocation().getY();
			this.getCoordinateTextField.setText(String.valueOf(xPos) + " , " + String.valueOf(yPos));
		}

	}

	private Edge getEdgeToSave(JTextField tf1, JTextField tf2) {

		if (tf1 == null || tf2 == null) {
			return null;
		}

		Coordinate startCorrdinate;
		Coordinate endCorrdinate;

		startCorrdinate = this.getCoordinate(tf1);
		endCorrdinate = this.getCoordinate(tf2);

		if ((startCorrdinate != null) && (endCorrdinate != null)) {
			Edge edge = new Edge();
			/// TODO this is a hack
			//nodeRelation.setFirstNodeCoordinate(startCorrdinate);
			//nodeRelation.setSecondNodeCoordinate(endCorrdinate);
			return edge;
		}

		return null;
	}

	// TODO: Remove this method and use static method from Coordinate class
	// instead..
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

			Set<Edge> edgesToSave = new HashSet<Edge>();
			for (int i = 0; i < 10; i++) {
				Edge edge = this.getEdgeToSave(textFieldNodePair[2 * i], textFieldNodePair[2 * i + 1]);
				if (edge != null) {
					edgesToSave.add(edge);
				}
			}

			// database..
		//	NodeRelationDao nrd = new NodeRelationDaoImpl();
		//	nrd.insertMultipleEdges(edgesToSave);
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
