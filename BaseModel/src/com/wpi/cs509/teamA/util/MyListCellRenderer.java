package com.wpi.cs509.teamA.util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class MyListCellRenderer extends DefaultListCellRenderer {
	
	private final JPanel p = new JPanel(new BorderLayout());
	private final JPanel IconPanel = new JPanel(new BorderLayout());
	private final JLabel l = new JLabel("icon"); //<-- this will be an icon instead of a text
	private final JLabel lt = new JLabel();
    private final String pre = "<html><body style='width: 180px;'>";
    private final Font font = new Font("helvitica", Font.BOLD, 20);

    public MyListCellRenderer() {
        //icon
        IconPanel.add(l, BorderLayout.CENTER);
        l.setFont(font);
//        l.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        p.add(IconPanel, BorderLayout.WEST);
        
      //text
        lt.setFont(font);
        lt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        p.add(lt, BorderLayout.CENTER);
        
        
    }

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus)
    {
        final String text = (String) value;
        String idx = Integer.toString(index+1);
        lt.setText(pre + text);
        l.setText(idx);
        

        return p;
    }

}
