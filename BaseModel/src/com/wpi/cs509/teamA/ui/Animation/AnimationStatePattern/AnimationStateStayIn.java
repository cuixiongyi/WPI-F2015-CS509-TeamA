package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationStateStayIn extends AnimationState{

    public AnimationStateStayIn(AnimationObject obj) {
        super(obj);

    }


    @Override
    public void paintAnimation() {
        if (null == object) {
            return;
        }
    }
}
