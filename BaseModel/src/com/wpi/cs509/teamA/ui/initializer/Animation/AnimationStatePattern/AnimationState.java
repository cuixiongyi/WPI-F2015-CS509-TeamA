package com.wpi.cs509.teamA.ui.initializer.Animation.AnimationStatePattern;

import java.awt.*;
import java.awt.geom.Point2D;

import com.wpi.cs509.teamA.ui.initializer.UIConstant;
import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.initializer.Animation.Point2d;

/**
 * Created by cuixi on 12/10/2015.
 */
public abstract class AnimationState {

    protected AnimationObject object = null;
    protected double baseLine = 0;

    protected double speed = UIConstant.SLIDING_SPEED;

    protected Point2d pos = null;

    public AnimationState(AnimationObject obj) {
        object = obj;
    }

    public abstract AnimationStateEnum execute();

    public void updateBaseLine() {
        if (AnimationPosition.BOTTOMM_MIDDLE == object.getPosition()) {
            baseLine = object.getParent().getLocation().getY() + object.getParent().getHeight();
        }
        else if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {
            baseLine = object.getParent().getLocation().getX();

        }
    }

    public final void setToMiddleBottom() {
        setToBottom();
        setToHorizontal_Middle();
    }
    public final void setToVertical_Middle() {
        Point parentL = object.getParent().getLocation();
        int y = (int)parentL.getY() + object.getParent().getHeight()/2 - object.getPanel().getHeight()/2;
        object.getPanel().setLocation((int)object.getPanel().getLocation().getX(), y);

    }
    public final void setToLeft() {
        object.getPanel().setLocation((int)baseLine - object.getPanel().getWidth(), (int)object.getPanel().getLocation().getY());

    }

    public final void setToHorizontal_Middle() {
        Point parentL = object.getParent().getLocation();

        int x = (int)parentL.getX() + object.getParent().getWidth()/2 - object.getPanel().getWidth()/2;
        object.getPanel().setLocation(x, (int)object.getPanel().getLocation().getY());

    }

    public final void setToBottom() {
        object.getPanel().setLocation((int)object.getPanel().getLocation().getX(), (int)baseLine);

    }

    public final void setToBottomUpperLimit() {
        object.getPanel().setLocation((int)object.getPanel().getLocation().getX(), (int)baseLine-object.getRange());

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void updatePos() {
        pos = new Point2d(object.getPanel().getLocation().getX(), object.getPanel().getLocation().getY());

    }
}
