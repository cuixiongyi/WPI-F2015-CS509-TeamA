package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.Coordinate;

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
	private final static String LOGIN = "Log in as Admin";

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.
	private MouseListener normalUserMouseListener;
	private MouseListener adminMouseListener;
	private int xPos;
	private int yPos;

	private List<Coordinate> coordinateList = new ArrayList<Coordinate>();

	private Map<Integer, List<Node>> result;

	private static int num = 1;

	private static int adminClicked = 2;

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
	public ImageComponent(final InputPanel inputPanel, final UserScreen userScreen, NeighborDialog neighborDialog) {

		// initialize the mouse listener state
		stateContext = new StateContext();
		coordinateList=neighborDialog.getCoorList();

		normalUserMouseListener = new NormalUserMouseListener(this);
		adminMouseListener = new AdminMouseListener(this, neighborDialog);

		
		// TODO: Move this part to input panel..
		// we need to add the event listener before the state pattern begins
		this.addMouseListener(normalUserMouseListener);

		// add listener to the search button
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

				result = algoController.getRoute();

				// TODO: use the result to draw the lines

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
					AdminDialog adminDialog = new AdminDialog(ImageComponent.this, userScreen, inputPanel);
					adminDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					adminDialog.setVisible(isFocusable());

				} else {

					JOptionPane.showMessageDialog(null, "You have logged out");
					stateContext.switchState(ImageComponent.this, normalUserMouseListener, adminMouseListener);
					userScreen.getBtnNeighborManage().setVisible(false);
					inputPanel.getAdminLogin().setText(LOGIN);
					adminClicked++;
				}
			}

		});

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
		this.adminClicked++;
	}

	/**
//	 * @param List<Coordinate>
//	 *            coordinateList return the nodelist
//	 */
//	public List<Coordinate> getCoorList() {
//		return coordinateList;
//	}
//
//	public void addNodeList(int x, int y) {
//		Coordinate coor = new Coordinate(x, y);
//		coordinateList.add(coor);
//	}

	@Override
	public void paintComponent(Graphics g) {

		// if isInitilized
		// no need to paint the image again

		if (null == image) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
		setForeground(Color.RED);
		if (coordinateList.size() != 0) {
			for (int i = 0; i < coordinateList.size(); i++) {
				xPos = coordinateList.get(i).getX();
				yPos = coordinateList.get(i).getY();
				g.fillOval(xPos, yPos, 10, 10);
			}
		}

		if (!(xPos == 0 && yPos == 0)) {
			g2.setPaint(Color.WHITE);
			g2.drawString("(" + xPos + "," + yPos + ")", xPos, yPos);
		}

		// since it will be repaint again
		// so the string will not be save on the image
		// we should figure out an idea to save all the point we have clicked
		// but I think maybe we can first save it
		// then load all the points back to draw them on the image?

		g2 = null;

	}

}
