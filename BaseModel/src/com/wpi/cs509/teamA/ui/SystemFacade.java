package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.DupEntranceMap;
import com.wpi.cs509.teamA.dao.impl.AllInitializedMatrixImpl;

/**
 * This is class is to initialize all the resources that will be used in the
 * system. Since the method used are all singleton, so the system will only
 * access database in this class (once). So later we use the resource, they are
 * already there.
 * 
 * @author CS 509-Team A
 *
 */
public class SystemFacade {

	/**
	 * The method to initialize all the resources
	 */
	public SystemFacade() {

		// initialize the relationship map
		DupEntranceMap.initDupEntranceMap();
		// initialize all the matrix for the map
		AllInitializedMatrixImpl.getAllInitializedMatrixImpl().getAllInitializedMatrix();
		// singleton
		UserScreen.launchUserScreen();
		
	}

}
