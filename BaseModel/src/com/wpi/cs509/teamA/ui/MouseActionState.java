package com.wpi.cs509.teamA.ui;

/**
 * Created by xiongyi on 11/23/15.
 *
 * This is the abstract class for state pattern
 */
public abstract class MouseActionState {


    private StateContext stateContext;

    abstract public boolean execute(int x, int y) ;

    abstract public boolean cleanup();

}
