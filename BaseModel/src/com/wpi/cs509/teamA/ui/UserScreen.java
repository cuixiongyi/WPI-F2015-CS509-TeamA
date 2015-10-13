package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 * This is the class that construct the main user interface of the application
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class UserScreen extends JFrame {

	private static UserScreen userScreen;

	private Container container;

	private ImageComponent imgPanel;
	private JScrollPane imgSp;
	/*
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;

	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {
		container = getContentPane();
		container.setLayout(new BorderLayout());

		inputPanel = new InputPanel();

		// the panel to show image
		imgPanel = new ImageComponent(inputPanel);

		// display the image
		imgPanel.setImagePath(System.getProperty("user.dir") + "\\src\\CSP.jpg");
		imgPanel.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		imgPanel.setVisible(true);

		// add mouse listener
		// imgPanel.addMouseListener(imgPanel);

		// scroll panel
		imgSp = new JScrollPane();
		imgSp.setPreferredSize(new Dimension(imgPanel.getImgWidth(), imgPanel.getImgHeight()));
		// for scroll panel
		imgSp.setViewportView(imgPanel);
		imgSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		container.add(imgSp, BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);

		setTitle("Path Finding");
		setLocation(0, 0);
		setSize(1200, 900);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static UserScreen launchUserScreen() {
		if (userScreen == null) {
			userScreen = new UserScreen();
		}

		return userScreen;
	}

	/**
	 * The start of the program
	 * 
	 * @param args
	 *            command line..
	 */
	public static void main(String[] args) {
		new SystemFacade();
	}

}