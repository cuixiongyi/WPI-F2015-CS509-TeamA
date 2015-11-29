package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.util.PaintHelper;
import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionAdminUser extends MouseActionState {


    public MouseActionAdminUser(StateContext pStateContext) {
        super(pStateContext);

        imageComponent.repaint();
    }

    @Override
    public boolean cleanup() {

        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {
        /// update coor and coorTrans
        super.execute(e);

        xPos = e.getX();
        yPos = e.getY();
        if (e.getButton() == MouseEvent.BUTTON3 )
        {
            NodeManageMenu nodeManageMenu = new NodeManageMenu(stateContext.getImageComponent(), xPos, yPos, stateContext);
            nodeManageMenu.show(e.getComponent(), xPos, yPos);
            return false;
        }

        return false;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {
        PaintHelper.paintNodes(stateContext.getCurrentMap().getNodes(),
                g2, PaintHelper.DrawStyleEnum.NewNode);
        PaintHelper.paintEdges(stateContext.getCurrentMap().getEdges(),
                g2, PaintHelper.DrawStyleEnum.BasicEdge);
    }
}
