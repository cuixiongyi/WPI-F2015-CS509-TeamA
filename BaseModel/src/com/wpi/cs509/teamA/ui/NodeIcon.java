package com.wpi.cs509.teamA.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.bean.Node;

public class NodeIcon {
	private Node node;
	private BufferedImage image;
	private String iconFileName = "/BaseModel/src/com/wpi/cs509/teamA/ui/icon_test1.png";

	public NodeIcon() {
		CreateImages();
	}

	void CreateImages() {
		try {
			setImage(ImageIO.read(new File(getIconFileName())));
		} catch (IOException e) {
		}
	}

	/**
	 * @return the iconFile
	 */
	public String getIconFileName() {
		return iconFileName;
	}

	/**
	 * @param iconFile
	 *            the iconFile to set
	 */
	public void setIconFileName(String iconFile) {
		this.iconFileName = iconFile;
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
	 * @return the image
	 */
	public BufferedImage getImage(Node node) {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
