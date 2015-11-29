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
import com.wpi.cs509.teamA.bean.GeneralMap;
//import com.sun.prism.paint.Color;
import com.wpi.cs509.teamA.util.Database;
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
		UIManager.put("PasswordField.background", new Color(180, 180, 180));
		UIManager.put("List.background", new Color(180, 180, 180));
		 
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
		gbcwrappingImgPanel.weighty = 0.6;
		contentPane.add(wrappingImgPanel, gbcwrappingImgPanel);
		wrappingImgPanel.setLayout(new BoxLayout(wrappingImgPanel, BoxLayout.X_AXIS));

		// the panel to show image
		imgComponent = new ImageComponent();
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
		imgComponent.repaint();
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

    /**
     * Legacy code
     */
	public JButton getBtnNeighborManage() {
		return this.btnNeighborManage;
	}


}