package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

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


    public MouseActionState(StateContext pStateContext) {
        this.stateContext = pStateContext;
        this.xPos = -1;
        this.yPos = -1;
        this.imageComponent = pStateContext.getImageComponent();

    };

    abstract public void paintOnImage(Graphics2D g2);

    abstract public boolean execute(MouseEvent e) ;

    abstract public boolean cleanup();

}
