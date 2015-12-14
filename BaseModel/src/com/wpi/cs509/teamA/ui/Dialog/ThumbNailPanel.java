package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.util.NodeIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * Created by xiongkuang on 12/13/15.
 */
public class ThumbNailPanel extends JScrollPane implements MouseListener {
    private MainModel model;
    private List<Path> paths;
    private List<JLabel> icons;
    private List<JLabel> iconTexts;
    private int resizeX = 200;
    private int resizeY = 200;
    private int picInset = 300;

    public  ThumbNailPanel(MainModel model){
        this.setSize(800,220);
        this.setLocation(110,110);
        this.model = model;
        this.icons = new ArrayList<JLabel>();
        this.iconTexts = new ArrayList<JLabel>();
        this.setLayout(null);
    }

    public void update()
    {
        this.paths = this.model.getPaths();
        this.removeAll();

        int picX=0;
        int picY=0;
        int textX=0;
        int textY=205;

        for(Path newPath : this.paths)
        {
            JLabel newIcon = new JLabel(new ImageIcon(NodeIcon.resize(newPath.getMap().getImage(),resizeX,resizeY)));
            JLabel newText = new JLabel(newPath.getMap().getMapName());
            newIcon.addMouseListener(this);
            icons.add(newIcon);
            iconTexts.add(newText);

            newIcon.setBounds(picX,picY,resizeX,resizeY);
            newText.setBounds(textX,textY,200,30);

            picX=picX+picInset;
            textX=textX+picInset;

            this.add(newIcon);
            this.add(newText);
            System.out.println("Made a new icon: " + newText.getText());
        }
        setCurrentMap(0);
    }

    void setCurrentMap(int index)
    {
        this.model.setCurrentPath(index);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

        int idx = icons.indexOf(e.getSource());
        setCurrentMap(idx);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }

}
