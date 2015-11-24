package com.wpi.cs509.teamA.ui;

import javafx.scene.input.MouseButton;

import java.awt.event.MouseEvent;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionAdminUser extends MouseActionState {


    public MouseActionAdminUser(StateContext pStateContext) {
        super(pStateContext);

    }

    @Override
    public boolean cleanup() {

        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {
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
}
