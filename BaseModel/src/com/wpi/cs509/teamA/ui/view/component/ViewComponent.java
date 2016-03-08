package com.wpi.cs509.teamA.ui.view.component;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;

public class ViewComponent {

	static protected ImageComponent imageComponent = null;
	static protected InputPanelComponent inputPanel = null;
	static protected MainModel model = null;
	static protected AnimationControl ac = new AnimationControl();

	protected ViewComponent() {

	}

	static public void init(ImageComponent pIC, InputPanelComponent pIP, MainModel pMM) {
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
