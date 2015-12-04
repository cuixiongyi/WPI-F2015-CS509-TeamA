package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseWheelListener;

/**
 * Created by cuixi on 12/4/2015.
 */
public class ViewControllerImpl extends ViewControllerBase{

    private ImageMouseListener mouseListener = null;
    private ImageMouseWheelListener wheelListener = null;

    public ViewControllerImpl () {
        mouseListener = new ImageMouseListener(imageComponent, model);
        wheelListener = new ImageMouseWheelListener(imageComponent, model);


    }
}
