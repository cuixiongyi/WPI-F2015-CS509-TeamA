package com.wpi.cs509.teamA.ui.Animation;

import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationState;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateContext;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateStayIn;

import javax.swing.*;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationObject {

    private JComponent panel = null;
    private JComponent parent = null;
    private AnimationStyle style = AnimationStyle.UNDEFINED;
    private AnimationPosition position = AnimationPosition.UNDEFINED;
    private AnimationStateContext stateContext = null;
    private int range = 0;

    AnimationObject(JComponent pPanel, JComponent pParent, AnimationStyle pStyle, AnimationPosition pPosition, int pRange) {
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
        range = pRange;
        stateContext.getMyState().updateBaseLine();
        if (AnimationPosition.BOTTOMM_MIDDLE == position) {
            stateContext.getMyState().setToBottom();
            stateContext.getMyState().setToMiddle();

        }

    }


    public void execute() {
        stateContext.execute();
    }

    public void switchState(AnimationState state) {
        stateContext.switchState(state);

    }

    public JComponent getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JComponent getParent() {
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

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
