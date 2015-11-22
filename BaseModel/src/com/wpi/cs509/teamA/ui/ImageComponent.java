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
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.UIDataBuffer;
import com.wpi.cs509.teamA.ui.StateContext;


/**
 * An component to show the images. This component has two different states
 * based on whether the user logged in or not. The state of this component will
 * change in the run time. As a admin user, a person would have more mouse
 * operation.
 * 
 * @author CS 509-Team A
 *
 */
@SuppressWarnings("serial")
public class ImageComponent extends JComponent {

	private Image image;
	private int imgWidth;
	private int imgHeight;
	private StateContext stateContext;
	private final static String LOGIN = "Login";
	private NodeIcon icon = new NodeIcon();
	private List<Node> nodesToPaint;

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.
	private MouseListener normalUserMouseListener;
	private int xPos;
	private int yPos;

	private List<Node> prevPaintedNodes;

	private Map<Integer, List<Node>> result;

	private static int adminClicked = 2;

	private final static int ovalOffset = 5;

	private static boolean isAdmin;

	private InputPanel inputPanel;

	public void setStateContext(StateContext stateContext) {
		this.stateContext = stateContext;
	}


	// admin will get a different repaint method
	// private boolean isAdmin;

	/**
	 * Constructor for image component The constructor will also add all the
	 * Listeners to the inputPanel it got
	 * 
	 * /@param inputPanel
	 *            an instance of inputPanel will add listeners to the buttons in
	 *            the inputPanel. inputPanel must be final since it will be used
	 *            in the inner class
	 */
	public ImageComponent() {
		ImageComponent.isAdmin = false;

		// initialize the mouse listener state
		stateContext = null;



        //setImageComponent(this);

	}


    protected void paintIcons(List<Node> nodes, Graphics2D g2) {
        for (Node node : nodes) {
            BufferedImage image = icon.getImage(node);
            g2.drawImage(image, node.getLocation().getX(), node.getLocation().getY(), image.getWidth(this),
                    image.getHeight(this), this);
        }
	}

    protected void paintNode(Node node, Graphics2D g2) {
		Coordinate xy = node.getLocation();
		g2.fillOval(xy.getX() - ovalOffset, xy.getY() - ovalOffset, 10, 10);
    }

    protected void paintEdge(Node nodeSrc, Node nodeDest, Graphics2D g2) {
        Coordinate start = nodeSrc.getLocation();
        Coordinate end = nodeDest.getLocation();

        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(start.getX(), start.getY(), end.getX(), end.getY()));
    }

    protected void paintPath(List<Node> nodes, Graphics2D g2) {
        if (null != nodes) {
            for (int i = 0; i < nodes.size() - 1; ++i) {
                paintEdge(nodes.get(i), nodes.get(i + 1), g2);
            }
        }
    }

	public void clearText() {

	}

	public void paintText() {

	}

    private boolean testBeforePaint()
    {
        try {
            if (null == stateContext)
            {
                return false;
                //throw new Exception("null stateContext in ImageComponent");
            }
            if (null == stateContext.getCurrentMap().getImage()) {
                return false;
                //throw new Exception("null image in ImageComponent");
            }
			if (null == this.image) {
				return false;
			}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

	@Override
	public void paintComponent(Graphics g) {

        /// test for null stateContext and null image
		this.image = stateContext.getCurrentMap().getImage();
        if ( ! testBeforePaint())
            return;


		// if isInitilized
		// no need to paint the image again


        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);
        setForeground(Color.RED);

        /**
            Paint path
         */
        List<Node> pathNode = this.stateContext.getPath();
        paintPath(pathNode, g2);

        List<Node> iconNodes = this.getStateContext().getIconNodes();
        paintIcons(iconNodes, g2);

/*

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
			int sourceX = pathNodeList.get(0).getLocation().getX();
			int sourceY = pathNodeList.get(0).getLocation().getY();

			int desX = pathNodeList.get(pathNodeList.size() - 1).getLocation().getX();
			int desY = pathNodeList.get(pathNodeList.size() - 1).getLocation().getY();
			
			
			
			g2.drawOval(sourceX - ovalOffset, sourceY - ovalOffset, 10, 10);
			g2.drawOval(desX - ovalOffset, desY - ovalOffset, 10, 10);
			Font font = g.getFont().deriveFont(20.0f);
			g.setFont(font);
			g2.drawString("Source", sourceX, sourceY - ovalOffset);
			g2.drawString("Destination", desX, desY - ovalOffset);
			
		}
*/
		
		g2 = null;

	}

	public void setInputPanel(InputPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	/**
	 * @return the inputPanel
	 */
	public InputPanel getInputPanel() {
		return inputPanel;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param xPos
	 *            the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @param yPos
	 *            the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public NormalUserMouseListener getNormalUserMouseListener() {
		return (NormalUserMouseListener) this.normalUserMouseListener;
	}
/*
	public AdminMouseListener getAdminMouseListener() {
		return (AdminMouseListener) this.adminMouseListener;
	}
*/
	public StateContext getStateContext() {
		return this.stateContext;
	}

	public void incrementAdminClicked() {
		ImageComponent.adminClicked++;
	}

	public static void setIsAdmin(boolean isAdmin) {
		ImageComponent.isAdmin = isAdmin;
	}

}
