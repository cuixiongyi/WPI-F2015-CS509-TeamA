package com.wpi.cs509.teamA.ui.Animation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationControl {



    static private List<AnimationObject> objects = null;
    private static Timer timer = new Timer(3,new MyActionListener());

    public static class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            for (AnimationObject obj : objects) {
                obj.execute();
            }
        }
    }


    public AnimationControl() {
        objects = new ArrayList<AnimationObject>();
        timer.start();

    }

    public void create(JComponent pPanel, JComponent pParent, AnimationStyle pStyle, AnimationPosition pPosition, int pRange) {
        if (null != checkObjectExist(pPanel))
            return;

        AnimationObject newAO = new AnimationObject(pPanel, pParent, pStyle, pPosition, pRange);
        objects.add(newAO);

    }

    public AnimationObject checkObjectExist(JComponent ppanel) {
        for (AnimationObject obj : objects) {
            if (ppanel == obj.getPanel()) {
                return obj;
            }
        }
        return null;
    }
}
