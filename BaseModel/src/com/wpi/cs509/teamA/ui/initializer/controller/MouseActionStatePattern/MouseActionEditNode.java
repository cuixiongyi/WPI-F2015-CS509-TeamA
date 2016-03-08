package com.wpi.cs509.teamA.ui.initializer.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.initializer.Dialog.NodeEditDialog;
import com.wpi.cs509.teamA.ui.initializer.view.ViewManager;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseActionEditNode extends MouseActionState {

	private Node lastNode = null;

	public MouseActionEditNode(MainModel pMM) {
		super(pMM);

		model.cleanUpRoute();

	}

	@Override
	public boolean cleanup() {

		return false;
	}

	@Override
	public boolean execute(MouseEvent e) {
		/**
		 * update coor and coorTrans
		 */
		getMouseTransCoor(e);
		Node node = getNodeFromClick(e);

		if (e.getButton() == MouseEvent.BUTTON3) {
			if (null == lastNode) {
				lastNode = node;
				return true;
			} else {
				Coordinate xy = getTransCoor(new Coordinate(e.getX(), e.getY()));
				Coordinate diff = new Coordinate(xy.getX() - lastNode.getLocation().getX(),
						xy.getY() - lastNode.getLocation().getY());
				if (Math.abs(diff.getX()) > Math.abs(diff.getY())) {
					xy.setY(lastNode.getLocation().getY());
				} else {
					xy.setX(lastNode.getLocation().getX());
				}

				addNewNode(xy);
				lastNode = null;
			}

		}
		if (e.getButton() == MouseEvent.BUTTON1) {

			lastNode = null;
			if (null != node) {
				/// TODO add edit node action
				NodeEditDialog nodeInfo = new NodeEditDialog(ViewManager.getImageComponent(), model, node);
				nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				nodeInfo.setVisible(nodeInfo.isFocusable());

			} else {
				// Create a NodeEditDialog
				addNewNode(coorTrans);
			}
		}
		return true;
	}

	@Override
	public void paintOnImage(Graphics2D g2) {
		LinearTransform lt = model.getLinearTransform();
		PaintHelperComposite.paintNodes(model.getCurrentMap().getNodes(), g2, PaintHelperBasics.DrawStyleEnum.BasicNode,
				lt);
		PaintHelperComposite.paintEdges(model.getCurrentMap().getEdges(), g2, PaintHelperBasics.DrawStyleEnum.BasicEdge,
				lt);
		PaintHelperComposite.paintDots(model.getCurrentMap().getNodes(), g2, lt);

		if (null != lastNode && model.getCurrentMap() == lastNode.getMap()) {
			PaintHelperBasics.paintDot(lastNode, g2, PaintHelperBasics.DrawStyleEnum.SelectedNode);
		}
	}

	private void addNewNode(Coordinate xy) {
		NodeEditDialog nodeInfo = new NodeEditDialog(ViewManager.getImageComponent(), model, xy.getX(), xy.getY());
		nodeInfo.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		nodeInfo.setVisible(nodeInfo.isFocusable());
	}
}
