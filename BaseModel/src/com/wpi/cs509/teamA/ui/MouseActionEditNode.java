package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditNode extends MouseActionState {


    public MouseActionEditNode(StateContext pStateContext) {
        super(pStateContext);

    }

    @Override
    public boolean cleanup() {

        return false;
    }

    @Override
    public boolean execute(MouseEvent e, int x, int y) {
        return false;
    }
}
