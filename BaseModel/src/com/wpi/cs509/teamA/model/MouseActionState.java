package com.wpi.cs509.teamA.model;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by xiongyi on 11/23/15.
 *
 * This is the abstract class for state pattern
 */
public abstract class MouseActionState extends ViewControllerBase {

    protected StateContext stateContext;

    protected int xPos;
    protected int yPos;

    protected Coordinate coor = null;
    protected Coordinate coorTrans = null;
//    MainModel model = null;
    public MouseActionState(StateContext pSC) {
        stateContext = pSC;
        this.xPos = -1;
        this.yPos = -1;

    };

	abstract public void paintOnImage(Graphics2D g2);

    public boolean execute(MouseEvent e) {
        xPos = e.getX();
        yPos = e.getY();
        coor = new Coordinate(e.getX(), e.getY());
        coorTrans = PaintHelper.backTransferCoor(coor);
        return true;
    }



    abstract public boolean cleanup();

    protected void paintRoute(Graphics2D g2) {
        ArrayList<ArrayList<Node>> multiMapPath = model.getMultiMapPathLists();
        if (null != multiMapPath) {
            for (ArrayList<Node> path : multiMapPath) {
                if (null != path) {
                    if (path.get(0).getMap().getMapId() == model.getCurrentMapID()) {
                        PaintHelper.paintPath(path, g2);
                        break;
                    }
                }
            }
        }

    }

    public Node getNodeFromClick(Coordinate coor) {
        return Database.getNodeFromCoordinate(coor, model.getCurrentMap().getMapId());
    }
    public Node getNodeFromClick(MouseEvent e) {
        return Database.getNodeFromCoordinate(PaintHelper.backTransferCoor(new Coordinate(e.getX(), e.getY()))
                , model.getCurrentMap().getMapId());
    }
}
