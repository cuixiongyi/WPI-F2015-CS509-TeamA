package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.Dialog.NodeSetMenu;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionSelectNode extends MouseActionState {


    private java.util.List<Node> nodesToPaintIcon = new ArrayList<Node>();

    public MouseActionSelectNode(StateContext pSC) {
        super( pSC);

    }


    @Override
    public boolean cleanup() {

    	return true;
    }

    @Override
    public boolean execute(MouseEvent e) {
        /**
         * update coor and coorTrans
         */
        super.execute(e);

        if (e.getButton() == MouseEvent.BUTTON3) {
            Node node = getNodeFromClick(coorTrans);

                /// TODO add edit node action
                NodeSetMenu nodeSetMenu = new NodeSetMenu(inputPanel, node, stateContext);
                nodeSetMenu.show(e.getComponent(), xPos, yPos);


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

        paintRoute(g2);
        PaintHelper.paintIcons(model.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
        PaintHelper.paintStartEndNode(g2);
    }
}
