//package com.wpi.cs509.teamA.util;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Font;
//
//import javax.swing.BorderFactory;
//import javax.swing.DefaultListCellRenderer;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.border.EtchedBorder;
//
//public class SameLabelListCellRenderer extends DefaultListCellRenderer {
//	
//	
//    private Font font = new Font("simsun", Font.BOLD, 20);
//    private Icon icon;
//
//    public SameLabelListCellRenderer(Icon icon) {
//        this.icon = icon;     
//    }
//
//    @Override
//    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus)
//    {
//    	   JLabel label = (JLabel) super.getListCellRendererComponent(
//                   list, value, index, isSelected, cellHasFocus);
//           if (isSelected) {
//           	label.setBackground(new Color(99,184,255));
//           }
//          
//           label.setHorizontalTextPosition(JLabel.RIGHT);
//           label.setFont(font);
//           label.setIcon(icon);
//           return label;
//    }
//
//}
