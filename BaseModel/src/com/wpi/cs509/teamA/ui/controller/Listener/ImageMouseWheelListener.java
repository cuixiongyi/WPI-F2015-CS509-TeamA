package com.wpi.cs509.teamA.ui.controller.Listener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.persistence.bean.GeneralMap;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.util.Coordinate;

/**
 * Created by cuixi on 11/27/2015.
 */
public class ImageMouseWheelListener implements MouseWheelListener {

	static float scaleIncConst = 0.15f;
	static private double upperBound = 2.5;
	static private double lowerBound = 0.5;

	private ImageComponent imageComponent = null;
	private MainModel model = null;

	public ImageMouseWheelListener(ImageComponent pImageComponent, MainModel pModel) {
		this.imageComponent = pImageComponent;
		model = pModel;
		if (null == pModel) {
			throw new NullPointerException();
		}
		model = pModel;

		imageComponent.addMouseWheelListener(this);

	}

	/**
	 * When a mouse wheel event occurs, that object's mouseWheelMoved method is
	 * invoked.
	 */
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

		// TODO: to display the scaled map, the method should belongs to class
		// LinearTransform, or view manager or something, since this is just an
		// event, and view should update the change of data
		changeDisplayScale(scaleInc, new Coordinate(Math.round(e.getX()), Math.round(e.getY())));
	}

	/**
	 * TODO: May not belongs to this class
	 * 
	 * Display the scaled map on the screen
	 * 
	 * @param scaleInc
	 * @param coor
	 */
	private void changeDisplayScale(float scaleInc, Coordinate coor) {

		GeneralMap map = model.getCurrentMap();
		double scale = map.getDisplayScale() + scaleInc;
		map.setDisplayScale((float) scale);
		model.getLinearTransform().setScale(scale);

		imageComponent.repaint();

	}

}
