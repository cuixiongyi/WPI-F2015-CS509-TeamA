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
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.controller.ViewController;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.PopupPanel;
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
    private static JPanel popUpPane;
    private static int yPos;



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

//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			// UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		UIManager.put("nimbusBase", new Color(50, 50, 50));
		UIManager.put("ComboBox:\"ComboBox.listRenderer\".background", new Color(142, 143, 145));
		UIManager.put("control", new Color(142, 143, 145));
		UIManager.put("text", new Color(255,255,255));
		UIManager.put("TextField.background", new Color(180, 180, 180));

        UIManager.put("List.background", new Color(180, 180, 180));
        UIManager.put("PasswordField.background", new Color(180, 180, 180));
        UIManager.put("TextField.disabled", new Color(180, 180, 180));
        UIManager.put("TextField.disabledText", new Color(255,255,255));
        UIManager.put("TextField[Disabled].textForeground", new Color(180, 180, 180));
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		// container.setLayout(new BorderLayout());

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
//		popUpPane.setSize(100,100);
//		popUpPane.setBackground(Color.RED);
//		popUpPane.setOpaque();
	
		Timer timer = new Timer(3,new MyActionListener());
		timer.start();
		popUpPane.setVisible(true);
		yPos=700;
	}


	
	public static class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(yPos>550)
			{
			popUpPane.setLocation(400, yPos--);
			}
		}

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