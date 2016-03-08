package com.wpi.cs509.teamA.ui;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

//import com.sun.prism.paint.Color;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationPathControl;
import com.wpi.cs509.teamA.ui.controller.ViewController;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;
import com.wpi.cs509.teamA.util.PaintHelper.PaintImageHelper;
import com.wpi.cs509.teamA.util.ParkingManager;

import javax.swing.BorderFactory;

/**
 * This is the class that construct the main user interface of the application
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class UserScreen extends JFrame {

	private static UserScreen userScreen;
	private JLayeredPane contentPane;
	private ImageComponent imgComponent;
	private ViewController controller = null;
	private JPanel popUpPane;
	private JPanel popUpPaneLeft;

	MainModel mainModel = null;
	ViewManager viewManager = null;
	ParkingManager parkingManager = null;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;

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
		inputPanel = new InputPanel();
		imgComponent = new ImageComponent();

		// set up the data model
		mainModel = new MainModel();
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
		// set up a static model for further references
		MainModel.setStaticModel(mainModel);

		ViewControllerBase.init(imgComponent, inputPanel, mainModel);
		
		
		// AnimationPathControl.init(mainModel);
		viewManager = new ViewManager();
		imgComponent.setModel(mainModel);
		inputPanel.setModel(mainModel);
		PaintHelperBasics.setModel(mainModel);
		PaintHelperComposite.setModel(mainModel);
		PaintImageHelper.setModel(mainModel);
		controller = new ViewController();
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

	public static UserScreen getUserScreen() {
		return userScreen;
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

		new DBInitializer();

		// singleton
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				UserScreen.launchUserScreen();
			}
		});

	}

}