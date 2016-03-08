package com.wpi.cs509.teamA.ui.controller.Listener;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.StateContextModel;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.util.Coordinate;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		String message;
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

		changeDisplayScale(scaleInc, new Coordinate(Math.round(e.getX()), Math.round(e.getY())));
	}

	private void changeDisplayScale(float scaleInc, Coordinate coor) {
		GeneralMap map = model.getCurrentMap();
		double scale = map.getDisplayScale() + scaleInc;
		map.setDisplayScale((float) scale);
		model.getLinearTransform().setScale(scale);
		int xpos = imageComponent.getImageXpos();
		int ypos = imageComponent.getImageYpos();
		int x = scaleImageOffset(xpos, scaleInc, coor.getX());
		int y = scaleImageOffset(ypos, scaleInc, coor.getY());

		// imageComponent.setImageXpos(x);
		// imageComponent.setImageXpos(y);
		imageComponent.repaint();

	}

	private int scaleImageOffset(int x, float scaleInc, int mouseCoor) {
		float ret = 0;
		if (mouseCoor - x < 0)
			mouseCoor = 0;
		if (x > 0) {
			ret = x - (mouseCoor - x) * (1.0f + scaleInc);
		} else {
			ret = (float) x * (1.0f - scaleInc);
		}

		return Math.round(ret);
	}

}
