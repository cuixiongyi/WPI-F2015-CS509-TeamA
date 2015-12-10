package com.wpi.cs509.teamA.ui.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;


import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;


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


	private MainModel model = null;

	private Image image;

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
	 *
	 */
	public ImageComponent() {

	}


    private boolean testBeforeRepaint()
    {
        try {
            if (null == model)
            {
//                return false;
				throw new NullPointerException();
            }
            if (null == model.getCurrentMap().getImage()) {
//                return false;
                throw new NullPointerException();
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
		GeneralMap map = model.getCurrentMap();
		this.image = map.getImage();
        if ( ! testBeforeRepaint())
            return;

		// if isInitilized
		// no need to paint the image again


        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, imageXpos, imageYpos, Math.round(image.getWidth(this)*map.getDisplayScale()),
				Math.round(image.getHeight(this)*map.getDisplayScale()), this);
        setForeground(Color.RED);

		{
			model.paintOnImage(g2);

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

	public void setModel(MainModel model) {
		this.model = model;
	}



}
