package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import java.awt.*;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;

/**
 * Created by cuixi on 12/10/2015.
 */

public class AnimationStateSlidingOut extends AnimationState{

    public AnimationStateSlidingOut(AnimationObject obj) {
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
            if (object.getPanel().getLocation().getX() < baseLine) {
                pos.setX( pos.getX()+speed);
                object.getPanel().setLocation((int)pos.getX(), (int)pos.getY());
//                object.getPanel().setLocation((int)(location.getX()+speed), (int)location.getY());
                return AnimationStateEnum.SLIDING_OUT;

            }
        }
        return AnimationStateEnum.SLIDED_OUT;
    }


}
