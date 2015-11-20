package com.wpi.cs509.teamA.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.UIDataBuffer;
import com.wpi.cs509.teamA.ui.ImageComponent;

/**
 * An component to show the images. This component has two different states
 * based on whether the user logged in or not. The state of this component will
 * change in the run time. As a admin user, a person would have more mouse
 * operation.
 * 
 * @author CS 509-Team A
 *
 */
public class ImageComponentAdmin extends ImageComponent {

	/**
	 * Constructor for image component The constructor will also add all the
	 * Listeners to the inputPanel it got
	 * 
	 * @param inputPanel
	 *            an instance of inputPanel will add listeners to the buttons in
	 *            the inputPanel. inputPanel must be final since it will be used
	 *            in the inner class
	 */
	public ImageComponentAdmin() {
    }



        @Override
        public void paintComponent(Graphics g) {

            /** call the super class paint first */
            super.paintComponent(g);
            // if isInitilized
            // no need to paint the image again

            if (null == image) {
                return;
            }

            //paint all of the nodes
            Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();

            if (allNodes != null && allNodes.get(1).size() != 0) {
                int x, y;
                for (int i = 0; i < allNodes.get(1).size(); i++) {
                    x = allNodes.get(1).get(i).getLocation().getX();
                    y = allNodes.get(1).get(i).getLocation().getY();
                    g.fillOval(x - ovalOffset, y - ovalOffset, 10, 10);
                }
            }

            // paint all the edges
            Set<NodeRelation> allEdges = UIDataBuffer.getAllEdges();
            if (allEdges.size() != 0) {
                for (NodeRelation edge : allEdges) {
                    int xstart, ystart, xend, yend;
                    xstart = edge.getFirstNodeCoordinate().getX();
                    ystart = edge.getFirstNodeCoordinate().getY();

                    xend = edge.getSecondNodeCoordinate().getX();
                    yend = edge.getSecondNodeCoordinate().getY();

                    g2.setStroke(new BasicStroke(5));
                    g2.draw(new Line2D.Float(xstart, ystart, xend, yend));

                }
            }

            if (pathNodeList != null && pathNodeList.get(0).getMapId() == UIDataBuffer.getCurrentMapId()) {

                // paint the route
                if (pathNodeList != null && pathNodeList.size() != 0) {
                    for (int i = 0; i < pathNodeList.size() - 1; i++) {
                        int xstart, ystart, xend, yend;
                        xstart = pathNodeList.get(i).getLocation().getX();
                        ystart = pathNodeList.get(i).getLocation().getY();

                        xend = pathNodeList.get(i + 1).getLocation().getX();
                        yend = pathNodeList.get(i + 1).getLocation().getY();

                        g2.setStroke(new BasicStroke(5));
                        g2.draw(new Line2D.Float(xstart, ystart, xend, yend));
                        // System.out.println("draw line.." + xstart + " " + ystart
                        // + "
                        // " + xend + " " + yend);
                    }
                }
                int sourceX=pathNodeList.get(0).getLocation().getX();
                int sourceY=pathNodeList.get(0).getLocation().getY();

                int desX=pathNodeList.get(pathNodeList.size() - 1).getLocation().getX();
                int desY=pathNodeList.get(pathNodeList.size() - 1).getLocation().getY();
                g2.drawOval(sourceX-ovalOffset, sourceY-ovalOffset, 10, 10);
                g2.drawOval(desX-ovalOffset, desY-ovalOffset, 10, 10);
                Font font = g.getFont().deriveFont( 20.0f );
                g.setFont( font );
                g2.drawString("Source", sourceX, sourceY-ovalOffset);
                g2.drawString("Destination", desX, desY-ovalOffset);

            }

            g2 = null;

        }



});







