package com.wpi.cs509.teamA.ui.initializer.Animation;

import javax.swing.*;

import com.wpi.cs509.teamA.ui.initializer.view.ViewManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationControl {



    static private List<AnimationObject> objects = null;
    private static Timer timer = new Timer(5,new MyActionListener());

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

    public AnimationObject create(JComponent pPanel, JComponent pParent, AnimationStyle pStyle, AnimationPosition pPosition, int pRange) {
        AnimationObject ret = checkObjectExist(pPanel);
        if (null != ret)
            return ret;

        ret = new AnimationObject(pPanel, pParent, pStyle, pPosition, pRange);
        objects.add(ret);
        return ret;

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
