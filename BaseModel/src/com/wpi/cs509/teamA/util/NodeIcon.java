package com.wpi.cs509.teamA.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.PaintHelper;


public class NodeIcon {
	private Node node;

	private static String labIconName = "Lab_Icon.png";
	private static String classroomIconName = "Classroom_Icon.png";
	private static String officeIconName = "Office_Icon.png";
	private static String restroomIconName = "Restroom_Icon.png";
	private static String parkingIconName = "Parking_Icon.png";
	private static String startIconName = "Start_Icon.png";
	private static String endIconName = "End_Icon.png";
	
	private static String num0IconName="numbers-0-icon.png";
	private static String num1IconName="numbers-1-icon.png";
	private static String num2IconName="numbers-2-icon.png";
	private static String num3IconName="numbers-3-icon.png";
	private static String num4IconName="numbers-4-icon.png";
	private static String num5IconName="numbers-5-icon.png";
	private static String num6IconName="numbers-6-icon.png";
	private static String num7IconName="numbers-7-icon.png";
	private static String num8IconName="numbers-8-icon.png";
	private static String num9IconName="numbers-9-icon.png";
	
	

	private static String labIconFilePath = PaintHelper.getUserDir() + labIconName;
	private static String classroomIconFilePath = PaintHelper.getUserDir() + classroomIconName;
	private static String officeIconFilePath = PaintHelper.getUserDir() + officeIconName;
	private static String restroomIconFilePath = PaintHelper.getUserDir() + restroomIconName;
	private static String parkingIconFilePath = PaintHelper.getUserDir() + parkingIconName;
	private static String startIconFilePath = PaintHelper.getUserDir() + startIconName;
	private static String endIconFilePath = PaintHelper.getUserDir() + endIconName;
	
	private static String num0IconPath=PaintHelper.getUserDir() + num0IconName;
	private static String num1IconPath=PaintHelper.getUserDir() + num1IconName;
	private static String num2IconPath=PaintHelper.getUserDir() + num2IconName;
	private static String num3IconPath=PaintHelper.getUserDir() + num3IconName;
	private static String num4IconPath=PaintHelper.getUserDir() + num4IconName;
	private static String num5IconPath=PaintHelper.getUserDir() + num5IconName;
	private static String num6IconPath=PaintHelper.getUserDir() + num6IconName;
	private static String num7IconPath=PaintHelper.getUserDir() + num7IconName;
	private static String num8IconPath=PaintHelper.getUserDir() + num8IconName;
	private static String num9IconPath=PaintHelper.getUserDir() + num9IconName;
	

	private static BufferedImage labIcon;
	private static BufferedImage classroomIcon;
	private static BufferedImage restroomIcon;
	private static BufferedImage parkingIcon;
	private static BufferedImage officeIcon;
	private static BufferedImage startIcon;
	private static BufferedImage endIcon;
	
	private static BufferedImage num0Icon;
	private static BufferedImage num1Icon;
	private static BufferedImage num2Icon;
	private static BufferedImage num3Icon;
	private static BufferedImage num4Icon;
	private static BufferedImage num5Icon;
	private static BufferedImage num6Icon;
	private static BufferedImage num7Icon;
	private static BufferedImage num8Icon;
	private static BufferedImage num9Icon;
	
	private static Map<Integer, BufferedImage> numIconMap;


	
	
	
	static {
		try {
			labIcon = ImageIO.read(new FileInputStream(labIconFilePath));
			classroomIcon = ImageIO.read(new FileInputStream(classroomIconFilePath));
			officeIcon = ImageIO.read(new FileInputStream(officeIconFilePath));
			restroomIcon = ImageIO.read(new FileInputStream(restroomIconFilePath));
			parkingIcon = ImageIO.read(new FileInputStream(parkingIconFilePath));
			startIcon = ImageIO.read(new FileInputStream(startIconFilePath));
			endIcon = ImageIO.read(new FileInputStream(endIconFilePath));
			num0Icon=ImageIO.read(new FileInputStream(num0IconPath));
			num1Icon=ImageIO.read(new FileInputStream(num1IconPath));
			num2Icon=ImageIO.read(new FileInputStream(num2IconPath));
			num3Icon=ImageIO.read(new FileInputStream(num3IconPath));
			num4Icon=ImageIO.read(new FileInputStream(num4IconPath));
			num5Icon=ImageIO.read(new FileInputStream(num5IconPath));
			num6Icon=ImageIO.read(new FileInputStream(num6IconPath));
			num7Icon=ImageIO.read(new FileInputStream(num7IconPath));
			num8Icon=ImageIO.read(new FileInputStream(num8IconPath));
			num9Icon=ImageIO.read(new FileInputStream(num9IconPath));
			
		} catch (Exception e) {
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

	static public BufferedImage getStartIcon() {
		return startIcon;
	}

	static public BufferedImage getEndIcon() {
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
			return officeIcon;
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
	public void setStartIcon(BufferedImage image) {
		NodeIcon.startIcon = image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setEndIcon(BufferedImage image) {
		NodeIcon.endIcon = image;
	}

	/**
	 * @return the labIcon
	 */
	public static BufferedImage getLabIcon() {
		return labIcon;
	}

	/**
	 * @return the classroomIcon
	 */
	public static BufferedImage getClassroomIcon() {
		return classroomIcon;
	}

	/**
	 * @return the restroomIcon
	 */
	public static BufferedImage getRestroomIcon() {
		return restroomIcon;
	}

	/**
	 * @return the parkingIcon
	 */
	public static BufferedImage getParkingIcon() {
		return parkingIcon;
	}

	/**
	 * @return the officeIcon
	 */
	public static BufferedImage getOfficeIcon() {
		return officeIcon;
	}

	/**
	 * @param officeIcon the officeIcon to set
	 */
	public static void setOfficeIcon(BufferedImage officeIcon) {
		NodeIcon.officeIcon = officeIcon;
	}

	public static BufferedImage getNum0Icon() {
		return num0Icon;
	}

	public static void setNum0Icon(BufferedImage num0Icon) {
		NodeIcon.num0Icon = num0Icon;
	}

	public static BufferedImage getNum1Icon() {
		return num1Icon;
	}

	public static void setNum1Icon(BufferedImage num1Icon) {
		NodeIcon.num1Icon = num1Icon;
	}

	public static BufferedImage getNum2Icon() {
		return num2Icon;
	}

	public static void setNum2Icon(BufferedImage num2Icon) {
		NodeIcon.num2Icon = num2Icon;
	}

	public static BufferedImage getNum3Icon() {
		return num3Icon;
	}

	public static void setNum3Icon(BufferedImage num3Icon) {
		NodeIcon.num3Icon = num3Icon;
	}

	public static BufferedImage getNum4Icon() {
		return num4Icon;
	}

	public static void setNum4Icon(BufferedImage num4Icon) {
		NodeIcon.num4Icon = num4Icon;
	}

	public static BufferedImage getNum5Icon() {
		return num5Icon;
	}

	public static void setNum5Icon(BufferedImage num5Icon) {
		NodeIcon.num5Icon = num5Icon;
	}

	public static BufferedImage getNum6Icon() {
		return num6Icon;
	}

	public static void setNum6Icon(BufferedImage num6Icon) {
		NodeIcon.num6Icon = num6Icon;
	}

	public static BufferedImage getNum7Icon() {
		return num7Icon;
	}

	public static void setNum7Icon(BufferedImage num7Icon) {
		NodeIcon.num7Icon = num7Icon;
	}

	public static BufferedImage getNum8Icon() {
		return num8Icon;
	}

	public static void setNum8Icon(BufferedImage num8Icon) {
		NodeIcon.num8Icon = num8Icon;
	}

	public static BufferedImage getNum9Icon() {
		return num9Icon;
	}

	public static void setNum9Icon(BufferedImage num9Icon) {
		NodeIcon.num9Icon = num9Icon;
	}


	

}
