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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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


import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
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
	private StateContext stateContext;
	private final static String LOGIN = "Login";
	private NodeIcon icon = new NodeIcon();
	private List<Node> nodesToPaint;

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.

	private List<Node> prevPaintedNodes;

	private Map<Integer, List<Node>> result;

	private final static int ovalOffset = 5;

	private InputPanel inputPanel;

	private ImageMouseListener mouseListener;
	
	private int imageXpos=0;
	private int imageYpos=0;
	private int imageStartXpos=0;
	private int imageStartYpos=0;
	private int pressxPos;
	private int pressyPos;

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

		// initialize the mouse listener state
		stateContext = null;

		mouseListener = new ImageMouseListener();
		this.addMouseListener(mouseListener);
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageComponent.this.setImageXpos(ImageComponent.this.imageStartXpos+  e.getX()-ImageComponent.this.pressxPos);
				ImageComponent.this.setImageYpos(ImageComponent.this.imageStartYpos+  e.getY()-ImageComponent.this.pressyPos);
				ImageComponent.this.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});



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
        g2.drawImage(image, imageXpos, imageYpos, image.getWidth(this), image.getHeight(this), this);
        setForeground(Color.RED);

		{
			stateContext.paintOnImage(g2);

            /// CXY test
            //GeneralMap tmp = stateContext.getCurrentMap();
            //List<Node> nodes = tmp.getNodes();
            //PaintHelper.paintPath(nodes, g2);
        }


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
			
			
			

			
		}
*/
		
		g2 = null;

	}

    public void setStateContext(StateContext stateContext) {

        this.stateContext = stateContext;
        this.mouseListener.setStateContext(stateContext);
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

	public StateContext getStateContext() {
		return this.stateContext;
	}


	public int getImageXpos() {
		return imageXpos;
	}


	public void setImageXpos(int imageXpos) {
		this.imageXpos = imageXpos;
	}


	public int getImageYpos() {
		return imageYpos;
	}


	public void setImageYpos(int imageYpos) {
		this.imageYpos = imageYpos;
	}


	public int getImageStartXpos() {
		return imageStartXpos;
	}


	public void setImageStartXpos(int imageStartXpos) {
		this.imageStartXpos = imageStartXpos;
	}


	public int getImageStartYpos() {
		return imageStartYpos;
	}


	public void setImageStartYpos(int imageStartYpos) {
		this.imageStartYpos = imageStartYpos;
	}


	public int getPressxPos() {
		return pressxPos;
	}


	public void setPressxPos(int pressxPos) {
		this.pressxPos = pressxPos;
	}


	public int getPressyPos() {
		return pressyPos;
	}


	public void setPressyPos(int pressyPos) {
		this.pressyPos = pressyPos;
	}
	
	


}
