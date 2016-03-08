package com.wpi.cs509.teamA.service;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.controller.ViewRerenderController;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.renderer.ViewComponent;
import com.wpi.cs509.teamA.util.Coordinate;

/**
 * This class implements what the should do when mouse has some certain events
 * 
 * TODO: singleton, this should be initialized only once
 * 
 * @author teama
 */
public class ImageMouseWheelListener implements MouseWheelListener {

	static float scaleIncConst = 0.15f;
	static private double upperBound = 2.5;
	static private double lowerBound = 0.5;

	private MainModel model;

	public ImageMouseWheelListener() {

		// initialize the model
		model = ViewComponent.getModel();

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		float scaleInc = 0;
		int notches = e.getWheelRotation();
		if (notches < 0) {
			scaleInc += scaleIncConst;

		} else {
			scaleInc -= scaleIncConst;
		}

		double scale = model.getCurrentMap().getDisplayScale();
		if (scale + scaleInc > upperBound || scale + scaleInc < lowerBound)
			return;

		this.changeDisplayScale(scaleInc, new Coordinate(Math.round(e.getX()), Math.round(e.getY())));
	}

	private void changeDisplayScale(float scaleInc, Coordinate coor) {
		GeneralMap map = model.getCurrentMap();
		double scale = map.getDisplayScale() + scaleInc;
		map.setDisplayScale((float) scale);
		model.getLinearTransform().setScale(scale);
		// force update
		ViewRerenderController.updateView();

	}

}
