package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class MouseActionEditEdge extends MouseActionState {

	private JButton btnNeighborManage;

	private java.util.List<Edge> edgeToAdd = new ArrayList<Edge>();

	private List<Node> nodesToPaint = new ArrayList<>();

	private Node lastNode = null;

	public MouseActionEditEdge(MainModel pMM) {
		super(pMM);

		model.cleanUpRoute();
	}

	@Override
	public boolean cleanup() {

		return false;
	}

	@Override
	public boolean execute(MouseEvent e) {
		/// update coor and coorTrans
		getMouseTransCoor(e);

		// we have to minus 5 to correct deviation
		xPos = e.getX();
		yPos = e.getY();
		// TODO Auto-generated method stub
		// TODO this is a hack
		if (e.getButton() == MouseEvent.BUTTON1) {

			Node node = Database.getNodeFromCoordinate(coorTrans, model.getCurrentMap().getMapId());
			if (null == node) {
				return false;
			}
			System.out.println(node.getId());
			if (null == lastNode) {
				lastNode = node;
				nodesToPaint.add(node);

			} else {
				// TODO this is a hack, distance is set to -1 as default
				edgeToAdd.add(new Edge(lastNode, node));
				nodesToPaint.add(node);
				NodeRelationDao nrd = new NodeRelationDaoImpl();
				nrd.deleteOrAddEdge(lastNode, node);
				Database.InitFromDatabase();
				lastNode = null;

			}
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			//
		} else if (e.getButton() == MouseEvent.BUTTON3) {

		}

		return false;
	}

	@Override
	public void paintOnImage(Graphics2D g2) {

		// TODO draw newly added edge
		LinearTransform lt = model.getLinearTransform();
		PaintHelperComposite.paintNodes(model.getCurrentMap().getNodes(), g2, PaintHelperBasics.DrawStyleEnum.BasicNode,
				lt);
		PaintHelperComposite.paintEdges(model.getCurrentMap().getEdges(), g2, PaintHelperBasics.DrawStyleEnum.BasicEdge,
				lt);
		PaintHelperComposite.paintDots(model.getCurrentMap().getNodes(), g2, lt);

		PaintHelperComposite.paintDots(Database.getAllMapRelationNodesFromMapId(model.getCurrentMapID()), g2, lt,
				PaintHelperBasics.DrawStyleEnum.NewNode);
		if (null != lastNode && model.getCurrentMap().getMapId() == lastNode.getMap().getMapId()) {
			PaintHelperBasics.paintDot(lastNode, g2, PaintHelperBasics.DrawStyleEnum.SelectedNode);
		}
	}
}
