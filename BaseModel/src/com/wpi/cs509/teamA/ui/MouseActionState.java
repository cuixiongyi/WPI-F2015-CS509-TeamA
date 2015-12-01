package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    protected ImageComponent imageComponent = null;
    protected Node nodeLastClicked = null;
    protected InputPanel inputPanel = null;
    public MouseActionState(StateContext pStateContext) {
        this.stateContext = pStateContext;
        this.xPos = -1;
        this.yPos = -1;
        this.imageComponent = pStateContext.getImageComponent();
        this.inputPanel = pStateContext.getInputPanel();

    };

	abstract public void paintOnImage(Graphics2D g2);

    public boolean execute(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
        coor = new Coordinate(e.getX(), e.getY());
        coorTrans = PaintHelper.backTransferCoor(coor);
        return true;
    }

    public Node getNodeFromClick(Coordinate coor) {
        return Database.getNodeFromCoordinate(coor, stateContext.getCurrentMap().getMapId());
    }

    abstract public boolean cleanup();

    protected void paintRoute(Graphics2D g2) {
        ArrayList<ArrayList<Node>> multiMapPath = stateContext.getMultiMapPathLists();
        if (null != multiMapPath) {
            for (ArrayList<Node> path : multiMapPath) {
                if (null != path) {
                    if (path.get(0).getMap().getMapId() == stateContext.getCurrentMap().getMapId()) {
                        PaintHelper.paintPath(path, g2);
                        break;
                    }
                }
            }
        }

    }
}
