package com.wpi.cs509.teamA.ui.initializer.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationObject;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationStateStayIn extends AnimationState{

    public AnimationStateStayIn(AnimationObject obj) {
        super(obj);

    }

@Override
public AnimationStateEnum execute() {

    return AnimationStateEnum.SLIDING_IN;
}


}
