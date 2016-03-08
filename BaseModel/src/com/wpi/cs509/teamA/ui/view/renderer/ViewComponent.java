package com.wpi.cs509.teamA.ui.view.renderer;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;

/**
 * 
 * This class contains all the components of the view and data that required
 * 
 * What view need to do is render
 * 
 * @author teama
 *
 */
public class ViewComponent {

	static protected ImageComponentRenderer imageComponent = null;
	static protected InputPanelRenderer inputPanel = null;
	static protected MainModel model = null;
	static protected AnimationControl ac = new AnimationControl();

	protected ViewComponent() {

	}

	static public void init(ImageComponentRenderer pIC, InputPanelRenderer pIP, MainModel pMM) {
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
