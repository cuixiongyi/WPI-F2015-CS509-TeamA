package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;

import java.awt.*;

/**
 * Created by cuixi on 12/12/2015.
 */
public class AnimationStateSlidingIn extends AnimationState{

    public AnimationStateSlidingIn(AnimationObject obj) {
        super(obj);
        updateBaseLine();
        if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {

        }

    }

    @Override
    public AnimationStateEnum execute() {
        updateBaseLine();
        if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {
            Point location = object.getPanel().getLocation();
            if (object.getPanel().getLocation().getX() + object.getPanel().getWidth() > baseLine) {
                object.getPanel().setLocation((int)location.getX()-speed, (int)location.getY());
                return AnimationStateEnum.SLIDING_IN;

            }
        }
        return AnimationStateEnum.SLIDED_IN;
    }


}
