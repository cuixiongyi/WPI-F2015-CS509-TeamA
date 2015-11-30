package com.wpi.cs509.teamA.ui;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
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
public class InputPanel extends JPanel implements ActionListener, FocusListener {

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

    private final static String SEARCH = "Search";
    private final static String LOGIN = "Login";
    private final static String TO = "To: ";
    private final static String FROM = "From: ";
    private static final String SEARCHWORD = "Search WPI Maps";
    private static final String BUTTONWORD = "Search";
    private UserScreen userScreen;
    private AutoSuggestor autoSuggestorFrom;
    private AutoSuggestor autoSuggestorTo;

    private ImageComponent imageComponent;
    private DefaultListModel<String> mapListModel = new DefaultListModel<>();
    private JList<String> mapList;
    private ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();
    private JLabel picLabel;


    private int adminClicked = 0;

    public void setStateContext(StateContext stateContext) {

        this.stateContext = stateContext;
    }

    private StateContext stateContext;

    private JTextField txtFrom;
    private JTextField txtTo;

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

        this.getAdminLogin().setBounds(150, 0, 75, 30);
        this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
        this.getAdminLogin().addActionListener(this);

        this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
        //this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        //this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        this.getAdminLogin().setBounds(150, 0, 75, 30);
        this.getBtnSearch().setBounds(80, 300, 150, 38);
        this.getBtnSearch().addActionListener(this);

        // this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
        // this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));

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

        txtFrom = new JTextField();
        txtFrom.setBounds(80, 150, 150, 27);
        txtFrom.setText(SEARCHWORD);
        add(txtFrom);
        txtFrom.addFocusListener(this);
        txtFrom.setColumns(10);
        txtTo = new JTextField();
        txtTo.setBounds(80, 225, 150, 27);
        txtTo.addFocusListener(this);
        txtTo.setText(SEARCHWORD);
        add(txtTo);
        txtTo.setColumns(10);

        comboSourceModel = new DefaultComboBoxModel<String>();
        comboDesModel = new DefaultComboBoxModel<String>();

        // Add all nodes from
        // TODO delete all of this and replace with new
        List<Node> allNodes = Database.getAllNodeListFromDatabase();
        if (allNodes != null && allNodes.size() != 0) {
            for (int i = 0; i < allNodes.size(); i++) {
                Node tmp = allNodes.get(i);
                String name = tmp.getName().toString();
                if (name.equals("Location"))
                    continue;
                comboSourceModel.addElement(name);
                comboDesModel.addElement(name);
            }
        }



        btnSynchronize = new JButton("Sync");
        btnSynchronize.addActionListener(this);
        btnSynchronize.setVisible(false);
        btnSynchronize.setBounds(155, 380, 75, 30);
        add(btnSynchronize);

        BufferedImage logo;
        try {
            logo = ImageIO.read(new File(System.getProperty("user.dir") + "/BaseModel/src/logo_iteration1.png"));
            picLabel = new JLabel(new ImageIcon(logo));
            picLabel.setBounds(50, 480, 200, 200);
            add(picLabel);
            picLabel.setOpaque(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //result list
        mapList = new JList<>(mapListModel);
        mapList.setVisible(false);
        JScrollPane mapListScroll = new JScrollPane(mapList);
        mapListScroll.setBounds(50, 480, 200, 200);
        add(mapListScroll);


        mapList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int currentMapID = -1;
                    if (InputPanel.this.getMapList().getSelectedValue().equals("Campus Map")) {
                        currentMapID = 1;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Atwater Kent  Laboratories- GroundFloor")) {
                        currentMapID = 2;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Atwater Kent  Laboratories- First Floor 1st")) {
                        currentMapID = 3;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Atwater Kent  Laboratories- Second Floor 2nd")) {
                        currentMapID = 4;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Atwater Kent  Laboratories- Third Floor 3rd")) {
                        currentMapID = 5;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Project Center-First Floor 1st")) {
                        currentMapID = 6;
                    } else if (InputPanel.this.getMapList().getSelectedValue().equals("Project Center-Second Floor 2nd")) {
                        currentMapID = 7;
                    }

                    stateContext.setCurrentMap(currentMapID);
                    imageComponent.repaint();
                }
            }
        });


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

    public void focusLost(FocusEvent e) {
        if (((JTextField) e.getSource()).getText().trim().equals(""))
            ((JTextField) e.getSource()).setText(SEARCHWORD);
    }

    private void processTextField(JTextField txt) {
        if (txt.getText().trim().equals(SEARCHWORD))
            txt.setText("");
    }
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txtFrom || e.getSource() == txtTo) {
            stateContext.resetNodeFromText();
            processTextField((JTextField)e.getSource());
            Node node = stateContext.getNodeFromText();
            if (e.getSource() == txtFrom && null != node) {
                stateContext.setStartNode(node);
            }
            else if (e.getSource() == txtTo && null != node) {
                stateContext.setEndNode(node);
            }
        }

        stateContext.resetNodeFromText();
    }


	

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

	public void clickSignup() {
		SignupDialog signUpDialog = new SignupDialog(imageComponent, InputPanel.this);
		signUpDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		signUpDialog.setVisible(true);
	}
	
	public void clickSearch(){
		this.picLabel.setVisible(false);
		this.getMapList().setVisible(true);
		AlgoController algoController = new AlgoController(InputPanel.this.stateContext.getStartNode().getName(),
                InputPanel.this.stateContext.getEndNode().getName());

		Stack<Node> path = algoController.getRoute();
		
//		ArrayList<ArrayList<Node>> listOfLists = new ArrayList<ArrayList<Node>>();
		ArrayList<Node> singleMapPath = new ArrayList<Node>();
		ArrayList<String> mapNameList=new ArrayList<String>();
		int tmpMapId=path.peek().getMap().getMapId();
		mapNameList.add(path.peek().getMap().getMapName());
		while (path.size() > 0)
		{
			Node node = path.pop();
			if(node.getMap().getMapId()==tmpMapId)
			{
				singleMapPath.add(node);
			}
			else {
				multiMapPathLists.add(singleMapPath);
				singleMapPath=new ArrayList<Node>();
				singleMapPath.add(node);
				tmpMapId=node.getMap().getMapId();
				mapNameList.add(node.getMap().getMapName());
			}
		}
		multiMapPathLists.add(singleMapPath);
		
		//reset and initiate the Jlist
//		mapListModel=new DefaultListModel<>(); 
		for(String name:mapNameList)
		{
			mapListModel.addElement(name);
		}
		stateContext.setPath(multiMapPathLists.get(0));
		stateContext.setMultiMapPathLists(this.multiMapPathLists);
		stateContext.setCurrentMap(multiMapPathLists.get(0).get(0).getMap().getMapId());
		stateContext.getImageComponent().repaint();
		
	}

	/**
	 * This is Button click event
	 * @param e
     */
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
		if(e.getSource()==getBtnSearch())
		{
			clickSearch();
		}
	

	}
	

	
	public void incrementAdminClicked() {
		this.adminClicked++;
	}

	public void setImageComponent(ImageComponent imageComponent2) {
		this.imageComponent = imageComponent2;
    }


	public JButton getBtnSynchronize() {
		return btnSynchronize;
	}

	public JComboBox<String> getComboBoxMap() {
		return comboBoxMap;
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(JTextField startPoint) {
		this.startPoint = startPoint;
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

	public DefaultListModel<String> getMapListModel() {
		return mapListModel;
	}

	public void setMapListModel(DefaultListModel<String> mapListModel) {
		this.mapListModel = mapListModel;
	}

	public JList<String> getMapList() {
		return mapList;
	}

	public void setMapList(JList<String> mapList) {
		this.mapList = mapList;
	}

    public void setUserScreen(UserScreen userScreen2) {
        this.userScreen = userScreen2;

        autoSuggestorFrom = new AutoSuggestor(txtFrom, userScreen, null, Color.WHITE.brighter(), Color.BLACK,
                Color.GRAY, 0.75f, 0.0, 0.0, stateContext, AutoSuggestor.SetNodeOption.setStartNode);
        autoSuggestorFrom.setInputPanel(this);
        autoSuggestorTo = new AutoSuggestor(txtTo, userScreen, null, Color.WHITE.brighter(), Color.BLACK,
                Color.GRAY, 0.75f, 0.0, 0.0, stateContext, AutoSuggestor.SetNodeOption.setEndNode);
        autoSuggestorTo.setInputPanel(this);
    }
	
	
	


};
