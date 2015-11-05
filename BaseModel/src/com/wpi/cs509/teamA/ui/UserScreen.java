package com.wpi.cs509.teamA.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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
	private JPanel contentPane;
	private ImageComponent imgComponent;
	private JScrollPane imgScrollPanel;
	private NeighborDialog neighborDialog;
	private final static String NEIGHBOR = "Neighbor Manage Tool";
	private final static String PATH = "Path Finding";

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
		// container.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gblContentPane = new GridBagLayout();
		gblContentPane.columnWidths = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,30,30,30,50};
		gblContentPane.rowHeights = new int[]{0};
		gblContentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gblContentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gblContentPane);
		
		
		// input panel and components
		inputPanel = new InputPanel();
		GridBagConstraints gbcInputPanel = new GridBagConstraints();
		gbcInputPanel.gridwidth = 4;
		gbcInputPanel.insets = new Insets(0, 0, 5, 5);
		gbcInputPanel.fill = GridBagConstraints.BOTH;
		gbcInputPanel.gridx = 12;
		gbcInputPanel.gridy = 0;
		contentPane.add(inputPanel, gbcInputPanel);
		
		
		// initialize neighborDialog to be used later
		neighborDialog = new NeighborDialog();
		neighborDialog.setVisible(false);

		// the panel to show image
		imgComponent = new ImageComponent(inputPanel, this, neighborDialog);

		// display the image. Note that "/" only works on UNIX
		imgComponent.setImagePath(System.getProperty("user.dir") + "/src/Final_Campus_Map.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);
		
		JScrollPane imgScrollPanel = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 0, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcInputPanel.gridwidth = 10;
		gbcInputPanel.gridheight = 10;
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 0;
		contentPane.add(imgScrollPanel, gbcScrollPane);
		imgScrollPanel.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
//		// for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		
		BasicArrowButton WestArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
		GridBagConstraints gbcWestArrowButton = new GridBagConstraints();
		gbcWestArrowButton.fill = GridBagConstraints.BOTH;
		gbcWestArrowButton.gridx = 12;
		gbcWestArrowButton.gridy = 9;
		contentPane.add(WestArrowButton, gbcWestArrowButton);
		
		BasicArrowButton EastArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
		EastArrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbcEastArrowButton = new GridBagConstraints();
		gbcEastArrowButton.fill = GridBagConstraints.BOTH;
		gbcEastArrowButton.gridx = 13;
		gbcEastArrowButton.gridy = 9;
		contentPane.add(EastArrowButton, gbcEastArrowButton);

		
		JButton btnNeighborManage = new JButton(NEIGHBOR);
		btnNeighborManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neighborDialog.setVisible(true);
				neighborDialog.setAlwaysOnTop(true);

			}
		});
		GridBagConstraints gbcBtnNeighborManage = new GridBagConstraints();
		gbcBtnNeighborManage.gridx = 15;
		gbcBtnNeighborManage.gridy = 9;
		contentPane.add(btnNeighborManage, gbcBtnNeighborManage);

		setSize(800, 500);
		setVisible(true);
		setResizable(true);
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

		// singleton
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				UserScreen.launchUserScreen();
			}
		});

		new SystemFacade();
	}
	

}