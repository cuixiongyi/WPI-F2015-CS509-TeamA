package com.wpi.cs509.teamA.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by xiongyi on 11/22/15.
 */
public class MouseListenerwithContext implements MouseListener {

    /**
     * The x position that the user clicked
     */
    protected int xPos;

    /**
     * The y position that the user clicked
     */
    protected int yPos;
    /**
     * The image component that the listener will be added to
     */
    protected ImageComponent imagePanel;

    /**
     * Component for entering edge
     */

    protected StateContext stateContext;

    /**
     * Default constructor
     */
    public MouseListenerwithContext() {

    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }



    public StateContext getStateContext() {
        return stateContext;
    }

    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }


}
