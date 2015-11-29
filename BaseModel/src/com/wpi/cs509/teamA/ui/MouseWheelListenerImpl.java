package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.GeneralMap;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by cuixi on 11/27/2015.
 */
public class MouseWheelListenerImpl implements MouseWheelListener{


    StateContext stateContext;
    static double scaleIncConst = 0.2;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        String message;
        float scaleInc = 0;
        int notches = e.getWheelRotation();
        if (notches < 0) {
            scaleInc += scaleIncConst;

        } else {
            scaleInc -= scaleIncConst;
        }


        changeDisplayScale(scaleInc);
    }

    private void changeDisplayScale(double scaleInc) {
        GeneralMap map = stateContext.getCurrentMap();
        ImageComponent IC = stateContext.getImageComponent();
        map.setDisplayScale(map.getDisplayScale()+(float)scaleInc);
        int x = scaleImageOffset(IC.getImageXpos(), scaleInc);
        int y = scaleImageOffset(IC.getImageYpos(), scaleInc);

        IC.setImageXpos(x);
        IC.setImageXpos(y);
        IC.repaint();


    }

    private int scaleImageOffset(int x, double scaleInc) {
        double ret = 0;
        if (x > 0) {
            ret = (double)x / (1.0d+scaleInc);
        }
        else {
            ret = (double)x / (1.0d-scaleInc);
        }

        return (int)Math.round(ret);
    }
    public StateContext getStateContext() {
        return stateContext;
    }

    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }
}
