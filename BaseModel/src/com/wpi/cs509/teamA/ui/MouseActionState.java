package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.PaintHelper;

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

    protected Coordinate coor = null;
    protected Coordinate coorTrans = null;
    protected ImageComponent imageComponent;


    public MouseActionState(StateContext pStateContext) {
        this.stateContext = pStateContext;
        this.xPos = -1;
        this.yPos = -1;
        this.imageComponent = pStateContext.getImageComponent();

    };

    abstract public void paintOnImage(Graphics2D g2);

    public boolean execute(MouseEvent e) {
        coor = new Coordinate(e.getX(), e.getY());
        coorTrans = PaintHelper.backTransferCoor(coor);
        return true;
    }

    abstract public boolean cleanup();

}
