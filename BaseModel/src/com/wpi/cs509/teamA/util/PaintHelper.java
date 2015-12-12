package com.wpi.cs509.teamA.util;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;

import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.ViewManager;

public class PaintHelper {



    static private MainModel model = null;
    private final static int ovalOffset = 10;
    private final static int ovalOffset_SelectedNode = ovalOffset + 5;
    private static BasicStroke basicNodeStrock = new BasicStroke(2);
    private static BasicStroke basicEdgeStrock = new BasicStroke(5);


    /**
     * currently all paint funcitons have 2 overloads, with or without DrawStyleEnum
     * In the case that DrawStyleEnum is not provided
     *      - All functions that draw a list of items (paintNodes, paintEdges)
     *          Set style to a default style
     *      - All functions that draw one single item (paintNode, paintEdge)
     *          use current style to draw
     */

    public enum DrawStyleEnum {
        Undefined,
        BasicNode,
        BasicEdge,
        BasicText,
        NewNode,
        NewEdge,
        SelectedNode,
    }

    private static void setStyle(DrawStyleEnum style, Graphics2D g2) {
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

    public static void paintEdgeAndNodes(List<Node> nodes, Graphics2D g2) {
        for (int i = 0; i < nodes.size()-1; ++i) {
            paintNode(nodes.get(i), g2);
            paintEdge(nodes.get(i), nodes.get(i+1), g2);
        }
        paintNode(nodes.get(nodes.size()-1), g2);
    }

    public static void paintIcon2(Node node, Graphics2D g2, BufferedImage image) {
        if (null == node ||  node.getMap().getMapId() != model.getCurrentMapID())
            return;
        Coordinate coorTrans = transferCoor(node.getLocation());
        int xCoor = coorTrans.getX() - (image.getWidth()/2);
        int yCoor = coorTrans.getY() - (image.getHeight()/2);
        g2.drawImage(image, xCoor, yCoor, image.getWidth(ViewManager.getImageComponent()),
                image.getHeight(ViewManager.getImageComponent()), ViewManager.getImageComponent());
    }
    public static void paintStartEndNode(Graphics2D g2) {
        Node node = model.getStartNode();
        if (node != null) {
            BufferedImage image = NodeIcon.getStartIcon();
            paintIcon2(node, g2, image);
        }

        node = model.getEndNode();
        if (null != node) {
            BufferedImage image = NodeIcon.getEndIcon();
            paintIcon2(node, g2, image);

        }

    }
   public static boolean paintIcon(Node node, Graphics2D g2) {
	   		BufferedImage image = null;
       if (null == node) {
           return false;
       }
	   		if (node == model.getStartNode())
	   		{
	   			image = NodeIcon.getStartIcon();
	   		}
	   		else if (node == model.getEndNode())
	   		{
	   			image = NodeIcon.getEndIcon();
	   		}
	   		else
	   		{
	   			image = NodeIcon.getImage(node);
	   		}
            if (null == image) {
                return false; 
            }
            Coordinate coorTrans = transferCoor(node.getLocation());
            int xCoor = coorTrans.getX() - (image.getWidth()/2);
            int yCoor = coorTrans.getY() - (image.getHeight()/2);
            g2.drawImage(image, xCoor, yCoor, image.getWidth(ViewManager.getImageComponent()),
                    image.getHeight(ViewManager.getImageComponent()), ViewManager.getImageComponent());
            return true;
	}


    public static void paintIcons(List<Node> nodes, Graphics2D g2, DrawStyleEnum style) {
        setStyle(style, g2);
        if (null == nodes)
            return;
        for (Node node : nodes) {
        	paintIcon(node, g2);
        }
    }
    
    public static void paintNodes(List<Node> nodes, Graphics2D g2, DrawStyleEnum style) {
        setStyle(style, g2);
        if (null == nodes)
            return;
        for (Node node : nodes) {
        	if (!paintIcon(node, g2))
        	{
        		paintNode(node, g2);
        	}
        }
    }
    
    
    
    public static void paintNodes(List<Node> nodes, Graphics2D g2) {
        paintNodes(nodes, g2, DrawStyleEnum.BasicNode);
    }

    /**
     * PaintNode function will paint node with defined style
     * @param node
     * @param g2
     * @param style
     */
    public static void paintNode(Node node, Graphics2D g2, DrawStyleEnum style) {
        if (null == node) {
            return;
        }
        setStyle(style, g2);
        Coordinate xy = transferCoor(node.getLocation());
        int tmp = ovalOffset;
        if (DrawStyleEnum.SelectedNode == style) {
            tmp = ovalOffset_SelectedNode;
        }
        g2.fillOval(xy.getX() - tmp, xy.getY() - tmp, tmp*2, tmp*2);

    }
    public static void paintNode(Node node, Graphics2D g2) {
        if (null == node)
            return;
		Coordinate xy = transferCoor(node.getLocation());
		g2.fillOval(xy.getX() - ovalOffset, xy.getY() - ovalOffset, ovalOffset*2, ovalOffset*2);
    }

    public static void paintEdges(List<Edge> edges, Graphics2D g2) {
        paintEdges(edges, g2, DrawStyleEnum.BasicEdge);
    }
    public static void paintEdges(List<Edge> edges, Graphics2D g2, DrawStyleEnum style) {
        setStyle(style, g2);
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

    public static void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2, DrawStyleEnum style) {
        setStyle(style, g2);
        paintEdge(nodeSrc, nodeDest, g2);
    }
    public static void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2) {
        Coordinate start = transferCoor(nodeSrc.getLocation());
        Coordinate end = transferCoor(nodeDest.getLocation());
        if (null == nodeSrc || null == nodeDest)
            return;

        //g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    public static void paintEverything(Graphics2D g2) {
        GeneralMap map = model.getCurrentMap();
        BufferedImage image = map.getImage();
        ImageComponent imageComponent = ViewManager.getImageComponent();

        g2.drawImage(image, imageComponent.getImageXpos(),
                imageComponent.getImageYpos(),
                Math.round(image.getWidth(imageComponent)*map.getDisplayScale()),
                Math.round(image.getHeight(imageComponent)*map.getDisplayScale()), imageComponent);
        model.paintOnImage(g2);
    }
    public static void paintRoute(Graphics2D g2) {
//        ArrayList<ArrayList<Node>> multiMapPath = model.getMultiMapPathLists();
//        if (null != multiMapPath && 0 != multiMapPath.size()) {
//            int idx = model.getCurrentMapID()-1;
//            PaintHelper.paintPath(multiMapPath.get(idx), g2);
//
//        }
        ArrayList<Node> path = model.getRouteOnCurrentMap();
        if (null == path)
            return;
        PaintHelper.paintPath(path, g2);


    }

   public static void paintPath(List<Node> nodes, Graphics2D g2) {
        if (null != nodes) {
            setStyle(DrawStyleEnum.NewEdge, g2);
            for (int i = 0; i < nodes.size() - 1; ++i) {
                paintEdge(nodes.get(i), nodes.get(i + 1), g2);
            }
        }
    }

    public static Coordinate backTransferCoor(Coordinate origin) {
        Coordinate result=new Coordinate();
        float scale=model.getCurrentMap().getDisplayScale();
        result.setX(Math.round((origin.getX()-ViewManager.getImageComponent().getImageXpos())/scale));
        result.setY(Math.round((origin.getY()-ViewManager.getImageComponent().getImageYpos())/scale));

        return result;
    }

    public static Coordinate transferCoor(Coordinate origin)
   {
	   Coordinate result=new Coordinate();
	   float scale=model.getCurrentMap().getDisplayScale();
	   result.setX(Math.round(origin.getX()*scale+ViewManager.getImageComponent().getImageXpos()));
	   result.setY(Math.round(origin.getY()*scale+ViewManager.getImageComponent().getImageYpos()));
	
	   return result;
	   
   }

    public static void setModel(MainModel model) {
        PaintHelper.model = model;
    }
    public static String dirtmp = "/BaseModel/src/";
//    public static String dirtmp = "/src/";
    public static String getUserDir() {
        return System.getProperty("user.dir") + dirtmp;
    }

}
