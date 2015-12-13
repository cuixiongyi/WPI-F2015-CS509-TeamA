package com.wpi.cs509.teamA.util;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class MarioListRenderer extends DefaultListCellRenderer {

    Font font = new Font("helvitica", Font.BOLD, 24);

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
//        label.setIcon(imageMap.get((String) value));
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}