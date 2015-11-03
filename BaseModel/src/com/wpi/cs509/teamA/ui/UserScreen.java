package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private NeighborDialog neighborDialog;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;
	private JButton btnNeighborManage;

	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {

		container = getContentPane();

		// input panel and components
		inputPanel = new InputPanel();
		
		neighborDialog = new NeighborDialog();
		neighborDialog.setVisible(false);

		// the panel to show image
		imgComponent = new ImageComponent(inputPanel, this, neighborDialog);

		// display the image
		imgComponent.setImagePath(System.getProperty("user.dir") + "/src/Final_AK_First_Floor.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);
		getContentPane().setLayout(null);
		// add listener
		// imgComponent.addMouseListener(imgComponent);

		// scroll panel
		imgScrollPanel = new JScrollPane();
		imgScrollPanel.setBounds(181, 215, 834, 557);
		imgScrollPanel.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		// for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// button to change map
		BasicArrowButton WestArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
		WestArrowButton.setBounds(109, 632, 57, 140);

		BasicArrowButton EastArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
		EastArrowButton.setBounds(1030, 632, 57, 140);

		container.add(WestArrowButton);
		container.add(EastArrowButton);
		container.add(imgScrollPanel);
		container.add(inputPanel);
		

		btnNeighborManage = new JButton("Neighbor Manage Tool");
		btnNeighborManage.setVisible(false);
		btnNeighborManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//NeighborDialog neighborDialog = new NeighborDialog();
				neighborDialog.setVisible(true);
				neighborDialog.setAlwaysOnTop(true);

			}
		});
		btnNeighborManage.setBounds(0, 815, 269, 29);
		getContentPane().add(btnNeighborManage);

		setTitle("Path Finding");
		setLocation(0, 0);
		setSize(1200, 900);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton getBtnNeighborManage() {
		return this.btnNeighborManage;
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
		new SystemFacade();
	}

}