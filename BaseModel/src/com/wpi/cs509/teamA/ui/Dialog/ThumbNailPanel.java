package com.wpi.cs509.teamA.ui.Dialog;

import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeIcon;
import com.wpi.cs509.teamA.util.PaintHelper.PaintImageHelper;

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

/**
 * Created by xiongkuang on 12/13/15.
 */
public class ThumbNailPanel extends JPanel implements MouseListener {
    private JPanel contentPane;
    private JPanel innerComponent;
    private MainModel model;
    private List<Path> paths;
    private List<JLabel> icons;
    private List<JLabel> iconTexts;
    private int resizeX = 150;
    private int resizeY = 150;
    private int picInset = 160;

    private static int onePicSize = 100;

    public  ThumbNailPanel(MainModel model){
        this.model = model;
        newLayout();
        setVisible(false);

    }

    public class InnerComponent extends JComponent{
        @Override
        public void paintComponent(Graphics g) {

        }



    }

    private void newLayout() {
//        contentPane.removeAll();
        this.removeAll();
        this.icons = new ArrayList<JLabel>();
        this.iconTexts = new ArrayList<JLabel>();

        innerComponent = new JPanel();

    }

    private void setLayoutPost() {
        innerComponent.setLayout(new GridLayout(icons.size(), 1, 0, 0));
        for (int ii = 0; ii < icons.size(); ii++) {
            innerComponent.add(icons.get(ii));
        }
        JScrollPane scroll = new JScrollPane(innerComponent);
//        contentPane.add(scroll);
        this.add(scroll);
        this.setSize(onePicSize, onePicSize*icons.size());
        setVisible(true);
    }
    public void update()
    {
        newLayout();
        this.paths = this.model.getPaths();

        int picX=10;
        int picY=10;
        int textX=0;
        int textY=165;

        for(Path newPath : this.paths)
        {
            LinearTransform lt = new LinearTransform();
            lt.setScale((float)onePicSize/newPath.getMap().getImage().getHeight());
            BufferedImage bi = PaintImageHelper.paintImage(newPath, lt);
            JLabel newIcon = new JLabel(new ImageIcon(bi));
            JLabel newText = new JLabel(newPath.getMap().getMapName());
            newIcon.addMouseListener(this);
            icons.add(newIcon);
            iconTexts.add(newText);

            newIcon.setBounds(onePicSize*(icons.size()-1),0,onePicSize,onePicSize);
            newText.setBounds(textX,textY,150,30);

            picX=picX+picInset;
            textX=textX+picInset;

//            this.add(newIcon);
//            this.add(newText);
            System.out.println("Made a new icon: " + newText.getText());
        }
        setLayoutPost();
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
