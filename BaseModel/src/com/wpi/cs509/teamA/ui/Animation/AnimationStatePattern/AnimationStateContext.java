package com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern;

import com.wpi.cs509.teamA.ui.Animation.*;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationStateContext {
    private AnimationState myState = null;

    public AnimationStateContext() {
        myState = new AnimationStateStayIn(null);
    }


    public void paintAnimation() {
        if (null == myState) {
            return;
        }
        myState.paintAnimation();
    }
    
    public void switchState(AnimationState state) {
        myState = state;
    }
}
