package com.wpi.cs509.teamA.ui.Animation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationControl {



    private List<AnimationObject> objects = null;


    public AnimationControl() {
        objects = new ArrayList<AnimationObject>();
    }

    public void create(JPanel pPanel, JPanel pParent, AnimationStyle pStyle, AnimationPosition pPosition) {
        if (null != checkObjectExist(pPanel))
            return;

        AnimationObject newAO = new AnimationObject(pPanel, pParent, pStyle, pPosition);
        objects.add(newAO);

    }
    private AnimationObject checkObjectExist(JPanel ppanel) {
        for (AnimationObject obj : objects) {
            if (ppanel == obj.getPanel()) {
                return obj;
            }
        }
        return null;
    }
}
