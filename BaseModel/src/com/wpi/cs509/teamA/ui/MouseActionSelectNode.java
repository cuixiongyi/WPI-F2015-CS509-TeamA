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
        stateContext.setStartNode(null);
        stateContext.setEndNode(null);
        stateContext.setMultiMapPathLists(new ArrayList<ArrayList<Node>>());
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

    	ArrayList<ArrayList<Node>> multiMapPath = this.stateContext.getMultiMapPathLists();
    	for (ArrayList<Node> path : multiMapPath) {
    		if (path.get(0).getMap().getMapId() == stateContext.getCurrentMap().getMapId()) {
    			PaintHelper.paintPath(path, g2);
    			break;
    		}
    	}
        java.util.List<Node> iconNodes = stateContext.getIconNodes();
        PaintHelper.paintNodes(stateContext.getCurrentMap().getNodes(), g2, PaintHelper.DrawStyleEnum.BasicNode);
        //PaintHelper.paintIcons(stateContext.getCurrentMap().getNodes(), g2);
        
        /*
        g2.drawOval(sourceX - ovalOffset, sourceY - ovalOffset, 10, 10);
        g2.drawOval(desX - ovalOffset, desY - ovalOffset, 10, 10);
        Font font = g.getFont().deriveFont(20.0f);
        g.setFont(font);
        g2.drawString("Source", sourceX, sourceY - ovalOffset);
        g2.drawString("Destination", desX, desY - ovalOffset);
*/

    }
}
