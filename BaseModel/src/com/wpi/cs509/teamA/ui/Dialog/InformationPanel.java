package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.NodeIcon;
import com.wpi.cs509.teamA.util.PaintHelper;

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
        picLabel=new JLabel();
    }

    public void setNode(Node node) {
        refresh();
        this.node = node;
        updateDisplay(node);
    }

    public void updateDisplay(Node pnode){

        String pre = "<html><body style='width: 150px;'>";
        this.setSize(256,106);
        this.setLayout(null);

        node=pnode;

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        textLabel.setText(pre+node.getName());
        textLabel.setBounds(103,3,150,100);
        textLabel.setFont(new Font("helvitica", Font.BOLD, 18));
        this.add(textLabel);

        try{
            informationPic= NodeIcon.resize(ImageIO.read(new File(node.getMap().getMapImgPath())),100,100) ;
            picLabel.setIcon(new ImageIcon(informationPic));
            picLabel.setBounds(3,3,100,100);
            this.add(picLabel);

        } catch (IOException e) {
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
