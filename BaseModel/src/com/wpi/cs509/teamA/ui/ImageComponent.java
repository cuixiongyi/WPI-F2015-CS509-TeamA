package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.UIDataBuffer;

/**
 * An component to show the images. This component has two different states
 * based on whether the user logged in or not. The state of this component will
 * change in the run time. As a admin user, a person would have more mouse
 * operation.
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class ImageComponent extends JComponent {

	private Image image;
	private int imgWidth;
	private int imgHeight;
	private StateContext stateContext;
	private final static String LOGIN = "Login";

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.
	private MouseListener normalUserMouseListener;
	private MouseListener adminMouseListener;
	private int xPos;
	private int yPos;

	private List<Node> pathNodeList;

	private Map<Integer, List<Node>> result;

	private static int adminClicked = 2;

	private InputPanel inputPanel;
	private final static int ovalOffset = 5;

	private static boolean isAdmin;

	// admin will get a different repaint method
	// private boolean isAdmin;

	public ImageComponent() {

		// initialize the mouse listener state
		stateContext = new StateContext();
	}

	/**
	 * Constructor for image component The constructor will also add all the
	 * Listeners to the inputPanel it got
	 * 
	 * @param inputPanel
	 *            an instance of inputPanel will add listeners to the buttons in
	 *            the inputPanel. inputPanel must be final since it will be used
	 *            in the inner class
	 */
	public ImageComponent(final InputPanel inputPanel) {
		ImageComponent.isAdmin = false;
		this.inputPanel = inputPanel;
		// initialize the mouse listener state
		stateContext = new StateContext();

		normalUserMouseListener = new NormalUserMouseListener(this);
		adminMouseListener = new AdminMouseListener(this);

		// TODO: Move this part to input panel..
		// we need to add the event listener before the state pattern begins
		this.addMouseListener(normalUserMouseListener);

		// Click the SEARCH button..
		inputPanel.getBtnSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// TODO: need to check if the input is valid!!

				// TODO: make the AlgoController singleton and use setter and
				// getter to operate the instance..

				// We will go to the backend here.. For now, all the resources
				// should be ready!
				AlgoController algoController = new AlgoController(inputPanel.getStartPoint().getText().trim(),
						inputPanel.getEndPoint().getText().trim());

				// get the result of the search..
				result = null;
				result = algoController.getRoute();

				// get a list of a map, so that we can draw line on that map..
				pathNodeList = null;
				pathNodeList = result.get(UIDataBuffer.getCurrentMapId());

				// we need to give all the information to the repaint metho
				repaint();

			}

		});

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
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-G")) {
					selectImage("Final_AK_Ground_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(2);
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-1")) {
					selectImage("Final_AK_First_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(3);
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-2")) {
					selectImage("Final_AK_Second_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(4);
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-3")) {
					selectImage("Final_AK_Third_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(5);
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-1")) {
					selectImage("Final_Project_Center_First_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(6);
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-2")) {
					selectImage("Final_Project_Center_Second_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(7);
				}
			}

		});

	}

	/**
	 * 
	 * Select the right image and paint it on the image component
	 * 
	 * @param mapName
	 * @param imageComponent
	 */
	private void selectImage(String mapName, ImageComponent imageComponent) {
		imageComponent.setImagePath(System.getProperty("user.dir") + "/src/" + mapName + ".jpg");
		imageComponent.repaint();

	}

	/**
	 * Get the path of the image we will load. Temporary use..
	 * 
	 * @param imgPath
	 *            path of the image, or in the database?
	 */
	public void setImagePath(String imgPath) {

		// TODO: It seems that we will store our image in the database.. so how
		// to implement that?

		try {
			image = ImageIO.read(new FileInputStream(imgPath));
			this.setImgWidth(image.getWidth(this));
			this.setImgHeight(image.getHeight(this));
			this.repaint();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the inputPanel
	 */
	public InputPanel getInputPanel() {
		return inputPanel;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the imgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * @return the imgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @param imgWidth
	 *            the imgWidth to set
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @param imgHeight
	 *            the imgHeight to set
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param xPos
	 *            the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos
	 *            the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public NormalUserMouseListener getNormalUserMouseListener() {
		return (NormalUserMouseListener) this.normalUserMouseListener;
	}

	public AdminMouseListener getAdminMouseListener() {
		return (AdminMouseListener) this.adminMouseListener;
	}

	public StateContext getStateContext() {
		return this.stateContext;
	}

	public void incrementAdminClicked() {
		ImageComponent.adminClicked++;
	}

	public static void setIsAdmin(boolean isAdmin) {
		ImageComponent.isAdmin = isAdmin;
	}

	@Override
	public void paintComponent(Graphics g) {

		// if isInitilized
		// no need to paint the image again

		if (null == image) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

		if (isAdmin == true) {
			// paint all of the nodes
			Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();
			setForeground(Color.RED);
			if (allNodes != null && allNodes.get(1).size() != 0) {
				int x, y;
				for (int i = 0; i < allNodes.get(1).size(); i++) {
					x = allNodes.get(1).get(i).getLocation().getX();
					y = allNodes.get(1).get(i).getLocation().getY();
					g.fillOval(x - ovalOffset, y - ovalOffset, 10, 10);
				}
			}

			// paint all the edges
			Set<NodeRelation> allEdges = UIDataBuffer.getAllEdges();
			if (allEdges.size() != 0) {
				for (NodeRelation edge : allEdges) {
					int xstart, ystart, xend, yend;
					xstart = edge.getFirstNodeCoordinate().getX();
					ystart = edge.getFirstNodeCoordinate().getY();

					xend = edge.getSecondNodeCoordinate().getX();
					yend = edge.getSecondNodeCoordinate().getY();

					g.drawLine(xstart, ystart, xend, yend);
				}
			}
		}

		if (pathNodeList != null && pathNodeList.get(1).getMapId() == UIDataBuffer.getCurrentMapId()) {

			// paint the route
			if (pathNodeList != null && pathNodeList.size() != 0) {
				for (int i = 0; i < pathNodeList.size() - 1; i++) {
					int xstart, ystart, xend, yend;
					xstart = pathNodeList.get(i).getLocation().getX();
					ystart = pathNodeList.get(i).getLocation().getY();

					xend = pathNodeList.get(i + 1).getLocation().getX();
					yend = pathNodeList.get(i + 1).getLocation().getY();

					g.drawLine(xstart, ystart, xend, yend);
					// System.out.println("draw line.." + xstart + " " + ystart
					// + "
					// " + xend + " " + yend);
				}
			}
		}

		g2 = null;

	}
}
