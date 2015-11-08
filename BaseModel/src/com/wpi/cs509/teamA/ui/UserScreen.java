package com.wpi.cs509.teamA.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;

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
	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;
	private JButton btnNeighborManage;
	private JPanel wrappingImgPanel;
	private JPanel wrappingButtonPanel;
	private JPanel wrappingButtonPanelE;

	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		container = getContentPane();
		// container.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBounds(100, 100, 1200, 750);
		GridBagLayout gblContentPane = new GridBagLayout();
		gblContentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 30, 50 };
		gblContentPane.rowHeights = new int[] { 0 };
		gblContentPane.columnWeights = new double[] { Double.MIN_VALUE };
		gblContentPane.rowWeights = new double[] { Double.MIN_VALUE };
		contentPane.setLayout(gblContentPane);

		// input panel and components

		inputPanel = new InputPanel();
		GridBagConstraints gbcInputPanel = new GridBagConstraints();
		gbcInputPanel.gridwidth = 7;
		// gbcInputPanel.gridheight = GridBagConstraints.RELATIVE;
		gbcInputPanel.insets = new Insets(0, 0, 5, 5);
		gbcInputPanel.fill = GridBagConstraints.BOTH;
		gbcInputPanel.gridx = 10;
		gbcInputPanel.gridy = 0;
		gbcInputPanel.weightx = 0.1;

		contentPane.add(inputPanel, gbcInputPanel);

		// initialize image block, wrapping panel to limit size of image
		// component
		wrappingImgPanel = new JPanel();
		wrappingImgPanel.setMaximumSize(new Dimension(1024, 1024));
		GridBagConstraints gbcwrappingImgPanel = new GridBagConstraints();
		gbcwrappingImgPanel.insets = new Insets(0, 0, 5, 5);
		gbcwrappingImgPanel.fill = GridBagConstraints.BOTH;
		gbcwrappingImgPanel.gridx = 0;
		gbcwrappingImgPanel.gridy = 0;
		gbcwrappingImgPanel.weightx = 0.6;
		gbcwrappingImgPanel.weighty = 0.5;
		contentPane.add(wrappingImgPanel, gbcwrappingImgPanel);
		wrappingImgPanel.setLayout(new BoxLayout(wrappingImgPanel, BoxLayout.X_AXIS));

		// the panel to show image
		imgComponent = new ImageComponent(inputPanel);
		imgComponent.setMaximumSize(new Dimension(1024, 1024));

		// display the image. Note that "/" only works on UNIX
		imgComponent.setImagePath(System.getProperty("user.dir") + "/src/Final_Campus_Map.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);

		JScrollPane imgScrollPanel = new JScrollPane();
		imgScrollPanel.setMaximumSize(new Dimension(1024, 1024));
		// contentPane.add(imgScrollPanel, gbcScrollPane);
		imgScrollPanel.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		// // for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		wrappingImgPanel.add(imgScrollPanel);

		wrappingButtonPanel = new JPanel();
		wrappingButtonPanel.setMinimumSize(new Dimension(30, 30));
		wrappingButtonPanel.setMaximumSize(new Dimension(50, 50));
		GridBagConstraints gbcWrappingButtonPanel = new GridBagConstraints();
		gbcWrappingButtonPanel.gridx = 13;
		gbcWrappingButtonPanel.gridy = 8;
		contentPane.add(wrappingButtonPanel, gbcWrappingButtonPanel);
		wrappingButtonPanel.setLayout(new BoxLayout(wrappingButtonPanel, BoxLayout.X_AXIS));

		wrappingButtonPanelE = new JPanel();
		wrappingButtonPanelE.setMinimumSize(new Dimension(30, 30));
		wrappingButtonPanelE.setMaximumSize(new Dimension(50, 50));
		GridBagConstraints gbcWrappingButtonPanelE = new GridBagConstraints();
		gbcWrappingButtonPanelE.gridx = 14;
		gbcWrappingButtonPanelE.gridy = 8;
		contentPane.add(wrappingButtonPanelE, gbcWrappingButtonPanelE);
		wrappingButtonPanelE.setLayout(new BoxLayout(wrappingButtonPanelE, BoxLayout.X_AXIS));

		BasicArrowButton WestArrowButton = new BasicArrowButton(BasicArrowButton.WEST);
		wrappingButtonPanel.add(WestArrowButton);

		BasicArrowButton EastArrowButton = new BasicArrowButton(BasicArrowButton.EAST);
		wrappingButtonPanelE.add(EastArrowButton);

		//
		// GridBagConstraints gbcBtnNeighborManage = new GridBagConstraints();
		// gbcBtnNeighborManage.gridx = 0;
		// gbcBtnNeighborManage.gridy = 10;
		// contentPane.add(btnNeighborManage, gbcBtnNeighborManage);

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