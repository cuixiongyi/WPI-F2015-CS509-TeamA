package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionSelectNode extends MouseActionState {


    private java.util.List<Node> nodesToPaintIcon = new ArrayList<Node>();

    public MouseActionSelectNode(StateContext pStateContext) {
        super(pStateContext);

        if (null != imageComponent)
            imageComponent.repaint();
        if (null != inputPanel) {
            inputPanel.repaint();
            }
    }


    @Override
    public boolean cleanup() {
        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {
        /**
         * update coor and coorTrans
         */
        super.execute(e);

        if (e.getButton() == MouseEvent.BUTTON3) {
            Node node = getNodeFromClick(coorTrans);
            if (null != node) {
                /// TODO add edit node action
                NodeSetMenu nodeSetMenu = new NodeSetMenu(inputPanel, node, stateContext);
                nodeSetMenu.show(e.getComponent(), xPos, yPos);
            }

        }

        if (e.getButton() == MouseEvent.BUTTON1) {

            Node node = getNodeFromClick(coorTrans);
            if (null != node) {
               // nodesToPaintIcon.clear();
               // nodesToPaintIcon.add(node);
            }
        }
            return false;
    }


    @Override
    public void paintOnImage(Graphics2D g2) {

        PaintHelper.paintIcons(stateContext.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
        paintRoute(g2);

    }
}
