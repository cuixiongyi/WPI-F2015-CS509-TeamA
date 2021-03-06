package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;

import java.awt.*;

/**
 * Created by cuixi on 12/11/2015.
 */
public class AnimationStateSlidingDown extends AnimationState {
    public AnimationStateSlidingDown(AnimationObject obj) {
        super(obj);
        updateBaseLine();
        if (AnimationPosition.BOTTOMM_MIDDLE == object.getPosition()) {

            setToHorizontal_Middle();
        }
        updatePos();


    }

    @Override
    public AnimationStateEnum execute() {
        updateBaseLine();
        if (AnimationPosition.BOTTOMM_MIDDLE == object.getPosition()) {
            Point location = object.getPanel().getLocation();
            if (baseLine - location.getY()  > 0) {
                pos.setY( pos.getY()+speed);

                object.getPanel().setLocation((int)pos.getX(), (int)pos.getY());

//                object.getPanel().setLocation((int)location.getX(), (int)(location.getY()-speed));
                return AnimationStateEnum.SLIDING_IN;

            }
        }
        return AnimationStateEnum.SLIDED_IN;

    }
}
