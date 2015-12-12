package com.wpi.cs509.teamA.ui.view;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.AnimationStyle;
import com.wpi.cs509.teamA.ui.Dialog.PopupPanel;
import com.wpi.cs509.teamA.ui.controller.ViewControllerBase;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cuixi on 12/3/2015.
 */
public class ViewManager extends ViewControllerBase implements Observer{

    private AnimationControl ac = new AnimationControl();

    private int mousePos = 0;
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

    @Override
    public void update(Observable obs, Object arg) {
        if ( model != obs) {
            return;
        }
        ViewManager.updateView();
    }

    public AnimationControl getAC() {
        return ac;
    }

//    public void setAC(AnimationControl ac) {
//        this.ac = ac;
//    }
}
