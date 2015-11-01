package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

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
	private ImageComponent imgComponent;
	private JScrollPane imgScrollPanel;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;

	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {
		
		System.out.println("initialize user screen..");

		container = getContentPane();
		container.setLayout(new BorderLayout());

		inputPanel = new InputPanel();

		// the panel to show image
		imgComponent = new ImageComponent(inputPanel);
		// display the image
		imgComponent.setImagePath(System.getProperty("user.dir") + "\\src\\CSP.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);

		// scroll panel
		imgScrollPanel = new JScrollPane();
		imgScrollPanel.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		// for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		container.add(imgScrollPanel, BorderLayout.CENTER);
		container.add(inputPanel, BorderLayout.NORTH);

		setTitle("Route Finder");
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
	 * The entrance of the program
	 * 
	 * @param args
	 *            command line..
	 */
	public static void main(String[] args) {
				
		// singleton
		EventQueue.invokeLater(new Runnable() {
		      public void run() {
		    	  
		    	  UserScreen.launchUserScreen();	    	  		    	  
		      }
		});

		new SystemFacade();
	
	}

}