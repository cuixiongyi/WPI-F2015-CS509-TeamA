package com.wpi.cs509.teamA.ui.initializer.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.initializer.Animation.*;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationStateContext {
    private AnimationState myState = null;

    public AnimationStateContext() {
        myState = new AnimationStateStayIn(null);
    }


    public void execute() {
        myState.execute();
    };

    public void switchState(AnimationState state) {
        myState = state;
    }


    public AnimationState getMyState() {
        return myState;
    }
}
