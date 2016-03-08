package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.UserScreen;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

public class ViewControllerBase {

	static protected ImageComponent imageComponent = null;
	static protected InputPanel inputPanel = null;
	static protected MainModel model = null;
	static protected AnimationControl ac = new AnimationControl();

	protected ViewControllerBase() {

	}

	static public void init(ImageComponent pIC, InputPanel pIP, MainModel pMM) {
		if (null == pIC)
			throw new NullPointerException("Empty ImageComponent");
		if (null == pIP)
			throw new NullPointerException("Empty ImageComponent");
		if (null == pMM)
			throw new NullPointerException("Empty MainModel");

		imageComponent = pIC;
		inputPanel = pIP;
		model = pMM;
	}

}
