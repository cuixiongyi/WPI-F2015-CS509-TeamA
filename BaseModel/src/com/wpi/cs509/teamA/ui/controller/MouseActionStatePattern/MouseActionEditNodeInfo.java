package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.ui.Dialog.NodeEditDialog;
import com.wpi.cs509.teamA.ui.Dialog.NodeInfoDIalog;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by cuixi on 12/13/2015.
 */
public class MouseActionEditNodeInfo extends MouseActionState {



    public MouseActionEditNodeInfo(MainModel pMM) {
        super( pMM);

        model.cleanUpRoute();

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
        getMouseTransCoor(e);
        Node node = getNodeFromClick(e);

        if (e.getButton() == MouseEvent.BUTTON1) {


            if (null != node) {
                /// TODO add edit node action
                NodeInfoDIalog nodeInfo = new NodeInfoDIalog();
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());
                //JOptionPane.showMessageDialog(null, "Too close from another node.");
            } else {
                // Create a NodeEditDialog
                NodeInfoDIalog nodeInfo = new NodeInfoDIalog();
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());

                //nodesToPaint.add(node);
            }
        }
        return true;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {

        PaintHelper.paintNodes(model.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
    }
}
