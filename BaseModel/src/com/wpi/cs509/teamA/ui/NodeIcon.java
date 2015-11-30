package com.wpi.cs509.teamA.ui;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeType;

public class NodeIcon {
	private Node node;


	private static String labIconName = "Lab_Icon.png";
	private static String classroomIconName = "Classroom_Icon.png";
	private static String restroomIconName = "Restroom_Icon.png";
	private static String parkingIconName = "Parking_Icon.png";


	private static String labIconFilePath = System.getProperty("user.dir") + "/src/" + labIconName;;
	private static String classroomIconFilePath = System.getProperty("user.dir") + "/src/" + classroomIconName;;
	private static String restroomIconFilePath = System.getProperty("user.dir") + "/src/" + restroomIconName;;
	private static String parkingIconFilePath = System.getProperty("user.dir") + "/src/" + parkingIconName;;

	private static BufferedImage labIcon;
	private static BufferedImage classroomIcon;
	private static BufferedImage restroomIcon;
	private static BufferedImage parkingIcon;

    static {
        try {
            labIcon = ImageIO.read(new FileInputStream(labIconFilePath));
            classroomIcon = ImageIO.read(new FileInputStream(classroomIconFilePath));
            restroomIcon = ImageIO.read(new FileInputStream(restroomIconFilePath));
            parkingIcon = ImageIO.read(new FileInputStream(parkingIconFilePath));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	public NodeIcon() {
		CreateImages();
	}

	void CreateImages() {

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
	static public BufferedImage getImage(Node node) {
		NodeType type = node.getNodeType();
		switch (type) {
            case CLASSROOM: {
                return classroomIcon;
            }
            case OFFICE: {
                return classroomIcon;
            }
            case MEETINGROOM: {
                return classroomIcon;
            }
            case RESTROOM: {
                return restroomIcon;
            }
            case PARKING: {
                return parkingIcon;
            }
            case LAB: {
                return labIcon;
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
