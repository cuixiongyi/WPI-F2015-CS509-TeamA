package com.wpi.cs509.teamA.ui;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeType;

public class NodeIcon {
	private Node node;

	private BufferedImage labIcon;
	private BufferedImage classroomIcon;
	private BufferedImage restroomIcon;
	private BufferedImage parkingIcon;

	private String labIconFilePath;
	private String classroomIconFilePath;
	private String restroomIconFilePath;
	private String parkingIconFilePath;

	private String labIconName = "Lab_Icon.png";
	private String classroomIconName = "Classroom_Icon.png";
	private String restroomIconName = "Restroom_Icon.png";
	private String parkingIconName = "Parking_Icon.png";

	public NodeIcon() {
		CreateImages();
	}

	void CreateImages() {
		this.labIconFilePath = System.getProperty("user.dir") + "/src/" + this.labIconName;
		this.classroomIconFilePath = System.getProperty("user.dir") + "/src/" + this.classroomIconName;
		this.restroomIconFilePath = System.getProperty("user.dir") + "/src/" + this.restroomIconName;
		this.parkingIconFilePath = System.getProperty("user.dir") + "/src/" + this.parkingIconName;
		try {
			setLabIcon(ImageIO.read(new FileInputStream(this.labIconFilePath)));
			setClassroomIcon(ImageIO.read(new FileInputStream(this.classroomIconFilePath)));
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
			return this.classroomIcon;
		}
		case OFFICE: {
			return this.classroomIcon;
		}
		case MEETINGROOM: {
			return this.classroomIcon;
		}
		case RESTROOM: {
			return this.restroomIcon;
		}
		case PARKING: {
			return this.parkingIcon;
		}
		case LAB: {
			return this.labIcon;
		}
		default: {
			return null;
		}
		}

	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setLabIcon(BufferedImage image) {
		this.labIcon = image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setClassroomIcon(BufferedImage image) {
		this.classroomIcon = image;
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
