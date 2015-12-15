package com.wpi.cs509.teamA.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by cuixi on 12/15/2015.
 */
public class ImageHelper {
    public static BufferedImage readImage(String name) {
        URL imageURL = ImageHelper.class.getResource("/com/wpi/cs509/teamA/resource/"+name);
        System.out.println(imageURL);
        Image img = new ImageIcon((imageURL)).getImage() ;
        return toBufferedImage(img);
    }

    public static BufferedImage resizeImage(BufferedImage img, double scale) {
        int width = (int)(img.getWidth()*scale);
        int height = (int)(img.getHeight()*scale);

        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static BufferedImage resizeImage(BufferedImage img, int w, int h) {
        double scale = ((double)w / (double)img.getWidth());
        return resizeImage(img, scale);
    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
