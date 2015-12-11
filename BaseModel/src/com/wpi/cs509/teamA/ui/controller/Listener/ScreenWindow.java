package com.wpi.cs509.teamA.ui.controller.Listener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ScreenWindow extends JFrame {

 private static final long serialVersionUID = -3758062802950480258L;
 
 public Dimension screenDims = null; 
 
 private boolean isDrag = false;

 private int x = 0;

 private int y = 0;

 private int xEnd = 0;

 private int yEnd = 0;
 
 public ImageIcon screenImageIcon = null;
 
 public JLabel label = null;
 
 public JFrame copy_mainwindow; 

 public ScreenWindow(JFrame mainwindow) throws AWTException, InterruptedException {
     screenDims = Toolkit.getDefaultToolkit().getScreenSize();
     screenImageIcon = new ImageIcon(ScreenImage.getScreenImage(0, 0, screenDims.width, screenDims.height));
     label = new JLabel(screenImageIcon);
     label.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
     copy_mainwindow = mainwindow;
     

     KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
     manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {

       @Override
       public boolean postProcessKeyEvent(KeyEvent e) {
          if (KeyEvent.VK_ESCAPE == e.getKeyCode()) { 
              dispose();
              copy_mainwindow.setVisible(true);
          }
          return false;
       }
       
     });
          
    
            try {

               JFileChooser filechooser = new JFileChooser();
               // filechooser.setCurrentDirectory(new File("C:\\Users\\miklejhon\\Desktop"));
   
               int result = filechooser.showSaveDialog(null);
          
               if(result==JFileChooser.APPROVE_OPTION){
            	  File selectedfile = filechooser.getSelectedFile();
            	  File file = new File(selectedfile.getPath());
                  FileOutputStream fos = new FileOutputStream(file);
                  BufferedImage bufferedimage = new BufferedImage(xEnd - x,yEnd - y,BufferedImage.TYPE_INT_BGR);
                  Image image = ScreenImage.getScreenImage(x, y, xEnd - x, yEnd - y);
                  Graphics g = bufferedimage.getGraphics();
                  g.drawImage(image,0, 0,xEnd - x,yEnd - y,null);
                  g.setColor(Color.white);
                  g.drawRect(0, 0, xEnd - x, yEnd - y);
                  ImageIO.write(bufferedimage, "JPEG",fos);
                  fos.flush();
                  fos.close();
                  
                  dispose();
                  copy_mainwindow.setVisible(true);
               }else{
            	   label.setIcon(screenImageIcon);
               }
            } catch (Exception ex) {
               ex.printStackTrace();
               JOptionPane.showConfirmDialog(null, "出现意外错误！", "系统提示", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
          
       
  });
     

  label.addMouseMotionListener(new MouseMotionAdapter() {
     public void mouseDragged(MouseEvent e) {
        if(!isDrag){
            isDrag = true;
        }
        int endx = e.getX();
   	    int endy = e.getY();
   	    BufferedImage bufferedimage = new BufferedImage(screenDims.width, screenDims.height,BufferedImage.TYPE_INT_BGR);
        Graphics g = bufferedimage.getGraphics();
        g.drawImage(screenImageIcon.getImage(),0, 0,screenDims.width,screenDims.height,null);
   	    g.setColor(Color.red);
   	    g.drawRect(x, y, endx-x, endy-y);
   	    label.setIcon(new ImageIcon(bufferedimage));
     }
  });
  
  this.setUndecorated(true);
  this.getContentPane().add(label);
  this.setSize(screenDims.width, screenDims.height);
  this.setVisible(true);
  this.setExtendedState(JFrame.MAXIMIZED_BOTH);
 }
}

class ScreenImage {

  public static Image getScreenImage(int x, int y, int w, int h) throws AWTException, InterruptedException {
     Robot robot = new Robot();
     Image screen = robot.createScreenCapture(new Rectangle(x, y, w, h)).getScaledInstance(w, h, Image.SCALE_SMOOTH);
     MediaTracker tracker = new MediaTracker(new Label());
     tracker.addImage(screen, 1);
     tracker.waitForID(0);
     return screen;
  }
}
