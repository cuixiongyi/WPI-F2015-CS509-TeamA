package com.wpi.cs509.teamA.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.wpi.cs509.teamA.bean.GeneralMap;
//import com.sun.prism.paint.Color;
import com.wpi.cs509.teamA.util.Database;
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
	private Container container;
	private JPanel contentPane;
	private ImageComponent imgComponent;


	private StateContext stateContext;

	/**
	 * A JPanel that have input text fields and buttons which will be shown on
	 * the top of the UI
	 */
	private InputPanel inputPanel;
	private JButton btnNeighborManage;
	private JPanel wrappingImgPanel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
		this.setBounds(50, 0, 1200, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		inputPanel = new InputPanel();
		inputPanel.setBounds(905, 30, 300, 750);
		contentPane.add(inputPanel);

		// the panel to show image
		imgComponent = new ImageComponent();
		contentPane.add(imgComponent);
		imgComponent.setBounds(0, 0, 900, 750);
		imgComponent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		
		setVisible(true);
		setResizable(false);

		imgComponent.setInputPanel(this.inputPanel);
        inputPanel.setImageComponent(this.imgComponent);

        stateContext = new StateContext();
       

        imgComponent.setStateContext(stateContext);
        PaintHelper.setStateContext(stateContext);
        inputPanel.setStateContext(stateContext);
        stateContext.setImageComponent(imgComponent);
        stateContext.setInputPanel(inputPanel);
        imgComponent.setPreferredSize(new Dimension(stateContext.getCurrentMap().getImage().getWidth(), stateContext.getCurrentMap().getImage().getHeight()));
        imgComponent.setVisible(true);
        inputPanel.setUserScreen(this);
		imgComponent.repaint();
		stateContext.switchToState(new MouseActionSelectNode(stateContext));
		stateContext.switchUserState(new NormalUserState(stateContext));
	}

	public JButton getBtnNeighborManage() {
		return this.btnNeighborManage;
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