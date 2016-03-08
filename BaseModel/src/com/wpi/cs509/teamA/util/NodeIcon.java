package com.wpi.cs509.teamA.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.renderer.PaintHelperBasics;


public class NodeIcon {
	private Node node;

	private static String labIconName = "Lab_Icon.png";
	private static String classroomIconName = "Classroom_Icon.png";
	private static String officeIconName = "Office_Icon.png";
	private static String restroomIconName = "Restroom_Icon.png";
	private static String parkingIconName = "Parking_Icon.png";
	private static String startIconName = "Start_Icon.png";
	private static String endIconName = "End_Icon.png";
	private static String nextIconName = "next_Icon.png";
	private static String rotateIconName= "rotate_Icon.png";

	private static String numPrefix = "numbers-";
	private static String numPostfix = "-icon.png";

	

	private static String labIconFilePath = PaintHelperBasics.getUserDir() + labIconName;
	private static String classroomIconFilePath = PaintHelperBasics.getUserDir() + classroomIconName;
	private static String officeIconFilePath = PaintHelperBasics.getUserDir() + officeIconName;
	private static String restroomIconFilePath = PaintHelperBasics.getUserDir() + restroomIconName;
	private static String parkingIconFilePath = PaintHelperBasics.getUserDir() + parkingIconName;
	private static String startIconFilePath = PaintHelperBasics.getUserDir() + startIconName;
	private static String endIconFilePath = PaintHelperBasics.getUserDir() + endIconName;
	private static String nextIconFilePath = PaintHelperBasics.getUserDir() + nextIconName;
	private static String rotateIconFilePath = PaintHelperBasics.getUserDir() + rotateIconName;



	private static String iconDir= PaintHelperBasics.getUserDir()+"numicon/"+numPrefix;
	

	private static BufferedImage labIcon;
	private static BufferedImage classroomIcon;
	private static BufferedImage restroomIcon;
	private static BufferedImage parkingIcon;
	private static BufferedImage officeIcon;
	private static BufferedImage startIcon;
	private static BufferedImage endIcon;
	private static BufferedImage nextIcon;
	private static BufferedImage rotateIcon;
	
	private static ArrayList<BufferedImage> numIcons;

	
	static {
		try {
			labIcon = ImageHelper.readImage(labIconName);
			classroomIcon = ImageHelper.readImage(classroomIconName);
			officeIcon = ImageHelper.readImage(officeIconName);
			restroomIcon = ImageHelper.readImage(restroomIconName);
			parkingIcon = ImageHelper.readImage(parkingIconName);
			startIcon = ImageHelper.readImage(startIconName);
			endIcon = ImageHelper.readImage(endIconName);
			nextIcon=ImageHelper.readImage(nextIconName);
			rotateIcon=ImageHelper.readImage(rotateIconName);

			int numCount = 9;
			numIcons = new ArrayList<BufferedImage>(numCount);

			for (int ii = 0; ii <= numCount; ii++) {
				String tmp = iconDir+Integer.toString(ii)+numPostfix;
//				numIcons.add(ImageIO.read(new FileInputStream(tmp)));
			}

			
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

	public static BufferedImage getNumIcon(int num) {
		return numIcons.get(num);
	}
	
	public static BufferedImage getNextIcon() {
		return nextIcon;
	}
	
	public static ImageIcon getStartIconSmall(){
		ImageIcon stIcon =new ImageIcon(startIcon);
    	Image stImage=stIcon.getImage();
    	Image newstImage=stImage.getScaledInstance(33, 33,  java.awt.Image.SCALE_SMOOTH);
    	stIcon = new ImageIcon(newstImage);
    	return stIcon;
	}
	
	public static ImageIcon getEndIconSmall(){
		ImageIcon edIcon =new ImageIcon(endIcon);
    	Image edImage=edIcon.getImage();
    	Image newedImage=edImage.getScaledInstance(33, 33,  java.awt.Image.SCALE_SMOOTH);
    	edIcon = new ImageIcon(newedImage);
    	return edIcon;
	}

	public static ImageIcon getRotateIcon(){
		return new ImageIcon(ImageHelper.resizeImage(rotateIcon,20,30));

	}


	

}
