package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.dao.impl.InitAllMatrixImpl;

public class SystemFacade {

	public SystemFacade() {

		// initialize all the matrix for the map
		InitAllMatrixImpl.getInitAllMatrixImpl();
		// singleton
		UserScreen.launchUserScreen();
	}

}
