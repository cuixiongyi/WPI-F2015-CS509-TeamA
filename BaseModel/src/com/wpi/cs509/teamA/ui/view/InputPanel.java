package com.wpi.cs509.teamA.ui.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.*;
import com.wpi.cs509.teamA.util.*;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.AutoSuggestUtil.AutoSuggestor;



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
    private MainModel model = null;

    private JButton btnSearch;
    private JButton adminLogin;
    private JButton signUp;
    private JLabel lblFrom;
    private JLabel lblTo;
	private JLabel lblSwapStartEnd;
    private JButton btnNeighborManage;
    private JButton btnSynchronize;
    private JButton classroomFilter;
    private JButton officeFilter;
    private JButton restroomFilter;
    private JButton labFilter;
    private JButton parkingFilter;
    private JButton openMap;
	private JButton allFilter;
	private JButton clearFilter;

	private JToggleButton btnMngNode;
	private JToggleButton btnMngEdge;
	private JToggleButton btnEditNodeInfo;

    JTabbedPane tabbedPane;


    private static int numNodeBtn;
	private static int numEdgeBtn;

    private JFileChooser fc;
    private JComboBox<String> comboBoxMap;

	private UserScreen userScreen;
	private AutoSuggestor autoSuggestorFrom;
	private AutoSuggestor autoSuggestorTo;
	private boolean lastSetEmptySearchWord = false;
	private boolean lastSetSearchWord = false;

	private DefaultListModel<String> mapListModel = new DefaultListModel<>();
	private JList<String> mapList;
	private ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();
	private JLabel picLabel;

	private int adminClicked = 0;

	private JTextField txtFrom;
	private JTextField txtTo;

	/**
	 * Constructor. Initialize all the input panel.
	 */
	public InputPanel() {
		// // User input block

		this.setLayout(null);

        tabbedPane = new JTabbedPane();
		JPanel searchResultTab = new JPanel();
		JPanel filterTab = new JPanel();
		JPanel adminTab = new JPanel();
		filterTab.setLayout(null);

		this.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.setBounds(0, 230, 300, 550);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		tabbedPane.addTab("Result", searchResultTab);
		tabbedPane.addTab("Filter", filterTab);
		tabbedPane.addTab("Admin", adminTab);

		adminTab.setLayout(null);

		// login panel
		this.adminLogin = new JButton(UIConstant.LOGIN);
		this.getBtnLogin().setBounds(150, 0, 75, 30);
		this.getBtnLogin().addActionListener(this);
		this.add(adminLogin);

		this.signUp = new JButton("SignUp");
		this.add(signUp);
		this.signUp.addActionListener(this);
		this.signUp.setBounds(80, 0, 75, 30);

		// search panel
		JLabel lblMap = new JLabel("Map: ");
		lblMap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMap.setBounds(15, 60, 61, 21);
		add(lblMap);

		comboBoxMap = new JComboBox<String>();
		comboBoxMap.setBounds(80, 55, 150, 30);
		List<GeneralMap> allMapList = Database.getAllMapFromDatabase();
		for (GeneralMap map : allMapList) {
			comboBoxMap.addItem(map.getMapAbbrName());
		}
		comboBoxMap.setMaximumRowCount(6);
		comboBoxMap.addFocusListener(this);

		comboBoxMap.requestFocus();
		add(comboBoxMap);

		lblFrom = new JLabel();
		lblFrom.setIcon(NodeIcon.getStartIconSmall());
		lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom.setBounds(15, 105, 61, 16);
		add(lblFrom);

		txtFrom = new JTextField();
		txtFrom.setBounds(80, 100, 150, 29);
		txtFrom.setText(UIConstant.SEARCHWORD);
		add(txtFrom);
		txtFrom.addFocusListener(this);
		txtFrom.setColumns(10);

		lblTo = new JLabel();
		lblTo.setIcon(NodeIcon.getEndIconSmall());
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(15, 130, 61, 16);
		add(lblTo);

		lblSwapStartEnd =new JLabel();
		lblSwapStartEnd.setIcon(NodeIcon.getRotateIcon());
		lblSwapStartEnd.setBounds(232,112,20,30);
		add(lblSwapStartEnd);

		txtTo = new JTextField();
		txtTo.setBounds(80, 125, 150, 29);
		txtTo.addFocusListener(this);
		txtTo.setText(UIConstant.SEARCHWORD);
		add(txtTo);
		txtTo.setColumns(10);

		this.btnSearch = new JButton(UIConstant.SEARCH);
		this.add(btnSearch);
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
		this.getBtnSearch().setBounds(80, 160, 150, 38);
		this.getBtnSearch().addActionListener(this);

		// tab panel-search result
		mapList = new JList<>();
		mapList.setPreferredSize(new Dimension(250, 430));
		// mapList.setFixedCellHeight(40);
		mapList.setCellRenderer(new MarioListRenderer());
		searchResultTab.add(mapList);
		/// for test
		DefaultListModel<String> model = new DefaultListModel<>();

		// tab panel-filter
		this.clearFilter = new JButton("ALL");
		this.classroomFilter = new JButton("Classrooms", new ImageIcon(NodeIcon.getClassroomIcon()));
		this.officeFilter = new JButton("Offices", new ImageIcon(NodeIcon.getOfficeIcon()));
		this.restroomFilter = new JButton("Restrooms", new ImageIcon(NodeIcon.getRestroomIcon()));
		this.labFilter = new JButton("Labs", new ImageIcon(NodeIcon.getLabIcon()));
		this.parkingFilter = new JButton("Parking", new ImageIcon(NodeIcon.getParkingIcon()));
		this.allFilter = new JButton("CLEAR");

		ArrayList<JButton> filterButtons = new ArrayList<JButton>();
		filterButtons.add(clearFilter);
		filterButtons.add(classroomFilter);
		filterButtons.add(officeFilter);
		filterButtons.add(restroomFilter);
		filterButtons.add(labFilter);
		filterButtons.add(parkingFilter);
		filterButtons.add(allFilter);

		int filterIconWidth = 255;
		int filterIconHeight = 50;
		int filterYspacing = 10;
		int filterXpos = 15;
		int filterYpos = 10;
		int iconTextGap = 30;
		Font buttonFont = new Font("Arial", Font.BOLD, 20);

		for (JButton button : filterButtons) {
			button.setBounds(filterXpos, filterYpos, filterIconWidth, filterIconHeight);
			// set alignment for buttons without icons
			if (button.equals(clearFilter) || button.equals(allFilter)) {
				button.setHorizontalAlignment(SwingConstants.CENTER);
			}
			// set alignment for buttons with icons
			else {
				button.setHorizontalAlignment(SwingConstants.LEFT);
				if (button.equals(officeFilter)) {
					button.setIconTextGap(iconTextGap - 5);
				} else if (button.equals(classroomFilter)) {
					button.setIconTextGap(iconTextGap - 2);
				} else {
					button.setIconTextGap(iconTextGap);
				}
			}
			button.setFont(buttonFont);
			filterYpos += filterYspacing + filterIconHeight;
			filterTab.add(button);
		}

		// tab panel-admin tool
		
		

		openMap=new JButton("Add Map");
//		adminTab.add(openMap);
//		openMap.setFont(buttonFont);
		
		btnMngNode = new JToggleButton("Manage Node");
//		adminTab.add(btnMngNode);
//		btnMngNode.setFont(buttonFont);

		btnMngEdge = new JToggleButton("Manage Edge");
//		btnMngEdge.setFont(buttonFont);
//		adminTab.add(btnMngEdge);

		btnEditNodeInfo =new JToggleButton("Edit Node Information");
		
		ArrayList<AbstractButton> adminButtons = new ArrayList<AbstractButton>();
		adminButtons.add(openMap);
		adminButtons.add(btnMngNode);
		adminButtons.add(btnMngEdge);
		adminButtons.add(btnEditNodeInfo);
		
		int adminIconWidth = 255;
		int adminIconHeight = 50;
		int adminYspacing = 10;
		int adminXpos = 15;
		int adminYpos = 10;
		
		for (AbstractButton button : adminButtons)
		{
			button.setBounds(adminXpos, adminYpos, adminIconWidth, adminIconHeight);
			button.setFont(buttonFont);
			adminYpos += adminYspacing + adminIconHeight;
			adminTab.add(button);
		}
		
		
		BufferedImage logo;
		try {
			logo = ImageIO.read(new File(PaintHelperBasics.getUserDir() + "logo_iteration1.png"));
			// picLabel = new JLabel(new ImageIcon(logo));
			// picLabel.setBounds(50, 480, 200, 200);
			// add(picLabel);
			// picLabel.setOpaque(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		numNodeBtn = 0;
		numEdgeBtn = 0;

		

		// result list

	}

	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (null != model.getCurrentPath())
            getMapList().setSelectedIndex(model.getCurrentPathIdx());

        if (null != model.getMyAccount() && model.getMyAccount().isAdmin()) {

            getBtnMngEdge().setEnabled(true);
            getBtnMngNode().setEnabled(true);
            getOpenMap().setEnabled(true);
            tabbedPane.setEnabledAt(2, true);

        }
        else {
            getBtnMngEdge().setEnabled(false);
            getBtnMngNode().setEnabled(false);
            getOpenMap().setEnabled(false);
            tabbedPane.setEnabledAt(2, false);
        }
        Path path = model.getCurrentPath();
		if (null == model.getPaths()) {
            DefaultListModel<String> mapListModel = new DefaultListModel<>();
            getMapList().setModel(mapListModel);
        }
        if (null != model.getCurrentMap()) {
            getComboBoxMap().setSelectedIndex(model.getCurrentMap().getMapId()-1);
        }
        if (null != model.getStartNode()) {
//            getFromText().setText(model.getStartNode().getName());
        }
    }

	public void focusLost(FocusEvent e) {
	
	}

	private void processTextField(JTextField txt) {
		if (txt.getText().trim().equals(UIConstant.SEARCHWORD)) {
			// if (!lastSetSearchWord) {
			// lastSetEmptySearchWord = true;
			// lastSetSearchWord = false;
			txt.setText("");
			// }
		}
		/*
		 * else if (lastSetSearchWord){ lastSetSearchWord = false;
		 * lastSetEmptySearchWord = false; }
		 */
	}

	public void focusGained(FocusEvent e) {
		if (e.getSource() == txtFrom || e.getSource() == txtTo) {
			processTextField((JTextField) e.getSource());

		} else if (e.getSource() == comboBoxMap) {
			this.autoSuggestorFrom.getAutoSuggestionPopUpWindow().setVisible(false);
			this.autoSuggestorTo.getAutoSuggestionPopUpWindow().setVisible(false);

		}

	}

	/**
	 * This is Button click event
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TODO: we need a pop up window here to verify the admin role.
		// If it is the admin, give it the admin mouse click event. If
		// not, give it normal user
		if (e.getSource() == getBtnLogin()) {

		}
		if (e.getSource() == signUp) {

		}
		if (e.getSource() == getBtnSearch()) {

		}
	

	}
	

	
	public void incrementAdminClicked() {
		this.adminClicked++;
	}



	public JButton getBtnSynchronize() {
		return btnSynchronize;
	}

	public JComboBox<String> getComboBoxMap() {
		return comboBoxMap;
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
	public JButton getBtnLogin() {
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

	public JButton getBtnSignUp() {
		return signUp;
	}

	public void setSignUp(JButton signUp) {
		this.signUp = signUp;
	}

	public JList<String> getMapList() {
		return mapList;
	}

	public void setMapList(JList<String> mapList) {
		this.mapList = mapList;
	}

	public void setUserScreen(UserScreen userScreen2) {
		this.userScreen = userScreen2;

		autoSuggestorFrom = new AutoSuggestor(txtFrom, userScreen, null, Color.GRAY, 0.75f, 0.0, 0.0, model,
				AutoSuggestor.SetNodeOption.setStartNode);
		autoSuggestorFrom.setInputPanel(this);
		autoSuggestorTo = new AutoSuggestor(txtTo, userScreen, null, Color.GRAY, 0.75f, 0.0, 0.0, model,
				AutoSuggestor.SetNodeOption.setEndNode);
		autoSuggestorTo.setInputPanel(this);
	}

	public AutoSuggestor getAutoSuggestorFrom() {
		return autoSuggestorFrom;
	}

	public AutoSuggestor getAutoSuggestorTo() {
		return autoSuggestorTo;
	}

	public JTextField getToText() {
		return txtTo;
	}

	public JTextField getFromText() {
		return txtFrom;
	}

	public void setModel(MainModel pmodel) {
		this.model = pmodel;
	}

	/**
	 * @return the classroomFilter
	 */
	public JButton getClassroomFilter() {
		return classroomFilter;
	}

	/**
	 * @return the officeFilter
	 */
	public JButton getOfficeFilter() {
		return officeFilter;
	}

	/**
	 * @return the restroomFilter
	 */
	public JButton getRestroomFilter() {
		return restroomFilter;
	}

	/**
	 * @return the labFilter
	 */
	public JButton getLabFilter() {
		return labFilter;
	}

	/**
	 * @return the parkingFilter
	 */
	public JButton getParkingFilter() {
		return parkingFilter;
	}

	public JButton getOpenMap() {
		return openMap;
	}

	public void setOpenMap(JButton openMap) {
		this.openMap = openMap;
	}

	public JButton getClearFilter() {
		return clearFilter;
	}

	
	public JButton getAllFilter() 
	{
		return allFilter;

	}

	public JToggleButton getBtnMngNode() {
		return btnMngNode;
	}

	public JToggleButton getBtnMngEdge() {
		return btnMngEdge;

	}

	public JLabel getLblSwapStartEnd() {
		return lblSwapStartEnd;
	}

	public JToggleButton getBtnEditNodeInfo() {
		return btnEditNodeInfo;
	}
};
