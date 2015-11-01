package com.wpi.cs509.teamA.ui;

import java.awt.Font;
import java.awt.GridLayout;
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

	/**
	 * Constructor. Initialize all the input panel.
	 */
	public InputPanel() {

		this.startPoint = new JTextField("Start");
		this.endPoint = new JTextField("End");
		this.btnSearch = new JButton("Search");
		this.adminLogin = new JButton("Login as Admin");
		
		this.setLayout(null);
		this.add(new JLabel("From: "));
		this.add(startPoint);
		this.add(new JLabel("To: "));
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);
		
		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 20));
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 25));
		this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 22));
		this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 22));
		this.getAdminLogin().setBounds(981, 0, 196, 29);
		this.getBtnSearch().setBounds(603, 69, 132, 89);
		this.getEndPoint().setBounds(200, 120, 310, 38);
		this.getStartPoint().setBounds(200, 69, 310, 38);
		this.setBounds(0, 0, 1178, 214);
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
