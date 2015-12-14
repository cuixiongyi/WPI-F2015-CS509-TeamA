package com.wpi.cs509.teamA.ui.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;


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

	private BufferedImage image;

	private int imageXpos = 0;
	private int imageYpos = 0;
	private int imageStartXpos = 0;
	private int imageStartYpos = 0;
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
		image = map.getImage();

		if (!testBeforeRepaint())
			return;

		LinearTransform lt = model.getLinearTransform();
		// if isInitilized
		// no need to paint the image again
	 
		Graphics2D g2 = (Graphics2D) g;
		if (model.isFisrtChangeMap()) {
			this.setImageXpos(0);
			this.setImageYpos(0);
			model.getCurrentMap().setDisplayScale(1.0f);

			ViewManager.infoPanelSlideDown();
			model.setFisrtChangeMapFalse();
			if (!model.isFisrtFocusNode()) {
				model.setLinearTransform(new LinearTransform());

			}
		}
		if (model.isFisrtFocusNode()) {
			Node node = model.getFocusNode();
			lt.setX(this.getWidth()/2 - (int)(node.getLocation().getX()*model.getCurrentMap().getDisplayScale()));
			lt.setY(this.getHeight()/2 - (int)(node.getLocation().getY()*model.getCurrentMap().getDisplayScale()));
			model.setFisrtFocusNode2False();
		}
		PaintHelperComposite.paintEverything(g2, image, lt);
		ViewManager.getThumbNailPanel().setLocation(0,0);


		/// CXY test
		// GeneralMap tmp = stateContext.getCurrentMap();
		// List<Node> nodes = tmp.getNodes();
		// PaintHelperBasics.paintPath(nodes, g2);

		// g.drawString("XY", this.getImageXpos(), this.getImageYpos());
		// g.drawString("Start", this.getImageStartXpos(),
		// this.getImageStartYpos());

       
        

            /// CXY test
            //GeneralMap tmp = stateContext.getCurrentMap();
            //List<Node> nodes = tmp.getNodes();
            //PaintHelperBasics.paintPath(nodes, g2);
        
//        g.drawString("XY", this.getImageXpos(), this.getImageYpos());
//        g.drawString("Start", this.getImageStartXpos(), this.getImageStartYpos());

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
