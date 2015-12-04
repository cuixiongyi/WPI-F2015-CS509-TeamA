package com.wpi.cs509.teamA.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
//import com.sun.prism.paint.Color;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.controller.ViewController;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.PaintHelper;

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
    private ViewController controller = null;



	MainModel mainModel = null;
	ViewManager viewManager = null;

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
        UIManager.put("TextField.disabled", new Color(180, 180, 180));
        UIManager.put("TextField.disabledText", new Color(255,255,255));
        UIManager.put("TextField[Disabled].textForeground", new Color(180, 180, 180));
        
        
        
		container = getContentPane();
		// container.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds(100, 100, 1600, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gblContentPane = new GridBagLayout();
		gblContentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 50, 50 };
		gblContentPane.rowHeights = new int[] { 0 };
		gblContentPane.columnWeights = new double[] { Double.MIN_VALUE };
		gblContentPane.rowWeights = new double[] { Double.MIN_VALUE };
		contentPane.setLayout(gblContentPane);

/**
 * set dependence
 */
		inputPanel = new InputPanel();
        imgComponent = new ImageComponent();
        mainModel = new MainModel();
        ViewControllerBase.init(imgComponent, inputPanel, mainModel);
        viewManager = new ViewManager();
        imgComponent.setModel(mainModel);
        inputPanel.setModel(mainModel);
        PaintHelper.setModel(mainModel);
        controller = new ViewController();
        // input panel and components

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
		gbcwrappingImgPanel.weighty = 0.6;
		contentPane.add(wrappingImgPanel, gbcwrappingImgPanel);
		wrappingImgPanel.setLayout(new BoxLayout(wrappingImgPanel, BoxLayout.X_AXIS));

		// the panel to show image
		imgComponent.setMaximumSize(new Dimension(1024, 1024));

		
		JScrollPane imgScrollPanel = new JScrollPane();
		imgScrollPanel.setMaximumSize(new Dimension(1024, 1024));
		// contentPane.add(imgScrollPanel, gbcScrollPane);
		// // for scroll panel
		imgScrollPanel.setViewportView(imgComponent);
		imgScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		imgScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		wrappingImgPanel.add(imgScrollPanel);

		setSize(800, 500);
		setVisible(true);
		setResizable(true);

		// TODO: default map? change the way to do this...
		// TODO load map image with different names

	/*	
		imgComponent.setImagePath(System.getProperty("user.dir") + "/src/Final_Campus_Map.jpg");
		imgComponent.setPreferredSize(new Dimension(imgComponent.getImgWidth(), imgComponent.getImgHeight()));
		imgComponent.setVisible(true);
*/





        imgComponent.setPreferredSize(new Dimension(mainModel.getCurrentMap().getImage().getWidth(), mainModel.getCurrentMap().getImage().getHeight()));
        imgComponent.setVisible(true);
        inputPanel.setUserScreen(this);
		imgComponent.repaint();
		mainModel.switchToState(new MouseActionSelectNode(mainModel));
//		stateContext.switchUserState(new NormalUserState(stateContext));
		viewManager.updateView();
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