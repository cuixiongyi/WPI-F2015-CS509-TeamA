package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseListener;
import com.wpi.cs509.teamA.ui.controller.Listener.ImageMouseWheelListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cuixi on 12/4/2015.
 */
public class ViewControllerImpl extends ViewControllerBase{

    private ImageMouseListener mouseListener = null;
    private ImageMouseWheelListener wheelListener = null;


    public ViewControllerImpl () {
        mouseListener = new ImageMouseListener(imageComponent, model);
        wheelListener = new ImageMouseWheelListener(imageComponent, model);
        addSearchButtonAction();
    }

    private void addSearchButtonAction() {
        inputPanel.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
