package com.wpi.cs509.teamA.ui.view;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

/**
 * Created by cuixi on 12/3/2015.
 */
public class ViewManager extends ViewControllerBase{

    public ViewManager() {

    }

    static public void updateImageComponent() {
        imageComponent.repaint();
    }

    static public void updateInputePanel() {
        inputPanel.repaint();
    }

    static public void updateView() {
        updateImageComponent();
        updateInputePanel();

    }
    static public ImageComponent getImageComponent() {
        return imageComponent;
    }
    static public InputPanel getInputPanel() {
        return inputPanel;
    }
}
