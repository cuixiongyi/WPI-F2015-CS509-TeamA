package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseActionAdminUser extends MouseActionState {

	public MouseActionAdminUser(MainModel pMM) {
		super(pMM);

	}

	@Override
	public boolean cleanup() {

		return false;
	}

	@Override
	public boolean execute(MouseEvent e) {
		/// update coor and coorTrans
		getMouseTransCoor(e);

		xPos = e.getX();
		yPos = e.getY();
		if (e.getButton() == MouseEvent.BUTTON3) {
			// NodeManageMenu nodeManageMenu = new
			// NodeManageMenu(stateContext.getImageComponent(), xPos, yPos,
			// stateContext);
			// nodeManageMenu.show(e.getComponent(), xPos, yPos);

			return false;
		}

		return false;
	}

	@Override
	public void paintOnImage(Graphics2D g2) {
		LinearTransform lt = model.getLinearTransform();
		PaintHelperComposite.paintEdges(model.getCurrentMap().getEdges(), g2, PaintHelperBasics.DrawStyleEnum.BasicEdge, lt);
		PaintHelperComposite.paintNodes(model.getCurrentMap().getNodes(), g2, PaintHelperBasics.DrawStyleEnum.NewNode, lt);
		PaintHelperComposite.paintRoute(g2, lt);

		PaintHelperComposite.paintStartEndNode(g2, lt);

	}
}
