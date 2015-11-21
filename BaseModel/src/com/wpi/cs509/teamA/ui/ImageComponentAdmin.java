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
 * this class extend the ImageComponent class
 * It is used to paint whatever it need to paint
 * when login as admin.
 * 
 * @author CS 509-Team A
 *
 */
public class ImageComponentAdmin extends ImageComponent {


    /**
	 * Default Constructor Now
	 */
	public ImageComponentAdmin() {

    }


    private void paintEdgeAndNodes(List<Node> nodes, Graphics2D g2) {
        for (int i = 0; i < nodes.size()-1; ++i) {
            paintNode(nodes.get(i), g2);
            paintEdge(nodes.get(i), nodes.get(i+1), g2);
        }
        paintNode(nodes.get(nodes.size()-1), g2);
    }
    /*
        @Override
        public void paintComponent(Graphics g) {

            /** call the super class paint first */
            //super.paintComponent(g);
/*
            if (null == image) {
                return;
            }

            //paint all of the nodes
            GeneralMap currentMap = stateContext.getCurrentMap();
            List<Node> nodes = currentMap.getNodes();

            g2 = null;

        }
*/


};







