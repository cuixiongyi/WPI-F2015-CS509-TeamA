package com.wpi.cs509.teamA.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.PaintHelper;

public class NodeIcon {
	private Node node;


	private static String labIconName = "Lab_Icon.png";
	private static String classroomIconName = "Classroom_Icon.png";
	private static String restroomIconName = "Restroom_Icon.png";
	private static String parkingIconName = "Parking_Icon.png";
	private static String startIconName = "Start_Icon.png";
	private static String endIconName = "End_Icon.png";
	private static String professorIconName = "Professor_Icon.png";
	private static String historyIconName = "History_Icon.png";
	private static String locationIconName = "Location_Icon.png";


	private static String labIconFilePath = PaintHelper.getUserDir() + labIconName;
	private static String classroomIconFilePath = PaintHelper.getUserDir() + classroomIconName;
	private static String restroomIconFilePath = PaintHelper.getUserDir() + restroomIconName;
	private static String parkingIconFilePath = PaintHelper.getUserDir() + parkingIconName;
	private static String startIconFilePath = PaintHelper.getUserDir() + startIconName;
	private static String endIconFilePath = PaintHelper.getUserDir() + endIconName;;


	
	private static BufferedImage labIcon;
	private static BufferedImage classroomIcon;
	private static BufferedImage restroomIcon;
	private static BufferedImage parkingIcon;
	private static BufferedImage startIcon;
	private static BufferedImage endIcon;



    static {
        try {
            labIcon = ImageIO.read(new FileInputStream(labIconFilePath));
            classroomIcon = ImageIO.read(new FileInputStream(classroomIconFilePath));
            restroomIcon = ImageIO.read(new FileInputStream(restroomIconFilePath));
            parkingIcon = ImageIO.read(new FileInputStream(parkingIconFilePath));
            startIcon = ImageIO.read(new FileInputStream(startIconFilePath));
            endIcon = ImageIO.read(new FileInputStream(endIconFilePath));

        }
        catch (Exception e) {
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

	
	static public BufferedImage getStartIcon()
	{
		return startIcon;
	}
	
	static public BufferedImage getEndIcon()
	{
		return endIcon;
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
	
	/**
	 * @param image
	 *            the image to set
	 */
	public void setStartIcon(BufferedImage image) {
		this.startIcon = image;
	}
	
	/**
	 * @param image
	 *            the image to set
	 */
	public void setEndIcon(BufferedImage image) {
		this.endIcon = image;
	}
	
	
}
