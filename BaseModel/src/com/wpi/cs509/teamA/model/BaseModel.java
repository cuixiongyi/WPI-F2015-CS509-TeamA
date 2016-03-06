package com.wpi.cs509.teamA.model;

import java.util.Observable;

/**
 * 
 * This is the base model for all the observable classes.
 * 
 * @author teama
 *
 */
public class BaseModel extends Observable {

	protected void modelChanged() {
		setChanged();
		notifyObservers();
	}

}
