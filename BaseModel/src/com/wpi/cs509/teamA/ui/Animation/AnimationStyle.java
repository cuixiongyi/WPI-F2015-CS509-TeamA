package com.wpi.cs509.teamA.ui.Animation;

/**
 * Created by cuixi on 12/10/2015.
 */
public enum   AnimationStyle {
    UNDEFINED("UNDEFINED"),
    SLIDE_LEFT("SLIDE_LEFT"),
    SLIDE_RIGHT("SLIDE_LEFT"),
    SLIDE_UP("SLIDE_LEFT");



    private String animationStyle;

    AnimationStyle(String tmp) {
        this.animationStyle = tmp;
    }

    @ Override
    public String toString() {
        return this.animationStyle;
    }

}
