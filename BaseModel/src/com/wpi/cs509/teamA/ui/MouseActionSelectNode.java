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


    public MouseActionSelectNode(StateContext pStateContext) {
        super(pStateContext);

    }

    @Override
    public boolean cleanup() {
        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {
        return false;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {

        java.util.List<Node> pathNode = this.stateContext.getPath();
        PaintHelper.paintPath(pathNode, g2);
        java.util.List<Node> iconNodes = stateContext.getIconNodes();
        //PaintHelper.paintIcons(iconNodes, g2);


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
