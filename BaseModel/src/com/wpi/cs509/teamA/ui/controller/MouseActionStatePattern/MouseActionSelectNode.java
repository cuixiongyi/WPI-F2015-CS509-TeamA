package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.Dialog.NodeSetMenu;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionSelectNode extends MouseActionState {


    private java.util.List<Node> nodesToPaintIcon = new ArrayList<Node>();

    public MouseActionSelectNode(MainModel pMM) {
        super( pMM);

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
        getMouseTransCoor(e);
        Node node = getNodeFromClick(e);

        if (e.getButton() == MouseEvent.BUTTON3) {

                /// TODO add edit node action
                NodeSetMenu nodeSetMenu = new NodeSetMenu(ViewManager.getInputPanel(), model, node);
                nodeSetMenu.show(e.getComponent(), xPos, yPos);


        }

        if (e.getButton() == MouseEvent.BUTTON1) {

            if (null != node) {
               // nodesToPaintIcon.clear();
               // nodesToPaintIcon.add(node);
            }
        }
            return false;
    }


    @Override
    public void paintOnImage(Graphics2D g2) {

        PaintHelper.paintRoute(g2);

        PaintHelper.paintIcons(model.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
        PaintHelper.paintStartEndNode(g2);
    }
}
