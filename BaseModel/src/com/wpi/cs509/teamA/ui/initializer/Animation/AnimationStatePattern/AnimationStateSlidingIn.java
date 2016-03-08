package com.wpi.cs509.teamA.ui.initializer.Animation.AnimationStatePattern;

import java.awt.*;

import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationPosition;

/**
 * Created by cuixi on 12/12/2015.
 */
public class AnimationStateSlidingIn extends AnimationState{

    public AnimationStateSlidingIn(AnimationObject obj) {
        super(obj);
        updateBaseLine();
        if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {


        }
        updatePos();



    }

    @Override
    public AnimationStateEnum execute() {
        updateBaseLine();
        if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {
            Point location = object.getPanel().getLocation();
            if (object.getPanel().getLocation().getX() + object.getPanel().getWidth() > baseLine) {
                pos.setX( pos.getX()-speed);
                object.getPanel().setLocation((int)pos.getX(), (int)pos.getY());

//                object.getPanel().setLocation((int)(location.getX()-speed), (int)location.getY());
                return AnimationStateEnum.SLIDING_IN;

            }
        }
        return AnimationStateEnum.SLIDED_IN;
    }


}
