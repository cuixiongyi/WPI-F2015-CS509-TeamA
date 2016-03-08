package com.wpi.cs509.teamA.ui.view.renderer;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.wpi.cs509.teamA.controller.ViewComponentListener;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.DBInitializer;
import com.wpi.cs509.teamA.util.ParkingManager;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperComposite;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintImageHelper;

/**
 * This is the class that construct the main user interface of the application
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class UserScreen extends JFrame {

	// singleton
	private static UserScreen userScreen;
	
	private JLayeredPane contentPane;
	private ImageComponentRenderer imgComponent;
	private ViewComponentListener controller;

	MainModel mainModel = null;
	ViewManager viewManager = null;
	ParkingManager parkingManager = null;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanelRenderer inputPanel;

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds(50, 0, 1200, 730);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * set dependence
		 */
		inputPanel = new InputPanelRenderer();
		imgComponent = new ImageComponentRenderer();

		// set up the data model
		mainModel = new MainModel();
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
		// set up a static model for further references
		MainModel.setStaticModel(mainModel);

		ViewComponent.init(imgComponent, inputPanel, mainModel);

		// AnimationPathControl.init(mainModel);
		viewManager = new ViewManager();
		imgComponent.setModel(mainModel);
		inputPanel.setModel(mainModel);
		PaintHelperBasics.setModel(mainModel);
		PaintHelperComposite.setModel(mainModel);
		PaintImageHelper.setModel(mainModel);
		controller = new ViewComponentListener();
		mainModel.addObserver(viewManager);
		parkingManager = new ParkingManager();
		parkingManager.setModel(mainModel);
		// input panel and components

		inputPanel.setBounds(905, 0, 300, 710);
		imgComponent.setBounds(0, 0, 900, 710);
		contentPane.add(inputPanel, new Integer(0));
		contentPane.add(imgComponent, new Integer(0));
		imgComponent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		setVisible(true);
		setResizable(false);

		imgComponent.setPreferredSize(new Dimension(mainModel.getCurrentMap().getImage().getWidth(),
				mainModel.getCurrentMap().getImage().getHeight()));
		imgComponent.setVisible(true);
		inputPanel.setUserScreen(this);
		imgComponent.repaint();
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
		// stateContext.switchUserState(new NormalUserState(stateContext));
		ViewManager.updateView();

	}

	// singleton
	public static synchronized UserScreen getUserScreen() {
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

		// TODO: this should be a singleton
		new DBInitializer();

		// singleton
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				UserScreen.getUserScreen();
			}
		});

	}

}