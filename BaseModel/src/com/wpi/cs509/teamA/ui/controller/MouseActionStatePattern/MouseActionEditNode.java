package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.Dialog.NodeInformationDialog;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditNode extends MouseActionState {
//	private JToggleButton btnMngNode; 	

    private List<Node> nodesToPaint;


    public MouseActionEditNode(StateContext pSC) {
        super( pSC);

        nodesToPaint = new ArrayList<>();

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
        super.execute(e);

	    if (e.getButton() == MouseEvent.BUTTON1) {

            Node node = getNodeFromClick(coorTrans);

            if (null != node) {
                /// TODO add edit node action
                NodeInformationDialog nodeInfo = new NodeInformationDialog(imageComponent, model, node);
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());
                //JOptionPane.showMessageDialog(null, "Too close from another node.");
            } else {
                // Create a NodeInformationDialog
                NodeInformationDialog nodeInfo = new NodeInformationDialog(imageComponent, model, coorTrans.getX(), coorTrans.getY());
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
        PaintHelper.paintNodes(nodesToPaint, g2, PaintHelper.DrawStyleEnum.NewNode);
    }
}
