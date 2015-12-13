package com.wpi.cs509.teamA.ui.view;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingDown;
import com.wpi.cs509.teamA.ui.Animation.AnimationStyle;
import com.wpi.cs509.teamA.ui.Dialog.InformationPanel;
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


    private int mousePos = 0;
    private static InformationPanel nodeInformation =new InformationPanel();
    public ViewManager() {


    }

    static public void infoPanelSlideDown() {
        AnimationObject AO = ViewManager.getAC().checkObjectExist(ViewManager.getNodeInformation());
        if (null == AO) {
            return ;
        }
        AO.switchState(new AnimationStateSlidingDown(AO));

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

    static public AnimationControl getAC() {
        return ac;
    }

//    public void setAC(AnimationControl ac) {
//        this.ac = ac;
//    }


    static public  InformationPanel getNodeInformation() {
        return nodeInformation;
    }

    public static void setInformationNode(Node pnode) {
        nodeInformation.setNode(pnode);
    }
}

