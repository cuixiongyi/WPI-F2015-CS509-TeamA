package com.wpi.cs509.teamA.ui;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

//import com.sun.prism.paint.Color;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingUp;
import com.wpi.cs509.teamA.ui.Animation.AnimationStyle;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.controller.ViewController;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.Dialog.PopupPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.PaintHelper;

import javax.swing.BorderFactory;
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
	private JLayeredPane contentPane;
	private ImageComponent imgComponent;
    private ViewController controller = null;
    private  JPanel popUpPane;
    private  int yPos;



	MainModel mainModel = null;
	ViewManager viewManager = null;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;


	/**
	 * Initialize the user screen, constructor
	 */
	private UserScreen() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds(50, 0, 1200, 770);
		contentPane = new  JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

/**
 * set dependence
 */
		inputPanel = new InputPanel();
        imgComponent = new ImageComponent();
        mainModel = new MainModel();
        mainModel.switchToState(new MouseActionSelectNode(mainModel));
        ViewControllerBase.init(imgComponent, inputPanel, mainModel,this);
        viewManager = new ViewManager();
        imgComponent.setModel(mainModel);
        inputPanel.setModel(mainModel);
        PaintHelper.setModel(mainModel);
        controller = new ViewController();
        mainModel.addObserver(viewManager);
        // input panel and components

        inputPanel.setBounds(905, 0, 300, 750);
        imgComponent.setBounds(0, 0, 900, 750);
		contentPane.add(inputPanel, new Integer(0));
		contentPane.add(imgComponent,new Integer(0));
		imgComponent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

	
		setVisible(true);
		setResizable(false);

        imgComponent.setPreferredSize(new Dimension(mainModel.getCurrentMap().getImage().getWidth(), mainModel.getCurrentMap().getImage().getHeight()));
        imgComponent.setVisible(true);
        inputPanel.setUserScreen(this);
		imgComponent.repaint();
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
//		stateContext.switchUserState(new NormalUserState(stateContext));
		viewManager.updateView();
		
		popUpPane=new PopupPanel();
		contentPane.add(popUpPane,new Integer(2));
		viewManager.getAC().create(popUpPane, contentPane, AnimationStyle.SLIDE_UP, AnimationPosition.BOTTOMM_MIDDLE, popUpPane.getHeight());
        AnimationObject AO = viewManager.getAC().checkObjectExist(popUpPane);
        AO.switchState(new AnimationStateSlidingUp(AO));

		popUpPane.setVisible(true);
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

        new SystemFacade();

        // singleton
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				UserScreen.launchUserScreen();
			}
		});

	}

}