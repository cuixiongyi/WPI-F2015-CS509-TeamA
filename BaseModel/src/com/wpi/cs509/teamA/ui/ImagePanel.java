package com.wpi.cs509.teamA.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ImagePanel extends JComponent implements MouseListener {

	private Image image;
	private int imgWidth;
	private int imgHeight;

	private int xPos;
	private int yPos;
	
	// this is for set event handlers for the button
	private InputPanel inputPanel;
	
	private static int num = 1;

	// admin will get a different repaint method
	private boolean isAdmin;

	public ImagePanel() {

	}
	
	public ImagePanel(JPanel inputPanel){
		((InputPanel) inputPanel).getBtnSearch().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("the linstener has been triggered!");
				
				// check what kind of two places are
				// proxy design pattern?
				// do search work here?
				// let the controller decide which algorithm will be called?
				// AlogController controller = new
				// AlogController(startPoint.getText().trim(),
				// endPoint.getText().trim());
				// List<Node> route = new ArrayList<Node>();
				// route = controller.getRoute();
				// how to make feedback to the image? more discussion..

				// give all the information to the repaint method

				repaint();

			}

		});
		
	}

	public void setImagePath(String imgPath) {
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		// this operation is only for admin
		xPos = e.getX();
		yPos = e.getY();

		System.out.println(xPos);
		System.out.println(yPos);

		// make a pop up window here
		// save the point that we have clicked to database
		// it doesnt matter if we repaint the window
		
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintComponent(Graphics g) {

		// if isInitilized
		// no need to paint the image again

		System.out.println("paintComponent() is called!");

		if (null == image) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(image, 0, 0, image.getWidth(this), image.getHeight(this), this);

		if(!(xPos == 0 && yPos == 0)){
			g2.setPaint(Color.WHITE);
			g2.drawString("("+xPos+","+yPos+")",xPos,yPos);
			
			// whenever call the repaint method
			// we draw two demon lines here
			if(num%2 == 0){
				g2.draw(new Line2D.Double(10, 10, 600, 10));
				g2.draw(new Line2D.Double(10, 80, 600, 80));
				num++;
			}else{
				g2.draw(new Line2D.Double(10, 600, 600, 600));
				g2.draw(new Line2D.Double(10, 700, 600, 700));
				num++;
			}
			
		}
		
		
		
		// since it will be repaint again
		// so the string will not be save on the image
		// we should figure out an idea to save all the point we have clicked
		// but I think maybe we can first save it
		// then load all the points back to draw them on the image?
		
		g2 = null;

	}
	
	private void addButtonListener(){
		
	}
}
