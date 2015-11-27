package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.xml.crypto.Data;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.UIDataBuffer;
import com.wpi.cs509.teamA.ui.StateContext;
import com.wpi.cs509.teamA.ui.ImageComponent;

/**
 * JPanel that have input text fields and buttons which will be shown on the top
 * of the UI
 * 
 * TODO: we need a singleton here..
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ActionListener {

	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JButton adminLogin;
	private JButton signUp;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JButton btnNeighborManage;
	private JButton btnSynchronize;
	private JComboBox<String> comboBoxMap;
	private DefaultComboBoxModel<String> comboSourceModel;
	private DefaultComboBoxModel<String> comboDesModel;
	private JComboBox<String> sourceBox;
	private JComboBox<String> desBox;
	private final static String SEARCH = "Search";
	private final static String LOGIN = "Login";
	private final static String TO = "To: ";
	private final static String FROM = "From: ";
	private ImageComponent imageComponent;

	private int adminClicked;

	public void setStateContext(StateContext stateContext) {
		this.stateContext = stateContext;
	}

	private StateContext stateContext;


	/**
	 * Constructor. Initialize all the input panel.
	 */
	public InputPanel() {
		// // User input block
		this.startPoint = new JTextField();
		this.endPoint = new JTextField();
		this.btnSearch = new JButton(SEARCH);
		this.adminLogin = new JButton(LOGIN);
		this.btnNeighborManage = new JButton("Edges");
		btnNeighborManage.setSize(75, 30);
		btnNeighborManage.setLocation(80, 380);
		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.btnNeighborManage.setVisible(false);

		this.setLayout(null);
		// this.add(startPoint);
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);
		this.add(btnNeighborManage);

		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
		// this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		// this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getAdminLogin().setBounds(150, 0, 75, 30);
		this.getBtnSearch().setBounds(80, 300, 150, 38);

		this.signUp = new JButton("SignUp");
		this.add(signUp);
		this.signUp.addActionListener(this);
		this.signUp.setBounds(80, 0, 75, 30);

		// this.getStartPoint().setBounds(80, 150, 150, 38);
		// this.setBounds(0, 0, 1178, 516);

		lblFrom = new JLabel(FROM);
		lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom.setBounds(15, 155, 61, 16);
		add(lblFrom);

		lblTo = new JLabel(TO);
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(15, 230, 61, 16);
		add(lblTo);

		comboBoxMap = new JComboBox<String>();
		comboBoxMap.setBounds(80, 55, 150, 30);
		comboBoxMap.addItem("Campus Map");
		comboBoxMap.addItem("AK-G");
		comboBoxMap.addItem("AK-1");
		comboBoxMap.addItem("AK-2");
		comboBoxMap.addItem("AK-3");
		comboBoxMap.addItem("PC-1");
		comboBoxMap.addItem("PC-2");
		comboBoxMap.setMaximumRowCount(4);
		add(comboBoxMap);

		JLabel lblMap = new JLabel("Map: ");
		lblMap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMap.setBounds(15, 60, 61, 21);
		add(lblMap);

		comboSourceModel = new DefaultComboBoxModel<String>();
		comboDesModel = new DefaultComboBoxModel<String>();
		sourceBox = new JComboBox<String>(comboSourceModel);
		desBox = new JComboBox<String>(comboDesModel);

		// Add all nodes from
		// TODO delete all of this and replace with new
		List<Node> allNodes = Database.getAllNodeListFromDatabase();
		if (allNodes != null && allNodes.size() != 0) {
			for (int i = 0; i < allNodes.size(); i++) {
				Node tmp = allNodes.get(i);
				String name = tmp.getName().toString();
				if (name.equals("Location"))
					continue;
				comboSourceModel.addElement(allNodes.get(1).get(i).getName().toString());
				comboDesModel.addElement(allNodes.get(1).get(i).getName().toString());
			}
		}

		sourceBox.setBounds(80, 150, 150, 38);
		desBox.setBounds(80, 225, 150, 38);
		this.add(sourceBox);
		this.add(desBox);


		btnSynchronize = new JButton("Sync");
		btnSynchronize.addActionListener(this);
		btnSynchronize.setVisible(false);
		btnSynchronize.setBounds(155, 380, 75, 30);
		add(btnSynchronize);

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/logo_iteration1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(50, 480, 200, 200);
			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		adminClicked = 0;
		/**
		 * Add listener to the admin login button
		 */
		/*
		 * this.getAdminLogin().addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * 
		 * 
		 * });
		 */
		// TODO: Make the map related things into a enum class..
		this.getComboBoxMap().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int currentMapID = -1;
				if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("Campus Map")) {
					// selectImage("Final_Campus_Map", imageComponent);
					currentMapID = 1;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("AK-G")) {
					// selectImage("Final_AK_Ground_Floor", imageComponent);
					currentMapID = 2;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("AK-1")) {
					// selectImage("Final_AK_First_Floor", imageComponent);
					currentMapID = 3;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("AK-2")) {
					// selectImage("Final_AK_Second_Floor", imageComponent);
					currentMapID = 4;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("AK-3")) {
					// selectImage("Final_AK_Third_Floor", imageComponent);
					currentMapID = 5;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("PC-1")) {
					// selectImage("Final_Project_Center_First_Floor",
					// imageComponent);
					currentMapID = 6;
				} else if (InputPanel.this.getComboBoxMap().getSelectedItem().equals("PC-2")) {
					// selectImage("Final_Project_Center_Second_Floor",
					// imageComponent);
					currentMapID = 7;
				}
				//UIDataBuffer.setCurrentMapId(currentMapID);
				stateContext.setCurrentMap(currentMapID);
				//Database.InitFromDatabase();
				imageComponent.repaint();

			}

		});
	}


		  // Click the SEARCH button.. this.btnSearch.addActionListener(new
		  ActionListener() {

		  @Override public void actionPerformed (ActionEvent arg0){

		  // TODO: need to check if the input is valid!!

		  // TODO: make the AlgoController singleton and use setter and //
		 getter to operate the instance..

		  // We will go to the backend here.. For now, all the resources //
		  should be ready! AlgoController algoController = new
		  AlgoController(inputPanel.getSourcePoint(),
		  inputPanel.getDesPoint());



		  // get a list of a map, so that we can draw line on that map..
		  pathNodeList = null; pathNodeList =
		  result.get(UIDataBuffer.getCurrentMapId());

		  // get the result of the search.. List<Node> path =
		  algoController.getRoute(); stateContext.setPath(path); // we need to
		  give all the information to the repaint metho
		  imageComponent.repaint(); } });


		this.getAdminLogin().addActionListener(this);
                // TODO: need to check if the input is valid!!

	public void clickLogin() {
		if (adminClicked % 2 == 0) {
			AdminDialog adminDialog = new AdminDialog(imageComponent, InputPanel.this);
			adminDialog.setStateContext(stateContext);
			adminDialog.setModalityType(ModalityType.APPLICATION_MODAL);
			adminDialog.setVisible(isFocusable());
			// stateContext.switchToAdminUser();
			imageComponent.repaint();

		} else {

			JOptionPane.showMessageDialog(null, "You have logged out");
			Database.InitFromDatabase();
			// InputPanel.this.getBtnNeighborManage().setVisible(false);
			InputPanel.this.getAdminLogin().setText(LOGIN);
			// InputPanel.this.getBtnSynchronize().setVisible(false);
			this.incrementAdminClicked();
			stateContext.switchToState(new MouseActionSelectNode(stateContext));
			stateContext.switchUserState(new NormalUserState(stateContext));
			this.repaint();
			imageComponent.repaint();
		}
	}
	*/

	public void clickSignup() {
		SignupDialog signUpDialog = new SignupDialog(imageComponent, InputPanel.this);
		signUpDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		signUpDialog.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TODO: we need a pop up window here to verify the admin role.
		// If it is the admin, give it the admin mouse click event. If
		// not, give it normal user
		if (e.getSource() == getAdminLogin()) {
			clickLogin();
		}
		if (e.getSource() == signUp) {
			clickSignup();
		}
	

	}

	
	public void incrementAdminClicked() {
		this.adminClicked++;
	}

	public void setImageComponent(ImageComponent imageComponent2) {
		this.imageComponent = imageComponent2;

	}

    }

    public void setImageComponent(ImageComponent imageComponent2)
	{
        this.imageComponent = imageComponent2;
       
    }
	public JButton getBtnSynchronize() {
		return btnSynchronize;
	}

	public JComboBox<String> getComboBoxMap() {
		return comboBoxMap;
	}

	/**
	 * @return the startPoint
	 */
	public String getSourcePoint() {
		return sourceBox.getSelectedItem().toString();
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(JTextField startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endPoint
	 */
	public String getDesPoint() {
		return desBox.getSelectedItem().toString();
	}

	/**
	 * @param endPoint
	 *            the endPoint to set
	 */
	public void setEndPoint(JTextField endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the btnSearch
	 */
	public JButton getBtnSearch() {
		return btnSearch;
	}

	/**
	 * @param btnSearch
	 *            the btnSearch to set
	 */
	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	/**
	 * @return the adminLogin
	 */
	public JButton getAdminLogin() {
		return adminLogin;
	}

	/**
	 * @param adminLogin
	 *            the adminLogin to set
	 */
	public void setAdminLogin(JButton adminLogin) {
		this.adminLogin = adminLogin;
	}

	/**
	 * @return the btnNeighborManage
	 */
	public JButton getBtnNeighborManage() {
		return btnNeighborManage;
	}

	public JButton getSignUp() {
		return signUp;
	}

	public void setSignUp(JButton signUp) {
		this.signUp = signUp;
	}



};
