package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.view.renderer.helper.PaintHelperBasics;

import java.awt.*;
import java.awt.event.MouseEvent;

/** 
 * this class is an interface for all the implementation class to follow
 * so all the mouse action will have same method interface
 *
 * This is the abstract class for state pattern
 */
public abstract class MouseActionState {

	protected MainModel model;

	protected int xPos;
	protected int yPos;

	protected Coordinate coor = null;
	protected Coordinate coorTrans = null;

	// MainModel model = null;
	public MouseActionState(MainModel pSC) {
		model = pSC;
		this.xPos = -1;
		this.yPos = -1;

	};

	abstract public void paintOnImage(Graphics2D g2);

	abstract public boolean execute(MouseEvent e);

	abstract public boolean cleanup();

	protected Coordinate getTransCoor(Coordinate xy) {

		return model.getLinearTransform().backTransferCoor(xy);
	}

	protected void getMouseTransCoor(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();
		coor = new Coordinate(e.getX(), e.getY());
		coorTrans = model.getLinearTransform().backTransferCoor(coor);
	}

	public Node getNodeFromClick(Coordinate coor) {
		return Database.getNodeFromCoordinate(coor, model.getCurrentMap().getMapId());
	}

	public Node getNodeFromClick(MouseEvent e) {
		getMouseTransCoor(e);
		return getNodeFromClick(coorTrans);
	}
}
