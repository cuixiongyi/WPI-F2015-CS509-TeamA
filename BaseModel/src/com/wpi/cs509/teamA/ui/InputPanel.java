package com.wpi.cs509.teamA.ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class InputPanel extends JPanel {

	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JButton adminLogin;
	private JLabel lblFrom;
	private JLabel lblTo;
	

	private final static String START = "Start";
	private final static String END = "End";
	private final static String SEARCH = "Search";
	private final static String LOGIN = "Login";
	private final static String TO = "To: ";
	private final static String FROM = "From: ";

	/**
	 * Constructor. Initialize all the input panel.
	 */
	public InputPanel() {
//		// User input block
		this.startPoint = new JTextField();
		this.endPoint = new JTextField();
		this.btnSearch = new JButton(SEARCH);
		this.adminLogin = new JButton(LOGIN);

		this.setLayout(null);
		this.add(startPoint);
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);

		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
		this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getAdminLogin().setBounds(210, 0, 60, 30);
		this.getBtnSearch().setBounds(50, 300, 150, 38);
		this.getEndPoint().setBounds(50, 200, 150, 38);
		this.getStartPoint().setBounds(50, 100, 150, 38);
		this.setBounds(0, 0, 1178, 214);
		
		lblFrom = new JLabel(FROM);
		lblFrom.setBounds(15, 110, 61, 16);
		add(lblFrom);
		
		lblTo = new JLabel(TO);
		lblTo.setBounds(30, 210, 61, 16);
		add(lblTo);
		
//		
//		setBounds(100, 100, 200, 450);
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{50,50,50,50};
//		gridBagLayout.rowHeights = new int[]{50,50,50,50,50,50,50,50,50};
//		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
//		setLayout(gridBagLayout);
//		
//		JButton adminLogin = new JButton("Log in");
//		GridBagConstraints gbc_adminLogin = new GridBagConstraints();
//		gbc_adminLogin.insets = new Insets(0, 0, 5, 0);
//		gbc_adminLogin.gridx = 3;
//		gbc_adminLogin.gridy = 0;
//		add(adminLogin, gbc_adminLogin);
//		
//		lblFrom = new JLabel("From:");
//		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
//		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
//		gbc_lblFrom.anchor = GridBagConstraints.SOUTHWEST;
//		gbc_lblFrom.gridx = 0;
//		gbc_lblFrom.gridy = 1;
//		add(lblFrom, gbc_lblFrom);
//		
//		lblTo = new JLabel("To:");
//		GridBagConstraints gbc_lblTo = new GridBagConstraints();
//		gbc_lblTo.insets = new Insets(0, 0, 0, 5);
//		gbc_lblTo.anchor = GridBagConstraints.SOUTHWEST;
//		gbc_lblTo.gridx = 0;
//		gbc_lblTo.gridy = 3;
//		add(lblTo, gbc_lblTo);
//		
//		JButton btnSearch = new JButton("Search");
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
//		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
//		gbc_btnSearch.gridx = 3;
//		gbc_btnSearch.gridy = 3;
//		add(btnSearch, gbc_btnSearch);
//		
//		startPoint = new JTextField();
//		GridBagConstraints gbc_startPoint = new GridBagConstraints();
//		gbc_startPoint.anchor = GridBagConstraints.NORTH;
//		gbc_startPoint.insets = new Insets(0, 0, 5, 5);
//		gbc_startPoint.fill = GridBagConstraints.HORIZONTAL;
//		gbc_startPoint.gridwidth = 3;
//		gbc_startPoint.gridx = 0;
//		gbc_startPoint.gridy = 2;
//		add(startPoint, gbc_startPoint);
//		startPoint.setColumns(10);
//		
//		endPoint = new JTextField();
//		GridBagConstraints gbc_endPoint = new GridBagConstraints();
//		gbc_endPoint.anchor = GridBagConstraints.NORTH;
//		gbc_endPoint.insets = new Insets(0, 0, 0, 5);
//		gbc_endPoint.fill = GridBagConstraints.HORIZONTAL;
//		gbc_endPoint.gridwidth = 3;
//		gbc_endPoint.gridx = 0;
//		gbc_endPoint.gridy = 4;
//		add(endPoint, gbc_endPoint);
//		endPoint.setColumns(10);
	}

	/**
	 * @return the startPoint
	 */
	public JTextField getStartPoint() {
		return startPoint;
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
	public JTextField getEndPoint() {
		return endPoint;
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
}
