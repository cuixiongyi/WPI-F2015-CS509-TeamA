package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.model.DataModel;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.ui.Dialog.NodeInfoDIalog;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by cuixi on 12/13/2015.
 */
public class MouseActionEditNodeInfo extends MouseActionState {



    public MouseActionEditNodeInfo(DataModel pMM) {
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
                NodeInfoDIalog nodeInfo = new NodeInfoDIalog(node);
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());
                //JOptionPane.showMessageDialog(null, "Too close from another node.");
            } else {
                // Create a NodeEditDialog
                NodeInfoDIalog nodeInfo = new NodeInfoDIalog(node);
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());

                //nodesToPaint.add(node);
            }
        }
        return true;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {
        LinearTransform lt = model.getLinearTransform();

        PaintHelperComposite.paintNodes(model.getCurrentMap().getNodes(), g2, PaintHelperBasics.DrawStyleEnum.BasicNode, lt);
        PaintHelperComposite.paintDots(model.getCurrentMap().getNodes(), g2, lt);

    }
}
