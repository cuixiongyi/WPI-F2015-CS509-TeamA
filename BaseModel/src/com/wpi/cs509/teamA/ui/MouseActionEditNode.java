package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditNode extends MouseActionState {
//	private JToggleButton btnMngNode; 	

    private List<Node> nodesToPaint;

	public MouseActionEditNode(StateContext pStateContext) {
        super(pStateContext);
        nodesToPaint = new ArrayList<>();

        stateContext.setAddedNewNode(false);
        stateContext.setNewNode(null);

        imageComponent.repaint();
    }

	@Override
	public boolean cleanup() {

        stateContext.setAddedNewNode(false);
        stateContext.setNewNode(null);
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
                JOptionPane.showMessageDialog(null, "Too close from another node.");
            } else {
                // Create a NodeInformationDialog
                NodeInformationDialog nodeInfo = new NodeInformationDialog(stateContext, coorTrans.getX(), coorTrans.getY());
                nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                nodeInfo.setVisible(nodeInfo.isFocusable());

                //nodesToPaint.add(node);
            }
        }
        return true;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {
        // TODO this is a hack, need to paint newly added node here
        if (stateContext.isAddedNewNode() && null != stateContext.getNewNode()) {
            nodesToPaint.add(stateContext.getNewNode());
            stateContext.setAddedNewNode(false);
            stateContext.setNewNode(null);
        }
        PaintHelper.paintNodes(stateContext.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
        PaintHelper.paintNodes(nodesToPaint, g2, PaintHelper.DrawStyleEnum.NewNode);

    }
}
