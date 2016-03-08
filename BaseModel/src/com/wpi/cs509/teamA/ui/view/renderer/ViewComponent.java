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

	static private ImageComponentRenderer imageComponent = null;
	static private InputPanelRenderer inputPanel = null;
	static private MainModel model = null;
	static private AnimationControl ac = new AnimationControl();

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

	/**
	 * @return the imageComponent
	 */
	public static ImageComponentRenderer getImageComponent() {
		return imageComponent;
	}

	/**
	 * @param imageComponent
	 *            the imageComponent to set
	 */
	public static void setImageComponent(ImageComponentRenderer imageComponent) {
		ViewComponent.imageComponent = imageComponent;
	}

	/**
	 * @return the inputPanel
	 */
	public static InputPanelRenderer getInputPanel() {
		return inputPanel;
	}

	/**
	 * @param inputPanel
	 *            the inputPanel to set
	 */
	public static void setInputPanel(InputPanelRenderer inputPanel) {
		ViewComponent.inputPanel = inputPanel;
	}

	/**
	 * @return the model
	 */
	public static MainModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public static void setModel(MainModel model) {
		ViewComponent.model = model;
	}

	/**
	 * @return the ac
	 */
	public static AnimationControl getAc() {
		return ac;
	}

	/**
	 * @param ac
	 *            the ac to set
	 */
	public static void setAc(AnimationControl ac) {
		ViewComponent.ac = ac;
	}

}
