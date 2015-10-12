package com.wpi.cs509.teamA.ui;

import java.awt.Color;
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
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;

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

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.
	private MouseListener normalUserMouseListener;
	private MouseListener adminMouseListener;
	private int xPos;
	private int yPos;

	private Map<Integer, List<Node>> result;

	private static int num = 1;

	private static int adminClicked = 2;

	// admin will get a different repaint method
	// private boolean isAdmin;

	public ImageComponent() {

		// initialize the mouse litener state
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

		// initialize the mouse litener state
		stateContext = new StateContext();

		normalUserMouseListener = new NormalUserMouseListener(this);
		adminMouseListener = new AdminMouseListener(this);

		// we need to add the event listener before the state pattern begins
		this.addMouseListener(normalUserMouseListener);

		// add listener to the search button
		inputPanel.getBtnSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("the linstener has been triggered!");

				// TODO: need to check if the input is valid!!

				AlgoController algoController = new AlgoController(inputPanel.getStartPoint().getText().trim(),
						inputPanel.getEndPoint().getText().trim());
				result = algoController.getRoute();

				// TODO: use the result to draw the lines

				// we need to give all the information to the repaint method
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
					System.out.println("Login...");
					stateContext.switchState(ImageComponent.this, normalUserMouseListener, adminMouseListener);
					adminClicked++;
				} else {
					System.out.println("Log off...");
					stateContext.switchState(ImageComponent.this, normalUserMouseListener, adminMouseListener);
					adminClicked++;
				}
			}

		});

	}

	public void setImagePath(String imgPath) {
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

	@Override
	public void paintComponent(Graphics g) {

		// if isInitilized
		// no need to paint the image again

		System.out.println("paintComponent() is called!");

		if (null == image) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

		// xPos = ((NormalUserMouseListener) normalUserMouseListener).getxPos();
		// yPos = ((NormalUserMouseListener) normalUserMouseListener).getyPos();

		System.out.println(xPos + " " + yPos);

		if (!(xPos == 0 && yPos == 0)) {
			g2.setPaint(Color.WHITE);
			g2.drawString("(" + xPos + "," + yPos + ")", xPos, yPos);

			// whenever call the repaint method
			// we draw two demon lines here
			if (num % 2 == 0) {
				g2.draw(new Line2D.Double(10, 10, 600, 10));
				g2.draw(new Line2D.Double(10, 80, 600, 80));
				num++;
			} else {
				g2.draw(new Line2D.Double(10, 600, 600, 600));
				g2.draw(new Line2D.Double(10, 700, 600, 700));
				num++;
			}

		}

		// since it will be repaint again
		// so the string will not be save on the image
		// we should figure out an idea to save all the point we have clicked
		// but I think maybe we can first save it
		// then load all the points back to draw them on the image?

		g2 = null;

	}

}
