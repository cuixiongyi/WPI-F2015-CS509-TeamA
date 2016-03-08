package com.wpi.cs509.teamA.ui.view.renderer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.ViewRerenderController;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperComposite;

/**
 * This is the view for the image, render the image here
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class ImageComponentRenderer extends JComponent {

	/**
	 * All the data
	 */
	private MainModel model;

	private BufferedImage image;

	private int imageXpos = 0;
	private int imageYpos = 0;
	private int imageStartXpos = 0;
	private int imageStartYpos = 0;
	private int pressxPos;
	private int pressyPos;

	public ImageComponentRenderer() {

	}

	private boolean testBeforeRepaint() {
		try {
			if (null == model) {
				throw new NullPointerException();
			}
			if (null == model.getCurrentMap().getImage()) {
				throw new NullPointerException();
			}
			if (null == this.image) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void paintComponent(Graphics g) {

		System.out.println("ImageComponent paintComponent....");

		// test for null stateContext and null image
		GeneralMap map = model.getCurrentMap();
		image = map.getImage();

		if (!testBeforeRepaint())
			return;

		LinearTransform lt = model.getLinearTransform();

		// if isInitilized
		// no need to paint the image again
		Graphics2D g2 = (Graphics2D) g;
		if (model.isFisrtChangeMap()) {
			this.setImageXpos(0);
			this.setImageYpos(0);
			model.getCurrentMap().setDisplayScale(1.0f);

			ViewRerenderController.infoPanelSlideDown();
			if (!model.isFisrtFocusNode()) {
				model.setLinearTransform(new LinearTransform());

			}
			model.setFisrtChangeMapFalse();
		}

		Node nodeFocus = model.getFocusNode();
		if (model.isFisrtFocusNode() && null != nodeFocus) {
			lt.setX(this.getWidth() / 2 - (int) (nodeFocus.getLocation().getX() * lt.getScale()));
			lt.setY(this.getHeight() / 2 - (int) (nodeFocus.getLocation().getY() * lt.getScale()));
			model.setFisrtFocusNode2False();
		}

		PaintHelperComposite.paintEverything(g2, image, lt);
		// ViewManager.getThumbNailPanel().setLocation(0,0);
		Node animationNode = model.getAnimationNode();

		if (null != animationNode) {
			PaintHelperBasics.paintDot(animationNode, g2);
			// model.setAnimationNode(null);
		}

		g2 = null;

	}

	public int getImageXpos() {
		return imageXpos;
	}

	public void setImageXpos(int imageXpos) {
		this.imageXpos = imageXpos;
	}

	public int getImageYpos() {
		return imageYpos;
	}

	public void setImageYpos(int imageYpos) {
		this.imageYpos = imageYpos;
	}

	public int getImageStartXpos() {
		return imageStartXpos;
	}

	public void setImageStartXpos(int imageStartXpos) {
		this.imageStartXpos = imageStartXpos;
	}

	public int getImageStartYpos() {
		return imageStartYpos;
	}

	public void setImageStartYpos(int imageStartYpos) {
		this.imageStartYpos = imageStartYpos;
	}

	public int getPressxPos() {
		return pressxPos;
	}

	public void setPressxPos(int pressxPos) {
		this.pressxPos = pressxPos;
	}

	public int getPressyPos() {
		return pressyPos;
	}

	public void setPressyPos(int pressyPos) {
		this.pressyPos = pressyPos;
	}

	public void setModel(MainModel model) {
		this.model = model;
	}

}
