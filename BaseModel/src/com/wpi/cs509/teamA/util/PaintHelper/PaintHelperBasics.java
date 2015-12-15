package com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;

import com.wpi.cs509.teamA.ui.UIConstant;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeIcon;
import com.wpi.cs509.teamA.util.NodeType;

public class PaintHelperBasics {

	/**
	 * currently all paint funcitons have 2 overloads, with or without
	 * DrawStyleEnum In the case that DrawStyleEnum is not provided - All
	 * functions that draw a list of items (paintNodes, paintEdges) Set style to
	 * a default style - All functions that draw one single item (paintDot,
	 * paintEdge) use current style to draw
	 */

	static private MainModel model = null;
	static private LinearTransform linearTransform = null;
	private final static int ovalOffset = 10;
	private final static int ovalOffset_SelectedNode = ovalOffset + 5;
	private static BasicStroke basicNodeStrock = new BasicStroke(2);
	private static BasicStroke basicEdgeStrock = new BasicStroke(5);
	public final static double SCALELOWWERBOUND_DRAWICON = 1.1;

	/**
	 * currently all paint funcitons have 2 overloads, with or without
	 * DrawStyleEnum In the case that DrawStyleEnum is not provided - All
	 * functions that draw a list of items (paintNodes, paintEdges) Set style to
	 * a default style - All functions that draw one single item (paintDot,
	 * paintEdge) use current style to draw
	 */

	public enum DrawStyleEnum {
		Undefined, BasicNode, BasicEdge, BasicText, NewNode, NewEdge, SelectedNode,
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	protected static void setStyle(DrawStyleEnum style, Graphics2D g2) {
		switch (style) {
		case Undefined:
		case BasicNode:
			g2.setStroke(basicNodeStrock);
			g2.setColor(Color.blue);
			break;
		case BasicEdge:
			g2.setStroke(basicEdgeStrock);
			g2.setColor(Color.blue);
			break;
		case BasicText:

			break;
		case NewNode:
			g2.setStroke(basicNodeStrock);
			g2.setColor(Color.red);
			break;
		case NewEdge:
			g2.setStroke(basicEdgeStrock);
			g2.setColor(Color.red);
			break;
		case SelectedNode:
			g2.setColor(Color.red);
			break;

		}
	}



	public static void paintIcon2(Node node, Graphics2D g2, BufferedImage image) {
		if (null == node || node.getMap().getMapId() != model.getCurrentMapID())
			return;
		Coordinate coorTrans = linearTransform.transferCoor(node.getLocation());
		int xCoor = coorTrans.getX() - (image.getWidth() / 2);
		int yCoor = coorTrans.getY() - (image.getHeight() / 2);
		g2.drawImage(image, xCoor, yCoor, image.getWidth(ViewManager.getImageComponent()),
				image.getHeight(ViewManager.getImageComponent()), ViewManager.getImageComponent());
	}



	public static boolean paintIcon(Node node, Graphics2D g2) {
		BufferedImage image = null;
		if (null == node) {
			return false;
		}
		image = NodeIcon.getImage(node);

		if (null == image) {
			return false;
		}
		Coordinate coorTrans = linearTransform.transferCoor(node.getLocation());
		int xCoor = coorTrans.getX() - (image.getWidth() / 2);
		int yCoor = coorTrans.getY() - (image.getHeight() / 2);
		g2.drawImage(image, xCoor, yCoor, image.getWidth(ViewManager.getImageComponent()),
				image.getHeight(ViewManager.getImageComponent()), ViewManager.getImageComponent());
		
		if (linearTransform.getScale() > SCALELOWWERBOUND_DRAWICON)
		{
			String nodeTitle = node.getName();
			if (node.getNodeType() == NodeType.PARKING)
			{
				nodeTitle += " (" + model.getParkingAvilibility().get(node.getName()) + ")"; // where to change parking info
			}

		    FontMetrics metrics = g2.getFontMetrics();
		    int titleXCoor = (coorTrans.getX() - (metrics.stringWidth(nodeTitle)/2));
		    int titleYCoor = coorTrans.getY() - 25;
			
			g2.drawString(nodeTitle, titleXCoor, titleYCoor);
		}
		return true;
	}

	/**
	 * PaintNode function will paint node with defined style
	 * 
	 * @param node
	 * @param g2
	 * @param style
	 */
	public static void paintDot(Node node, Graphics2D g2, DrawStyleEnum style) {
		if (null == node) {
			return;
		}
		setStyle(style, g2);
		Coordinate xy = linearTransform.transferCoor(node.getLocation());
		int tmp = ovalOffset;
		if (DrawStyleEnum.SelectedNode == style) {
			tmp = ovalOffset_SelectedNode;
		}
		g2.fillOval(xy.getX() - tmp, xy.getY() - tmp, tmp * 2, tmp * 2);

	}

	public static void paintDot(Node node, Graphics2D g2) {
		if (null == node)
			return;
		Coordinate xy = linearTransform.transferCoor(node.getLocation());
		g2.fillOval(xy.getX() - ovalOffset, xy.getY() - ovalOffset, ovalOffset * 2, ovalOffset * 2);
	}



	public static void paintEdge(Edge edge, Graphics2D g2) {
		if (null == edge)
			return;
		paintEdge(edge.getNode1(), edge.getNode2(), g2);
	}

	public static void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2, DrawStyleEnum style) {
		setStyle(style, g2);
		paintEdge(nodeSrc, nodeDest, g2);
	}

	public static void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2) {
		Coordinate start = linearTransform.transferCoor(nodeSrc.getLocation());
		Coordinate end = linearTransform.transferCoor(nodeDest.getLocation());
		if (null == nodeSrc || null == nodeDest)
			return;

		// g2.setStroke(new BasicStroke(5));
		g2.draw(new Line2D.Float(start.getX(), start.getY(), end.getX(), end.getY()));
	}

//	public static void paintPath(List<Node> nodes, Graphics2D g2) {
//		if (null != nodes || nodes.size() > 0) {
//			setStyle(DrawStyleEnum.NewEdge, g2);
//			if (model.getCurrentMap().getMapId() != nodes.get(0).getMap().getMapId()) {
//				return;
//			}
//
//			for (int i = 0; i < nodes.size() - 1; ++i) {
//				paintEdge(nodes.get(i), nodes.get(i + 1), g2);
//			}
//		}
//	}






	public static LinearTransform getLinearTransform() {
		return linearTransform;
	}

	public static void setLinearTransform(LinearTransform linearTransform) {
		PaintHelperBasics.linearTransform = linearTransform;
	}


	public static void setModel(MainModel model) {
		PaintHelperBasics.model = model;
	}

//	 public static String dirtmp = "/BaseModel/src/";
	public static String dirtmp = "/src/";

	public static String getUserDir() {
		return System.getProperty("user.dir") + dirtmp;
	}


}
