package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.Point2d;

import java.awt.*;

/**
 * Created by cuixi on 12/11/2015.
 */
public class AnimationStateSlidingUp extends AnimationState{

    public AnimationStateSlidingUp(AnimationObject obj) {
        super(obj);
        updateBaseLine();
        if (AnimationPosition.BOTTOMM_MIDDLE == object.getPosition()) {
//            setToBottom();
            setToHorizontal_Middle();
        }
        updatePos();
    }

    @Override
    public AnimationStateEnum execute() {
        updateBaseLine();
        if (AnimationPosition.BOTTOMM_MIDDLE == object.getPosition()) {
            Point location = object.getPanel().getLocation();
            if (baseLine - location.getY()  < object.getPanel().getHeight() ) {
                double tmp = pos.getY()-speed;
                pos.setY( tmp);
                object.getPanel().setLocation((int)pos.getX(), (int)pos.getY());
                return AnimationStateEnum.SLIDING_OUT;

            }
        }

        return AnimationStateEnum.SLIDED_OUT;
    }
}
