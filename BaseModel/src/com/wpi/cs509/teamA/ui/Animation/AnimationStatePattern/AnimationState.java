package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.AnimationObject;

/**
 * Created by cuixi on 12/10/2015.
 */
public abstract class AnimationState {

    protected AnimationObject object = null;
    public AnimationState(AnimationObject obj) {
        object = obj;
    }

    public abstract void execute();

}
