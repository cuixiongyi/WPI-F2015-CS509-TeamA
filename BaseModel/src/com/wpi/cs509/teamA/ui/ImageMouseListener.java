package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by cuixi on 11/24/2015.
 */
public class ImageMouseListener implements MouseListener {



    private StateContext stateContext;

    public ImageMouseListener() {
        this.stateContext = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        stateContext.execute(e);
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }


}
