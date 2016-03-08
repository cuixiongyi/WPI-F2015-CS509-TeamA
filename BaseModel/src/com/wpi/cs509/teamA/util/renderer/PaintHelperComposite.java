package com.wpi.cs509.teamA.util.renderer;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.ui.view.component.ImageComponent;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeIcon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by cuixi on 12/14/2015.
 */
public class PaintHelperComposite extends PaintHelperBasics {

	static private MainModel model = null;

	static private LinearTransform linearTransform = null;

	public static void paintRoute(Graphics2D g2, LinearTransform pLinearTransform) {

		PaintHelperBasics.setLinearTransform(pLinearTransform);

		Path path = model.getCurrentPath();
		if (null == path)
			return;
		paintPath(path, g2, pLinearTransform);

	}

	public static void paintEverything(Graphics2D g2, BufferedImage image, LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		ImageComponent imageComponent = ViewManager.getImageComponent();

		double scale = pLinearTransform.getScale();
		g2.drawImage(image, (int) pLinearTransform.getX(), (int) pLinearTransform.getY(),
				(int) Math.round(image.getWidth(imageComponent) * scale),
				(int) Math.round(image.getHeight(imageComponent) * scale), imageComponent);
		model.paintOnImage(g2);
	}

	public static void paintStartEndNode(Graphics2D g2, LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		if (pLinearTransform.getScale() < 0.5) {
			return;
		}

		Node node = model.getStartNode();
		if (node != null) {
			BufferedImage image = NodeIcon.getStartIcon();
			PaintHelperBasics.paintIcon2(node, g2, image);
		}

		ArrayList<Node> endNodes = model.getEndNode();
		if (null != endNodes) {
			BufferedImage image = NodeIcon.getEndIcon();
			for (Node endNode : endNodes) {
				PaintHelperBasics.paintIcon2(endNode, g2, image);
			}

		}

	}

	public static void paintStartEndNode(Graphics2D g2, Path path, LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		if (pLinearTransform.getScale() < 0.5) {
			return;
		}
		if (null == path) {
			return;
		}

		Node node = model.getStartNode();
		if (node != null) {
			BufferedImage image = NodeIcon.getStartIcon();
			if (path.getMap() == node.getMap())
				PaintHelperBasics.paintIconForce(node, g2, image);
		}

		ArrayList<Node> endNodes = model.getEndNode();
		if (null != endNodes) {
			BufferedImage image = NodeIcon.getEndIcon();
			for (Node endNode : endNodes) {
				if (path.getMap().getMapId() == 1) {
					int a = 0;
				}
				if (path.getMap() == endNode.getMap()) {
					PaintHelperBasics.paintIconForce(endNode, g2, image);

				}

			}

		}

	}

	public static void paintDots(List<Node> nodes, Graphics2D g2, LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		for (Node node : nodes) {
			PaintHelperBasics.paintDot(node, g2, DrawStyleEnum.BasicNode);

		}
	}

	public static void paintDots(List<Node> nodes, Graphics2D g2, LinearTransform pLinearTransform,
			DrawStyleEnum style) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		for (Node node : nodes) {
			PaintHelperBasics.paintDot(node, g2, style);

		}
	}

	public static void paintEdgeAndNodes(java.util.List<Node> nodes, Graphics2D g2) {
		for (int i = 0; i < nodes.size() - 1; ++i) {
			PaintHelperBasics.paintDot(nodes.get(i), g2);
			PaintHelperBasics.paintEdge(nodes.get(i), nodes.get(i + 1), g2);
		}
		PaintHelperBasics.paintDot(nodes.get(nodes.size() - 1), g2);
	}

	public static void paintPath(Path path, Graphics2D g2, LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		if (null != path && 0 < path.getNodes().size()) {
			ArrayList<Node> nodes = path.getNodes();
			PaintHelperBasics.setStyle(PaintHelperBasics.DrawStyleEnum.NewEdge, g2);
			for (int i = 0; i < nodes.size() - 1; ++i) {
				PaintHelperBasics.paintEdge(nodes.get(i), nodes.get(i + 1), g2);
			}
		}
	}

	public static void paintNodes(java.util.List<Node> nodes, Graphics2D g2, PaintHelperBasics.DrawStyleEnum style,
			LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		// System.out.println("Drawing nodes.");
		PaintHelperBasics.setStyle(style, g2);
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		g2.setColor(Color.BLACK);
		if (null == nodes)
			return;
		if (pLinearTransform.getScale() < 0.5) {
			return;
		}
		for (Node node : nodes) {
			if (!model.hasFilter(node.getNodeType())) {
				PaintHelperBasics.paintIcon(node, g2);
			} else {
				if (model.isLoginAdmin()) {
					PaintHelperBasics.paintDot(node, g2);
				}
			}
		}
		PaintHelperBasics.setStyle(style, g2);
	}

	public static void paintNodes(java.util.List<Node> nodes, Graphics2D g2, LinearTransform pLinearTransform) {
		paintNodes(nodes, g2, PaintHelperBasics.DrawStyleEnum.BasicNode, pLinearTransform);
	}

	public static void paintEdges(java.util.List<Edge> edges, Graphics2D g2, LinearTransform pLinearTransform) {
		paintEdges(edges, g2, PaintHelperBasics.DrawStyleEnum.BasicEdge, pLinearTransform);
	}

	public static void paintEdges(java.util.List<Edge> edges, Graphics2D g2, PaintHelperBasics.DrawStyleEnum style,
			LinearTransform pLinearTransform) {
		PaintHelperBasics.setLinearTransform(pLinearTransform);
		PaintHelperBasics.setStyle(style, g2);
		if (null == edges)
			return;
		for (Edge edge : edges) {
			PaintHelperBasics.paintEdge(edge.getNode1(), edge.getNode2(), g2);
		}
	}

	public static void setModel(MainModel model) {
		PaintHelperComposite.model = model;
	}

	public static LinearTransform getLinearTransform() {
		return linearTransform;
	}

	public static void setLinearTransform(LinearTransform linearTransform) {
		PaintHelperComposite.linearTransform = linearTransform;
	}
}
