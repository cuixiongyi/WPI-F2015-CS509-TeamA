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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,30,30,30,50};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		// input panel and components
		inputPanel = new InputPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.gridwidth = 4;
		gbc_inputPanel.insets = new Insets(0, 0, 5, 5);
		gbc_inputPanel.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridx = 12;
		gbc_inputPanel.gridy = 0;
		contentPane.add(inputPanel, gbc_inputPanel);
		
		
		// initialize neighborDialog to be used later
		neighborDialog = new NeighborDialog();
		neighborDialog.setVisible(false);

		// the panel to show image
		imgComponent = new ImageComponent(inputPanel, this, neighborDialog);

		// display the image. Note that "/" only works on UNIX
		imgComponent.setImagePath(System.getProperty("user.dir") + "/src/Final_Campus_Map.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);
		// add listener
		// imgComponent.addMouseListener(imgComponent);

//		// scroll panel
//		imgScrollPanel = new JScrollPane();
//		imgScrollPanel.setBounds(181, 215, 834, 557);
		JScrollPane imgScrollPanel = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_inputPanel.gridwidth = 10;
		gbc_inputPanel.gridheight = 10;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(imgScrollPanel, gbc_scrollPane);
		imgScrollPanel.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
//		// for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
//		// button to change map
//		BasicArrowButton WestArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
//		WestArrowButton.setBounds(109, 632, 57, 140);
//
//		BasicArrowButton EastArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
//		EastArrowButton.setBounds(1030, 632, 57, 140);
		
		BasicArrowButton WestArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
		GridBagConstraints gbc_WestArrowButton = new GridBagConstraints();
		gbc_WestArrowButton.fill = GridBagConstraints.BOTH;
		gbc_WestArrowButton.gridx = 12;
		gbc_WestArrowButton.gridy = 9;
		contentPane.add(WestArrowButton, gbc_WestArrowButton);
		
		BasicArrowButton EastArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
		EastArrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_EastArrowButton = new GridBagConstraints();
		gbc_EastArrowButton.fill = GridBagConstraints.BOTH;
		gbc_EastArrowButton.gridx = 13;
		gbc_EastArrowButton.gridy = 9;
		contentPane.add(EastArrowButton, gbc_EastArrowButton);

//		container.add(WestArrowButton);
//		container.add(EastArrowButton);
//		container.add(imgScrollPanel);
//		container.add(inputPanel);
		// button to set neighbordialog visible
		
		
//		btnNeighborManage = new JButton(NEIGHBOR);
//		btnNeighborManage.setVisible(false);
		
		JButton btnNeighborManage = new JButton(NEIGHBOR);
		btnNeighborManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neighborDialog.setVisible(true);
				neighborDialog.setAlwaysOnTop(true);

			}
		});
		GridBagConstraints gbc_btnNeighborManage = new GridBagConstraints();
		gbc_btnNeighborManage.gridx = 15;
		gbc_btnNeighborManage.gridy = 9;
		contentPane.add(btnNeighborManage, gbc_btnNeighborManage);
//		btnNeighborManage.setBounds(0, 500, 40, 29);
//		getContentPane().add(btnNeighborManage);

//		setTitle(PATH);
//		setLocation(0, 0);
		setSize(800, 500);
		setVisible(true);
		setResizable(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton getBtnNeighborManage() {
		return this.btnNeighborManage;
	}

//	public static UserScreen launchUserScreen() {
//		if (userScreen == null) {
//			userScreen = new UserScreen();
//		}
//
//		return userScreen;
//	}

	/**
	 * The entrance of the program
	 * 
	 * @param args
	 *            command line..
	 */
//	public static void main(String[] args) {
//
//		// singleton
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//
//				UserScreen.launchUserScreen();
//			}
//		});
//
//		new SystemFacade();
//	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserScreen frame = new UserScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new SystemFacade();
	}

}