package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

/**
 * Created by cuixi on 12/3/2015.
 */
public class ViewControllerBase {

    static protected ImageComponent imageComponent = null;
    static protected InputPanel inputPanel = null;
    static protected MainModel model = null;

    static public void init(ImageComponent pIC, InputPanel pIP, MainModel pMM) {
        if (null == pIC)
            throw new NullPointerException("Empty ImageComponent");
        if (null == pIP)
            throw new NullPointerException("Empty ImageComponent");
        if (null == pMM)
            throw new NullPointerException("Empty MainModel");

        imageComponent = pIC;
        inputPanel = pIP;
        model = pMM;
    }
    public ViewControllerBase() {

    }

}
