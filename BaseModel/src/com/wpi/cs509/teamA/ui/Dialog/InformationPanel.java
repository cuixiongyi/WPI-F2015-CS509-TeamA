package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.ImageHelper;
import com.wpi.cs509.teamA.util.NodeIcon;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by xiongkuang on 12/13/15.
 */
public class InformationPanel extends JPanel implements ActionListener {

    private JLabel picLabel;
    private JTextArea textArea;
    private JLabel textLabel;
    private Node node;
    private BufferedImage informationPic;



    public InformationPanel()
    {
        textLabel=new JLabel();
//        textArea=new JTextArea();
        picLabel=new JLabel();
    }

    public void setNode(Node node) {
        refresh();
        this.node = node;
        updateDisplay(node);
        this.repaint();
    }

    public void updateDisplay(Node pnode){

        String pre = "<html><body style='width: 150px;'>";
        this.setSize(306,126);
        this.setLayout(null);

        node=pnode;

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        textLabel.setText(pre+Database.getNodeInformation(node.getId()).PrintNodeInfo());
        textLabel.setBounds(123,3,180,120);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.add(textLabel);

        try{
            informationPic= ImageHelper.resizeImage(node.getMap().getImage(),120,120) ;
            picLabel.setIcon(new ImageIcon(informationPic));
            picLabel.setBounds(3,3,120,120);
            this.add(picLabel);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public void refresh(){
        textLabel.setText(" ");
        picLabel.setIcon(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


}
