package com.wpi.cs509.teamA.ui.Animation;

/**
 * Created by cuixi on 12/10/2015.
 */

public enum   AnimationPosition {
    UNDEFINED("UNDEFINED"),
    LEFT_MIDDLE("LEFT_MIDDLE"),
    BOTTOMM_MIDDLE("BOTTOMM_MIDDLE");



    private String position;

    AnimationPosition(String tmp) {
        this.position = tmp;
    }

    @ Override
    public String toString() {
        return this.position;
    }

}
