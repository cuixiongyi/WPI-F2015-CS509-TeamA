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
import javax.swing.JComboBox;

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
	private JButton btnNeighborManage;
	private JComboBox comboBoxMap;
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
		this.btnNeighborManage = new JButton("NEIGHBOR");
		btnNeighborManage.setSize(150, 30);
		btnNeighborManage.setLocation(80, 55);
		this.btnNeighborManage.setVisible(false);

		this.setLayout(null);
		this.add(startPoint);
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);
		this.add(btnNeighborManage);

		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
		this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getAdminLogin().setBounds(80, 0, 150, 30);
		this.getBtnSearch().setBounds(80, 258, 150, 38);
		this.getEndPoint().setBounds(80, 188, 150, 38);
		this.getStartPoint().setBounds(80, 114, 150, 38);
		this.setBounds(0, 0, 1178, 516);
	
		
		
		lblFrom = new JLabel(FROM);
		lblFrom.setBounds(15, 122, 61, 16);
		add(lblFrom);
		
		lblTo = new JLabel(TO);
		lblTo.setBounds(15, 196, 61, 16);
		add(lblTo);
		
		comboBoxMap = new JComboBox();
		comboBoxMap.setBounds(80, 345, 150, 27);
		comboBoxMap.addItem("Campus Map");
		comboBoxMap.addItem("AK-G");
		comboBoxMap.addItem("AK-1");
		comboBoxMap.addItem("AK-2");
		comboBoxMap.addItem("AK-3");
		comboBoxMap.addItem("AK-4");
		comboBoxMap.addItem("PC-1");
		comboBoxMap.addItem("PC-2");
		add(comboBoxMap);
		
		JLabel lblMap = new JLabel("Map: ");
		lblMap.setBounds(15, 348, 61, 21);
		add(lblMap);
		
	}
	
	public JComboBox getComboBoxMap() {
		return comboBoxMap;
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

	/**
	 * @return the btnNeighborManage
	 */
	public JButton getBtnNeighborManage() {
		return btnNeighborManage;
	}
}
