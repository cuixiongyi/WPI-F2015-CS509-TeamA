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
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.util.UIDataBuffer;
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

	// TODO: make these to classes singleton. We should avoid to initialize them
	// here.
	private MouseListener normalUserMouseListener;
	private MouseListener adminMouseListener;
	
	private int xPos;
	private int yPos;
	private int imageXpos=0;
	private int imageYpos=0;
	private int pressxPos;
	private int pressyPos;

	private List<Node> pathNodeList;

	private Map<Integer, List<Node>> result;

	private static int adminClicked = 2;

	private InputPanel inputPanel;
	private final static int ovalOffset = 5;

	private static boolean isAdmin;

	// admin will get a different repaint method
	// private boolean isAdmin;

	public ImageComponent() {

		// initialize the mouse listener state
		stateContext = new StateContext();
	}

	/**
	 * Constructor for image component The constructor will also add all the
	 * Listeners to the inputPanel it got
	 * 
	 * @param inputPanel
	 *            an instance of inputPanel will add listeners to the buttons in
	 *            the inputPanel. inputPanel must be final since it will be used
	 *            in the inner class
	 */
	public ImageComponent(final InputPanel inputPanel) {
		ImageComponent.isAdmin = false;
		this.inputPanel = inputPanel;
		// initialize the mouse listener state
		stateContext = new StateContext();

		normalUserMouseListener = new NormalUserMouseListener(this);
		adminMouseListener = new AdminMouseListener(this);

		// TODO: Move this part to input panel..
		// we need to add the event listener before the state pattern begins
		this.addMouseListener(normalUserMouseListener);
		
		
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageComponent.this.setImageXpos(e.getX()-ImageComponent.this.pressxPos);
				ImageComponent.this.setImageYpos(e.getY()-ImageComponent.this.pressyPos);
				ImageComponent.this.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		

		// Click the SEARCH button..
		inputPanel.getBtnSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// TODO: need to check if the input is valid!!

				// TODO: make the AlgoController singleton and use setter and
				// getter to operate the instance..

				// We will go to the backend here.. For now, all the resources
				// should be ready!
				AlgoController algoController = new AlgoController(inputPanel.getSourcePoint(),
						inputPanel.getDesPoint());

				// get the result of the search..
				result = null;
				result = algoController.getRoute();

				// get a list of a map, so that we can draw line on that map..
				pathNodeList = null;
				pathNodeList = result.get(UIDataBuffer.getCurrentMapId());

				// we need to give all the information to the repaint metho
				repaint();

			}

		});

		/**
		 * Add listener to the admin login button
		 */
		inputPanel.getAdminLogin().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// TODO: we need a pop up window here to verify the admin role.
				// If it is the admin, give it the admin mouse click event. If
				// not, give it normal user

				if (adminClicked % 2 == 0) {
					AdminDialog adminDialog = new AdminDialog(ImageComponent.this, inputPanel);
					adminDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					adminDialog.setVisible(isFocusable());

				} else {

					JOptionPane.showMessageDialog(null, "You have logged out");
					stateContext.switchState(ImageComponent.this, normalUserMouseListener, adminMouseListener);
					inputPanel.getBtnNeighborManage().setVisible(false);
					inputPanel.getAdminLogin().setText(LOGIN);
					inputPanel.getBtnSynchronize().setVisible(false);
					isAdmin = false;
					adminClicked++;
					ImageComponent.this.repaint();
				}
			}

		});

		// TODO: Make the map related things into a enum class..
		inputPanel.getComboBoxMap().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (inputPanel.getComboBoxMap().getSelectedItem().equals("Campus Map")) {
					selectImage("Final_Campus_Map", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(1);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-G")) {
					selectImage("Final_AK_Ground_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(2);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-1")) {
					selectImage("Final_AK_First_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(3);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-2")) {
					selectImage("Final_AK_Second_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(4);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("AK-3")) {
					selectImage("Final_AK_Third_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(5);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-1")) {
					selectImage("Final_Project_Center_First_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(6);
					inputPanel.getBtnSynchronize().doClick();
				} else if (inputPanel.getComboBoxMap().getSelectedItem().equals("PC-2")) {
					selectImage("Final_Project_Center_Second_Floor", ImageComponent.this);
					UIDataBuffer.setCurrentMapId(7);
					inputPanel.getBtnSynchronize().doClick();
				}
			}

		});

	}

	/**
	 * 
	 * Select the right image and paint it on the image component
	 * 
	 * @param mapName
	 * @param imageComponent
	 */
	private void selectImage(String mapName, ImageComponent imageComponent) {
		imageComponent.setImagePath(System.getProperty("user.dir") + "/src/" + mapName + ".jpg");
		imageComponent.repaint();

	}

	/**
	 * Get the path of the image we will load. Temporary use..
	 * 
	 * @param imgPath
	 *            path of the image, or in the database?
	 */
	public void setImagePath(String imgPath) {

		// TODO: It seems that we will store our image in the database.. so how
		// to implement that?

		try {
			image = ImageIO.read(new FileInputStream(imgPath));
			this.setImgWidth(image.getWidth(this));
			this.setImgHeight(image.getHeight(this));
			this.repaint();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the inputPanel
	 */
	public InputPanel getInputPanel() {
		return inputPanel;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @return the imgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}

	/**
	 * @return the imgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @param imgWidth
	 *            the imgWidth to set
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	/**
	 * @param imgHeight
	 *            the imgHeight to set
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
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


	public NormalUserMouseListener getNormalUserMouseListener() {
		return (NormalUserMouseListener) this.normalUserMouseListener;
	}

	public AdminMouseListener getAdminMouseListener() {
		return (AdminMouseListener) this.adminMouseListener;
	}

	public StateContext getStateContext() {
		return this.stateContext;
	}

	public void incrementAdminClicked() {
		ImageComponent.adminClicked++;
	}

	public static void setIsAdmin(boolean isAdmin) {
		ImageComponent.isAdmin = isAdmin;
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
	
//	public void drawName(int x, int y)
//	{
//		this.xPos=x;
//		this.yPos=y;
//	}

	@Override
	public void paintComponent(Graphics g) {

		// if isInitilized
		// no need to paint the image again

		if (null == image) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		System.out.println(imageXpos+","+imageYpos+"hehe");
		System.out.println(xPos+","+yPos+"haha");
		
		g2.drawImage(image, imageXpos, imageYpos, image.getWidth(this), image.getHeight(this), this);
		setForeground(Color.RED);

		Map<Integer, List<Node>> allNodes = UIDataBuffer.getAllNodes();
		Set<NodeRelation> allEdges = UIDataBuffer.getAllEdges();
		if (isAdmin == true) {
			// paint all of the nodes
			
			
			if (allNodes != null && allNodes.get(1).size() != 0) {
				int x, y;
				for (int i = 0; i < allNodes.get(1).size(); i++) {
					x = allNodes.get(1).get(i).getLocation().getX();
					y = allNodes.get(1).get(i).getLocation().getY();
					g.fillOval(x - ovalOffset, y - ovalOffset, 10, 10);
				}
			}

			// paint all the edges
			
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
		
		//draw the name of location, ugly ....
//		if (allNodes != null && allNodes.get(1).size() != 0) {
//			int x, y;
//			for (int i = 0; i < allNodes.get(1).size(); i++) {
//				x = allNodes.get(1).get(i).getLocation().getX();
//				y = allNodes.get(1).get(i).getLocation().getY();
//				if(!allNodes.get(1).get(i).getName().toString().equals("Location"))
//					g.drawString(allNodes.get(1).get(i).getName(), x-ovalOffset, y-ovalOffset);
//			}
//		}
		
		//draw the name of the clicked area
		for (int i = 0; i < allNodes.get(1).size(); i++) 
		{
//			System.out.println(xPos+","+yPos);
//			System.out.println(allNodes.get(1).get(i).getLocation().getX()+","+allNodes.get(1).get(i).getLocation().getY());
			if(Math.abs(this.xPos-allNodes.get(1).get(i).getLocation().getX())<50 && Math.abs(this.yPos-allNodes.get(1).get(i).getLocation().getY())<50)
			{
				
				if(!allNodes.get(1).get(i).getName().toString().equals("Location"))
					g.drawString(allNodes.get(1).get(i).getName(), allNodes.get(1).get(i).getLocation().getX(),allNodes.get(1).get(i).getLocation().getY());
			}
		}
		
		

		g2 = null;

	}
}
