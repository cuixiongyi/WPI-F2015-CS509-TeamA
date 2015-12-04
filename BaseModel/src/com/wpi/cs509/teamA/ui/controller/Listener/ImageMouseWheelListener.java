package com.wpi.cs509.teamA.ui.controller.Listener;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.ui.view.ImageComponent;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Created by cuixi on 11/27/2015.
 */
public class ImageMouseWheelListener implements MouseWheelListener{


    static double scaleIncConst = 0.2;

    private ImageComponent imageComponent = null;
    private MainModel model = null;

    public ImageMouseWheelListener(ImageComponent pImageComponent, MainModel pModel) {
        this.imageComponent = pImageComponent;
        model = pModel;
        if (null == pModel) {
            throw new NullPointerException();
        }
        model = pModel;

        imageComponent.addMouseWheelListener(this);

    }
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
        GeneralMap map = model.getCurrentMap();
        map.setDisplayScale(map.getDisplayScale()+(float)scaleInc);
        int x = scaleImageOffset(imageComponent.getImageXpos(), scaleInc);
        int y = scaleImageOffset(imageComponent.getImageYpos(), scaleInc);

        imageComponent.setImageXpos(x);
        imageComponent.setImageXpos(y);
        imageComponent.repaint();


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

}
