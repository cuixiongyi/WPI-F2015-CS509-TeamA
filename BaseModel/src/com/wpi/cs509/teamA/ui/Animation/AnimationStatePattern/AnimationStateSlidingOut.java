package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

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
    }

    @Override
    public AnimationStateEnum execute() {
        updateBaseLine();
        if (AnimationPosition.LEFT_MIDDLE == object.getPosition()) {
            return AnimationStateEnum.SLIDING_OUT;

        }
        return AnimationStateEnum.SLIDED_OUT;
    }


}
