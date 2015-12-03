package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;

public abstract class UserState {
	 protected InputPanel inputPanel;
	 protected ImageComponent imageComponent;
	 protected StateContext stateContext;
	 
	 public UserState(StateContext pStateContext) {
	        this.stateContext = pStateContext;
	        this.inputPanel = pStateContext.getInputPanel();
	        this.imageComponent = pStateContext.getImageComponent();
	    };
	
	
	abstract public boolean execute(MouseEvent e) ;

    abstract public boolean cleanup();
}
