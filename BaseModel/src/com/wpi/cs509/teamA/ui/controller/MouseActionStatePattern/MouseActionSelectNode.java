package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingUp;
import com.wpi.cs509.teamA.ui.Animation.AnimationStyle;
import com.wpi.cs509.teamA.ui.Dialog.NodeSetMenu;
import com.wpi.cs509.teamA.ui.UserScreen;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperBasics;
import com.wpi.cs509.teamA.util.PaintHelper.PaintHelperComposite;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class MouseActionSelectNode extends MouseActionState {

	private java.util.List<Node> nodesToPaintIcon = new ArrayList<Node>();

	public MouseActionSelectNode(MainModel pMM) {
		super(pMM);

	}

	@Override
	public boolean cleanup() {

		return true;
	}

	@Override
	public boolean execute(MouseEvent e) {
		/**
		 * update coor and coorTrans
		 */
		getMouseTransCoor(e);
		Node node = getNodeFromClick(e);

		if (e.getButton() == MouseEvent.BUTTON3) {

            /// TODO add edit node action
            NodeSetMenu nodeSetMenu = new NodeSetMenu(ViewManager.getInputPanel(), model, node);
            nodeSetMenu.show(e.getComponent(), xPos, yPos);

		}


		if (e.getButton() == MouseEvent.BUTTON1) {
            if (jumpToNextPath(node)) {
                return true;
            }

			if (null != node) {
				ViewManager.getNodeInformation().setNode(node);
				AnimationObject ret = addInfoAnimation();
				ret.switchState(new AnimationStateSlidingUp(ret));

			}else{
				ViewManager.infoPanelSlideDown();

			}
		}
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			if (null != node) {
				if (node.getNodeType() == NodeType.LAB) {
					// model.setCurrentMap();
				}
			}
		}
		return false;
	}

	@Override
	public void paintOnImage(Graphics2D g2) {
        LinearTransform lt = model.getLinearTransform();

		PaintHelperComposite.paintRoute(g2, lt);
		PaintHelperComposite.paintNodes(model.getCurrentMap().getNodes(), g2, lt);
		PaintHelperComposite.paintStartEndNode(g2, lt);
	}

    private boolean jumpToNextPath(Node node) {

        Path path = model.getCurrentPath();
        if (null == path)
            return false;

        List<Node> nodes = path.getNodes();

        if (null != path && 0 != nodes.size()) {
            int mapID = model.getCurrentMapID();
            List<Node> nodesCross = Database.getAllMapRelationNodesFromMapId(mapID);
            if (nodesCross.contains(node)) {
                if(nodes.size() == 1) {
                    model.setNextPath();
                    return true;
                }
                if (nodes.get(0).getId() == node.getId() && model.getCurrentPathIdx()-1 >= 0) {
                    model.setPrivousPath();
                    return true;

                }
                if (nodes.get(nodes.size()-1).getId() == node.getId() && model.getCurrentPathIdx()+1 <model.getPaths().size()) {
                    model.setNextPath();
                    return true;
                }

            }
        }
        return false;
    }

	private AnimationObject addInfoAnimation() {
		AnimationObject ret = ViewManager.getAC().checkObjectExist(ViewManager.getNodeInformation());
		if (null == ret) {
			UserScreen.getUserScreen().getContentPane().add(ViewManager.getNodeInformation(),new Integer(3));
			ret = ViewManager.getAC().create(ViewManager.getNodeInformation(),ViewManager.getImageComponent() , AnimationStyle.SLIDE_UP, AnimationPosition.BOTTOMM_MIDDLE,
						ViewManager.getNodeInformation().getHeight());
			ret.setSpeed(4.0);
			ViewManager.getNodeInformation().setVisible(true);
		}


		return ret;
	}
}
