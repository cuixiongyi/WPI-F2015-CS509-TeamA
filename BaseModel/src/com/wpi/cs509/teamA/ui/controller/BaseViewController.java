package com.wpi.cs509.teamA.ui.controller;

import com.wpi.cs509.teamA.model.DataModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.UserScreen;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

public class BaseViewController {

	static protected ImageComponent imageComponent = null;
	static protected InputPanel inputPanel = null;
	static protected UserScreen userScreen = null;
	static protected DataModel model = null;
	static protected AnimationControl ac = new AnimationControl();

	protected BaseViewController() {

	}

	static public void init(ImageComponent pIC, InputPanel pIP, DataModel pMM, UserScreen pUS) {
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
