package com.wpi.cs509.teamA.ui;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeType;

public class NodeIcon {
	private Node node;

	private BufferedImage redIcon;
	private BufferedImage blueIcon;
	private BufferedImage restroomIcon;
	private BufferedImage parkingIcon;

	private String redIconFilePath;
	private String blueIconFilePath;
	private String restroomIconFilePath;
	private String parkingIconFilePath;

	private String redIconName = "Red_Icon.png";
	private String blueIconName = "Blue_Icon.png";
	private String restroomIconName = "Restroom_Icon.png";
	private String parkingIconName = "Parking_Icon.png";

	public NodeIcon() {
		CreateImages();
	}

	void CreateImages() {
		this.redIconFilePath = System.getProperty("user.dir") + "/src/" + this.redIconName;
		this.blueIconFilePath = System.getProperty("user.dir") + "/src/" + this.blueIconName;
		this.restroomIconFilePath = System.getProperty("user.dir") + "/src/" + this.restroomIconName;
		this.parkingIconFilePath = System.getProperty("user.dir") + "/src/" + this.parkingIconName;
		try {
			setRedIcon(ImageIO.read(new FileInputStream(this.redIconFilePath)));
			setBlueIcon(ImageIO.read(new FileInputStream(this.blueIconFilePath)));
			setRestroomIcon(ImageIO.read(new FileInputStream(this.restroomIconFilePath)));
			setParkingIcon(ImageIO.read(new FileInputStream(this.parkingIconFilePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @todo Fix this function to support multiple images for different node
	 *       types
	 * @return the image
	 */
	public BufferedImage getImage(Node node) {
		NodeType type = node.getNodeType();
		switch (type) {
		case CLASSROOM: {
			return this.redIcon;
		}
		case RESTROOM: {
			return this.restroomIcon;
		}
		case PARKING: {
			return this.parkingIcon;
		}
		default: {
			return this.blueIcon;
		}
		}

	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setRedIcon(BufferedImage image) {
		this.redIcon = image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setBlueIcon(BufferedImage image) {
		this.blueIcon = image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setRestroomIcon(BufferedImage image) {
		this.restroomIcon = image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setParkingIcon(BufferedImage image) {
		this.parkingIcon = image;
	}

}
