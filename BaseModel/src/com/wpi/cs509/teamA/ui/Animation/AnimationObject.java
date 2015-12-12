package com.wpi.cs509.teamA.ui.Animation;

import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationState;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateContext;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateStayIn;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.*;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationObject {

    private JPanel panel = null;
    private JPanel parent = null;
    private AnimationStyle style = AnimationStyle.UNDEFINED;
    private AnimationPosition position = AnimationPosition.UNDEFINED;
    private AnimationStateContext stateContext = null;

    AnimationObject(JPanel pPanel, JPanel pParent, AnimationStyle pStyle, AnimationPosition pPosition) {
        if (pStyle == AnimationStyle.UNDEFINED) {
            throw new NullPointerException();
        }
        if (pPosition == AnimationPosition.UNDEFINED) {
            throw new NullPointerException();
        }
        if (null == pPanel) {
            throw new NullPointerException();
        }
        if (null == pParent) {
            throw new NullPointerException();
        }

        panel = pPanel;
        parent = pParent;
        style = pStyle;
        position = pPosition;
        stateContext = new AnimationStateContext();
        stateContext.switchState(new AnimationStateStayIn(this));
    }



    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getParent() {
        return parent;
    }

    public void setParent(JPanel parent) {
        this.parent = parent;
    }

    public AnimationStyle getStyle() {
        return style;
    }

    public void setStyle(AnimationStyle style) {
        this.style = style;
    }

    public AnimationPosition getPosition() {
        return position;
    }

    public void setPosition(AnimationPosition position) {
        this.position = position;
    }
}