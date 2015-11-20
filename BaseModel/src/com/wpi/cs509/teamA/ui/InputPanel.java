package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.UIDataBuffer;
import com.wpi.cs509.teamA.ui.StateContext;
import com.wpi.cs509.teamA.ui.ImageComponent;

/**
 * JPanel that have input text fields and buttons which will be shown on the top
 * of the UI
 * 
 * TODO: we need a singleton here..
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel implements ActionListener {

	private JTextField startPoint;
	private JTextField endPoint;
	private JButton btnSearch;
	private JButton adminLogin;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JButton btnNeighborManage;
	private JButton btnSynchronize;
	private JComboBox<String> comboBoxMap;
	private DefaultComboBoxModel<String> comboSourceModel;
	private DefaultComboBoxModel<String> comboDesModel;
	private JComboBox<String> sourceBox;
	private JComboBox<String> desBox;
	private final static String SEARCH = "Search";
	private final static String LOGIN = "Login";
	private final static String TO = "To: ";
	private final static String FROM = "From: ";
    private ImageComponent imageComponent;

	public void setStateContext(StateContext stateContext) {
		this.stateContext = stateContext;
	}

	private StateContext stateContext;
	/**
	 * Constructor. Initialize all the input panel.
	 */
	public InputPanel() {
		// // User input block
		this.startPoint = new JTextField();
		this.endPoint = new JTextField();
		this.btnSearch = new JButton(SEARCH);
		this.adminLogin = new JButton(LOGIN);
		this.btnNeighborManage = new JButton("Edges");
		btnNeighborManage.setSize(75, 30);
		btnNeighborManage.setLocation(80, 380);
		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.btnNeighborManage.setVisible(false);

		this.setLayout(null);
//		this.add(startPoint);
		this.add(endPoint);
		this.add(btnSearch);
		this.add(adminLogin);
		this.add(btnNeighborManage);

		this.getAdminLogin().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getBtnSearch().setFont(new Font("Arial", Font.PLAIN, 15));
		//this.getEndPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		//this.getStartPoint().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getAdminLogin().setBounds(150, 0, 75, 30);
		this.getBtnSearch().setBounds(80, 300, 150, 38);
		
//		this.getStartPoint().setBounds(80, 150, 150, 38);
//		 this.setBounds(0, 0, 1178, 516);

		lblFrom = new JLabel(FROM);
		lblFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFrom.setBounds(15, 155, 61, 16);
		add(lblFrom);

		lblTo = new JLabel(TO);
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(15, 230, 61, 16);
		add(lblTo);

		comboBoxMap = new JComboBox<String>();
		comboBoxMap.setBounds(80, 55, 150, 30);
		comboBoxMap.addItem("Campus Map");
		comboBoxMap.addItem("AK-G");
		comboBoxMap.addItem("AK-1");
		comboBoxMap.addItem("AK-2");
		comboBoxMap.addItem("AK-3");
		comboBoxMap.addItem("PC-1");
		comboBoxMap.addItem("PC-2");
		comboBoxMap.setMaximumRowCount(4);
		add(comboBoxMap);

		JLabel lblMap = new JLabel("Map: ");
		lblMap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMap.setBounds(15, 60, 61, 21);
		add(lblMap);
		
		comboSourceModel = new DefaultComboBoxModel<String>();
		comboDesModel = new DefaultComboBoxModel<String>();
		sourceBox = new JComboBox<String>(comboSourceModel);
		desBox = new JComboBox<String>(comboDesModel);
		
		// Add all nodes from
		
		Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();
		if (allNodes != null && allNodes.get(1).size() != 0) {
			for (int i = 0; i < allNodes.get(1).size(); i++) {
				if (allNodes.get(1).get(i).getName().toString().equals("Location"))
					continue;
				comboSourceModel.addElement(allNodes.get(1).get(i).getName().toString());
				comboDesModel.addElement(allNodes.get(1).get(i).getName().toString());
			}
		}

		sourceBox.setBounds(80, 150, 150, 38);
		desBox.setBounds(80, 225, 150, 38);
		this.add(sourceBox);
		this.add(desBox);

		
		btnSynchronize = new JButton("Sync");
		btnSynchronize.addActionListener(this);
		btnSynchronize.setVisible(false);
		btnSynchronize.setBounds(155, 380, 75, 30);
		add(btnSynchronize);		
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/logo_iteration1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(50, 480, 200, 200);
			add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /**
         * Add listener to the admin login button
         */
        inputPanel.getAdminLogin().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                // TODO: we need a pop up window here to verify the admin role.
                // If it is the admin, give it the admin mouse click event. If
                // not, give it normal user

                if (adminClicked % 2 == 0) {
                    AdminDialog adminDialog = new AdminDialog(ImageComponent.this, inputPanel);
                    adminDialog.setModalityType(ModalityType.APPLICATION_MODAL);
                    adminDialog.setVisible(isFocusable());

                } else {

                    JOptionPane.showMessageDialog(null, "You have logged out");
                    stateContext.switchState(ImageComponent.this, normalUserMouseListener, adminMouseListener);
                    inputPanel.getBtnNeighborManage().setVisible(false);
                    inputPanel.getAdminLogin().setText(LOGIN);
                    inputPanel.getBtnSynchronize().setVisible(false);
                    isAdmin = false;
                    adminClicked++;
                    ImageComponent.this.repaint();
                }
            }

        });

	// TODO: Make the map related things into a enum class..
				inputPanel.getComboBoxMap().addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
								if (inputPanel.getComboBoxMap().getSelectedItem().equals("Campus Map")) {
										selectImage("Final_Campus_Map", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(1);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-G")) {
										selectImage("Final_AK_Ground_Floor", ImageComponent.this);
									UIDataBuffer.setCurrentMapId(2);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-1")) {
										selectImage("Final_AK_First_Floor", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(3);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-2")) {
										selectImage("Final_AK_Second_Floor", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(4);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-3")) {
										selectImage("Final_AK_Third_Floor", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(5);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-1")) {
										selectImage("Final_Project_Center_First_Floor", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(6);
										inputPanel.getBtnSynchronize().doClick();
									} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-2")) {
										selectImage("Final_Project_Center_Second_Floor", ImageComponent.this);
										UIDataBuffer.setCurrentMapId(7);
										inputPanel.getBtnSynchronize().doClick();
									}
							}

					});

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnSynchronize) {
			comboSourceModel.removeAllElements();
			comboDesModel.removeAllElements();
			Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();
			if (allNodes != null && allNodes.get(1).size() != 0) {
				for (int i = 0; i < allNodes.get(1).size(); i++) {
					if (allNodes.get(1).get(i).getName().toString().equals("Location"))
						continue;
					comboSourceModel.addElement(allNodes.get(1).get(i).getName().toString());
					comboDesModel.addElement(allNodes.get(1).get(i).getName().toString());
				}
			}
		}
	}

    // Click the SEARCH button..
    this.btnSearch().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {

            // TODO: need to check if the input is valid!!

            // TODO: make the AlgoController singleton and use setter and
            // getter to operate the instance..

            // We will go to the backend here.. For now, all the resources
            // should be ready!
            AlgoController algoController = new AlgoController(inputPanel.getSourcePoint(),
                    inputPanel.getDesPoint());

            // get the result of the search..
            result = null;
            result = algoController.getRoute();

            // get a list of a map, so that we can draw line on that map..
            pathNodeList = null;
            pathNodeList = result.get(UIDataBuffer.getCurrentMapId());
            stateContext.setPath(pathNodeList);
            // we need to give all the information to the repaint metho
            imageComponent.repaint();
        }

    public void setImageComponent(ImageComponent imageComponent2)
	{
        this.imageComponent = imageComponent2;
       
    }
	public JButton getBtnSynchronize() {
		return btnSynchronize;
	}

	public JComboBox<String> getComboBoxMap() {
		return comboBoxMap;
	}

	/**
	 * @return the startPoint
	 */
	public String getSourcePoint() {
		return sourceBox.getSelectedItem().toString();
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(JTextField startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endPoint
	 */
	public String getDesPoint() {
		return desBox.getSelectedItem().toString();
	}

	/**
	 * @param endPoint
	 *            the endPoint to set
	 */
	public void setEndPoint(JTextField endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the btnSearch
	 */
	public JButton getBtnSearch() {
		return btnSearch;
	}

	/**
	 * @param btnSearch
	 *            the btnSearch to set
	 */
	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	/**
	 * @return the adminLogin
	 */
	public JButton getAdminLogin() {
		return adminLogin;
	}

	/**
	 * @param adminLogin
	 *            the adminLogin to set
	 */
	public void setAdminLogin(JButton adminLogin) {
		this.adminLogin = adminLogin;
	}

	/**
	 * @return the btnNeighborManage
	 */
	public JButton getBtnNeighborManage() {
		return btnNeighborManage;
	}


}
