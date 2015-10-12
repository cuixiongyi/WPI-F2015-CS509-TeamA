package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JPanel that have input text fields and buttons which will be shown on the top
 * of the UI
 * 
 * @author CS 509-Team A
 *
 */
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

		this.setLayout(new GridLayout(1, 7));
		this.add(new JLabel("From: "));
		this.add(startPoint);
		this.add(new JLabel("To: "));
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);
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
