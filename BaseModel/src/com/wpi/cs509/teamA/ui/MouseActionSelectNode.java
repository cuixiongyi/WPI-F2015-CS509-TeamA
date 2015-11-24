package com.wpi.cs509.teamA.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

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

    }
}
