package com.wpi.cs509.teamA.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.ui.StateContext;

public class PaintHelper {
	
	private static StateContext stateContext;
	private final static int ovalOffset = 5;
	


   public static void paintEdgeAndNodes(List<Node> nodes, Graphics2D g2) {
        for (int i = 0; i < nodes.size()-1; ++i) {
            paintNode(nodes.get(i), g2);
            paintEdge(nodes.get(i), nodes.get(i+1), g2);
        }
        paintNode(nodes.get(nodes.size()-1), g2);
    }

//   public static void paintIcons(List<Node> nodes, Graphics2D g2) {
//        for (Node node : nodes) {
//            BufferedImage image = icon.getImage(node);
//            g2.drawImage(image, node.getLocation().getX(), node.getLocation().getY(), image.getWidth(stateContext.getImageComponent()),
//                    image.getHeight(stateContext.getImageComponent()), stateContext.getImageComponent());
//        }
//	}


    public static void paintNodes(List<Node> nodes, Graphics2D g2) {
        if (null == nodes)
            return;
        for (Node node : nodes) {
            paintNode(node, g2);
        }

    }

   public static void paintNode(Node node, Graphics2D g2) {
        if (null == node)
            return;
		Coordinate xy = transferCoor(node.getLocation());
		g2.fillOval(xy.getX() - ovalOffset, xy.getY() - ovalOffset, 10, 10);
    }

    public static void paintEdges(List<Edge> edges, Graphics2D g2) {
        if (null == edges )
            return;
        for (Edge edge : edges) {
            paintEdge(edge.getNode1(), edge.getNode2(), g2);
        }
    }

    public static void paintEdge(Edge edge, Graphics2D g2) {
        if (null == edge )
            return;
        paintEdge(edge.getNode1(), edge.getNode2(), g2);
    }

    public static void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2) {
        Coordinate start = transferCoor(nodeSrc.getLocation());
        Coordinate end = transferCoor(nodeDest.getLocation());
        if (null == nodeSrc || null == nodeDest)
            return;

        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(start.getX(), start.getY(), end.getX(), end.getY()));
    }

   public static void paintPath(List<Node> nodes, Graphics2D g2) {
        if (null != nodes) {
            for (int i = 0; i < nodes.size() - 1; ++i) {
                paintEdge(nodes.get(i), nodes.get(i + 1), g2);
            }
        }
    }
   
   public static Coordinate transferCoor(Coordinate origin)
   {
	   Coordinate result=new Coordinate();
	   float scale=stateContext.getCurrentMap().getDisplayScale();
	   result.setX(origin.getX()+stateContext.getImageComponent().getImageXpos());
	   result.setY(origin.getY()+stateContext.getImageComponent().getImageYpos());
	
	   return result;
	   
   }

public StateContext getStateContext() {
	return stateContext;
}

public static void setStateContext(StateContext pstateContext) {
	PaintHelper.stateContext = pstateContext;
}
   


}
