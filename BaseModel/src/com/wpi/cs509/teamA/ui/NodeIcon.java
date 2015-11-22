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
	private String redIconFilePath;
	private String blueIconFilePath;
	private String redIconName = "Red_Icon.png";
	private String blueIconName = "Blue_Icon.png";

	public NodeIcon() {
		CreateImages();
	}

	void CreateImages() {
		this.redIconFilePath = System.getProperty("user.dir") + "/src/" + this.redIconName;
		this.blueIconFilePath = System.getProperty("user.dir") + "/src/" + this.blueIconName;
		try {
			setRedIcon(ImageIO.read(new FileInputStream(this.redIconFilePath)));
			setBlueIcon(ImageIO.read(new FileInputStream(this.blueIconFilePath)));
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
	 * @todo Fix this function to support multiple images for different node types
	 * @return the image
	 */
	public BufferedImage getImage(Node node) {
		NodeType type = node.getNodeType();
		switch (type) {
		case CLASSROOM: {
			return this.redIcon;
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
	
}
