package com.wpi.cs509.teamA.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

public class MarioListRenderer extends DefaultListCellRenderer {

    Font font = new Font("helvitica", Font.BOLD, 24);
    ImageIcon icon;

    
    public MarioListRenderer(ImageIcon icon){
    	super();
    	this.icon = icon;
    	
    }
    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        
        if (isSelected) {
        	label.setBackground(new Color(99,184,255));
        }
        if(index==(list.getModel().getSize()-1))
        {
        	label.setIcon(NodeIcon.getEndIconSmall());
        }else if(index==0){
        	label.setIcon(NodeIcon.getStartIconSmall());
        }else{
//        	ImageIcon image=new ImageIcon(NodeIcon.getNumIcon(index));
        	ImageIcon image=new ImageIcon(NodeIcon.getNextIcon());
            label.setIcon(image);
        }
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
    
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus, ImageIcon icon) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        
        if (isSelected) {
        	label.setBackground(new Color(99,184,255));
        }
        label.setIcon(icon);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}