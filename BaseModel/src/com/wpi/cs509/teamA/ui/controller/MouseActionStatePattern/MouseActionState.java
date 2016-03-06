package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.wpi.cs509.teamA.model.DataModel;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;

/**
 * This is the abstract class for state pattern, different state will have
 * different permissions(actions)
 */
public abstract class MouseActionState {

	protected DataModel model;

	protected int xPos;
	protected int yPos;

	protected Coordinate coor = null;
	protected Coordinate coorTrans = null;

	public MouseActionState() {

	}

	public MouseActionState(DataModel pSC) {
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
