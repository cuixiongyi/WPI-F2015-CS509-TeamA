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

import com.wpi.cs509.teamA.controller.ViewImageComponentListenerController;
import com.wpi.cs509.teamA.controller.ViewInputPanelListenerController;
import com.wpi.cs509.teamA.controller.ViewRerenderController;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.util.DBInitializer;
import com.wpi.cs509.teamA.util.ParkingManager;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperComposite;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintImageHelper;

/**
 * Initialization of the view
 * 
 * @author CS 509-Team A
 *
 */

@SuppressWarnings("serial")
public class UserScreen extends JFrame {

	// renderers
	private JLayeredPane contentPane;
	private static UserScreen userScreen;
	private InputPanelRenderer inputPanel;
	private ImageComponentRenderer imgComponent;

	// model
	private MainModel mainModel;

	// controllers
	private ViewRerenderController viewManager;

	// for now, unknown
	private ParkingManager parkingManager;

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
		// set up the data model
		mainModel = new MainModel();
		// set up the state model
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
		// set up a static model for further references
		MainModel.setStaticModel(mainModel);

		// set up the views
		inputPanel = new InputPanelRenderer();
		imgComponent = new ImageComponentRenderer();
		ViewComponent.init(imgComponent, inputPanel, mainModel);

		// AnimationPathControl.init(mainModel);

		imgComponent.setModel(mainModel);
		inputPanel.setModel(mainModel);
		PaintHelperBasics.setModel(mainModel);
		PaintHelperComposite.setModel(mainModel);
		PaintImageHelper.setModel(mainModel);
		// TODO: Refactor, should be able to bind them together
		ViewInputPanelListenerController.bindListeners();
		ViewImageComponentListenerController.bindListeners();

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

		// init the observe pattern
		viewManager = new ViewRerenderController();
		mainModel.addObserver(viewManager);
		ViewRerenderController.updateView();

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