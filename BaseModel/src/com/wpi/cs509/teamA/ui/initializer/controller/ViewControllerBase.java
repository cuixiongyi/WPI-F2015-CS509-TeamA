package com.wpi.cs509.teamA.ui.initializer.controller;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.initializer.UserScreen;
import com.wpi.cs509.teamA.ui.initializer.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.initializer.view.ImageComponent;
import com.wpi.cs509.teamA.ui.initializer.view.InputPanel;

public class ViewControllerBase {

	static protected ImageComponent imageComponent = null;
	static protected InputPanel inputPanel = null;
	static protected UserScreen userScreen = null;
	static protected MainModel model = null;
	static protected AnimationControl ac = new AnimationControl();

	protected ViewControllerBase() {

	}

	static public void init(ImageComponent pIC, InputPanel pIP, MainModel pMM, UserScreen pUS) {
		if (null == pIC)
			throw new NullPointerException("Empty ImageComponent");
		if (null == pIP)
			throw new NullPointerException("Empty ImageComponent");
		if (null == pMM)
			throw new NullPointerException("Empty MainModel");
		if (null == pUS)
			throw new NullPointerException("Empty UserScreen");

		imageComponent = pIC;
		inputPanel = pIP;
		model = pMM;
		userScreen = pUS;
	}

}
