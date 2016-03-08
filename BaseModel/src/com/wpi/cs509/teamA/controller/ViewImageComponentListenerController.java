package com.wpi.cs509.teamA.controller;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.service.ImageMouseListener;
import com.wpi.cs509.teamA.service.ImageMouseWheelListener;
import com.wpi.cs509.teamA.ui.view.renderer.ImageComponentRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.ViewComponent;

/**
 * 
 * This class will bind two different listeners to the image component
 * 
 * 1. mouse drag
 * 
 * 2. mouse wheel
 * 
 * @author teama
 *
 */
public class ViewImageComponentListenerController {

	private static ViewImageComponentListenerController viewImageComponentListenerController;

	private static ImageComponentRenderer imageComponent = ViewComponent.getImageComponent();
	private static MainModel model = ViewComponent.getModel();

	/**
	 * 
	 * bind listeners, singleton
	 * 
	 * @return true the first time bind the listeners
	 */
	public synchronized static boolean bindListeners() {
		if (null == viewImageComponentListenerController) {
			viewImageComponentListenerController = new ViewImageComponentListenerController();
			return true;
		}

		return false;
	}

	/**
	 * 
	 * Add listeners here
	 * 
	 */
	private ViewImageComponentListenerController() {

		// TODO: singleton
		new ImageMouseListener(imageComponent, model);
		// new ImageMouseWheelListener(imageComponent, model);
		// imageComponent.addMouseWheelListener(new ImageMouseWheelListener());
		imageComponent.addMouseWheelListener(new ImageMouseWheelListener());
	}

}
