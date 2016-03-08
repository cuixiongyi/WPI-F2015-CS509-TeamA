package com.wpi.cs509.teamA.ui.dialog;

import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.controller.ViewManager;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.renderer.InputPanelRenderer;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeIcon;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

/**
 * Created by xiongkuang on 12/13/15.
 */
public class ThumbNailPanel extends JPanel implements MouseListener {
    private JPanel contentPane;
    private JPanel innerComponent;
    private MainModel model;
    private List<Path> paths;
    private List<JLabel> icons;
    private int resizeX = 150;
    private int resizeY = 150;
    private int picInset = 160;

    private static int onePicSize = 100;

    public  ThumbNailPanel(MainModel model){
        this.model = model;
//        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        newLayout();
        setVisible(false);

    }

    public class InnerComponent extends JComponent{
        @Override
        public void paintComponent(Graphics g) {

        }



    }

    private void newLayout() {
        this.removeAll();
        this.icons = new ArrayList<JLabel>();

        innerComponent = new JPanel();

    }

    private void setLayoutPost() {
        innerComponent.setLayout(new GridLayout(icons.size(), 1, 0, 0));
        for (int ii = 0; ii < icons.size(); ii++) {
            innerComponent.add(icons.get(ii));
        }

//        JScrollPane scroll = new JScrollPane(innerComponent);
        this.add(innerComponent);
        innerComponent.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setSize(onePicSize+4,(onePicSize+6)*icons.size());
        setVisible(true);

    }

    public boolean update()
    {
        if (null == this.model.getPaths()) {
            setVisible(false);
            return false;
        }
        if (this.paths == this.model.getPaths()) {
            return false;
        }
        newLayout();

        this.paths = this.model.getPaths();


        for(Path newPath : this.paths)
        {
            LinearTransform lt = new LinearTransform();
            lt.setScale((float)onePicSize/newPath.getMap().getImage().getHeight());
            BufferedImage bi = PaintImageHelper.paintImage(newPath, lt);
            JLabel newIcon = new JLabel(new ImageIcon(bi));
            newIcon.addMouseListener(this);
            icons.add(newIcon);

            newIcon.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));


        }
        setLayoutPost();
        setCurrentMap(0);
        return true;
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
