package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;

/**
 * Created by xiongyi on 11/23/15.
 *
 * This is the abstract class for state pattern
 */
public abstract class MouseActionState {


    protected StateContext stateContext;

    protected int xPos;
    protected int yPos;

    protected ImageComponent imageComponent;
            ;
    public MouseActionState(StateContext pStateContext) {
        this.stateContext = pStateContext;
        this.xPos = -1;
        this.yPos = -1;
        this.imageComponent = pStateContext.getImageComponent();
    };

    abstract public boolean execute(MouseEvent e, int x, int y) ;

    abstract public boolean cleanup();

}
