package com.wpi.cs509.teamA.ui.controller;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.History;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationPosition;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingUp;
import com.wpi.cs509.teamA.ui.Animation.AnimationStyle;
import com.wpi.cs509.teamA.ui.UIConstant;
import com.wpi.cs509.teamA.ui.Dialog.AdminDialog;
import com.wpi.cs509.teamA.ui.Dialog.OpenMapDialog;
import com.wpi.cs509.teamA.ui.Dialog.SignupDialog;
import com.wpi.cs509.teamA.ui.UserScreen;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionEditEdge;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionEditNode;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.ui.view.ViewManager;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.MarioListRenderer;
import com.wpi.cs509.teamA.util.NodeType;

class ViewControllerImpl extends ViewControllerBase {

	public void clickLogin() {
		if (model.getMyAccount() == null) {
			AdminDialog adminDialog = new AdminDialog(model, inputPanel);
			adminDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			adminDialog.setVisible(inputPanel.isFocusable());
			// stateContext.switchToAdminUser();
			ViewManager.updateView();

		} else {

			JOptionPane.showMessageDialog(null, "You have logged out");
			model.setMyAccount(null);
			Database.InitFromDatabase();
			// InputPanel.this.getBtnNeighborManage().setVisible(false);
			inputPanel.getBtnLogin().setText(UIConstant.LOGIN);
			// InputPanel.this.getBtnSynchronize().setVisible(false);
			model.switchToState(new MouseActionSelectNode(model));

			ViewManager.updateView();
		}
	}

	public void clickEditNode() {
		JToggleButton button = inputPanel.getBtnMngNode();

		if (MouseActionEditNode.class.isInstance(model.getMyState())) {
			button.setSelected(false);
            model.switchToState(new MouseActionSelectNode(model));
		} else {
			model.switchToState(new MouseActionEditNode(model));
			button.setSelected(true);
            inputPanel.getBtnMngEdge().setSelected(false);

        }
	}

	public void clickEditEdge() {
		JToggleButton button = inputPanel.getBtnMngEdge();

		if (MouseActionEditEdge.class.isInstance(model.getMyState())) {
			button.setSelected(false);
            model.switchToState(new MouseActionSelectNode(model));
		} else {
			model.switchToState(new MouseActionEditEdge(model));
			button.setSelected(true);
            inputPanel.getBtnMngNode().setSelected(false);


        }
	}

	public void clickSignup() {
		SignupDialog signUpDialog = new SignupDialog(inputPanel);
		signUpDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		signUpDialog.setVisible(true);
	}

	public void clickFilter(NodeType nodeType) {
		model.setFilter(nodeType);
		ViewManager.updateView();
	}

	public void clickAllFilter() {
		model.addAllFilters();
		ViewManager.updateView();
	}

	public void clickClearFilter() {
		model.clearFilters();
		ViewManager.updateView();
	}

	public void clickSearch() {
		if (model.getStartNode() == null || model.getEndNode() == null)
			return;
		if(model.getMyAccount()!=null){
			addHistory();
		
		}
		
		// inputPanel.picLabel.setVisible(false);
		inputPanel.getMapList().setVisible(true);
		inputPanel.getMapList().setEnabled(true);
		inputPanel.getMapList().setCellRenderer(new MarioListRenderer());
		ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();
		inputPanel.getMapList().removeAll();

		AlgoController algoController = new AlgoController(model.getStartNode(), model.getEndNode());

		Stack<Node> pathNodes = algoController.getRoute();

		ArrayList<Node> singleMapPath = new ArrayList<Node>();
		ArrayList<String> mapNameList = new ArrayList<String>();
		ArrayList<GeneralMap> mapList = new ArrayList<GeneralMap>();
		int tmpMapId = pathNodes.peek().getMap().getMapId();
		mapNameList.add(pathNodes.peek().getMap().getMapAbbrName());
		mapList.add(pathNodes.peek().getMap());
		model.clearPaths();
		Path path = new Path();

		while (pathNodes.size() > 0) {

			Node node = Database.getNodeFromId(pathNodes.pop().getId());
			if (node.getMap().getMapId() == tmpMapId) {
				singleMapPath.add(node);
				path.addNode(node);


			} else {
				multiMapPathLists.add(singleMapPath);

				model.addOnePath(path);
				path = new Path();
				path.setMap(node.getMap());
				path.addNode(node);

				singleMapPath = new ArrayList<Node>();
				singleMapPath.add(node);
				tmpMapId = node.getMap().getMapId();
				mapNameList.add(node.getMap().getMapAbbrName());

				mapList.add(node.getMap());

			}
		}

		model.addOnePath(path);

		multiMapPathLists.add(singleMapPath);

		// reset and initiate the Jlist

		DefaultListModel<String> mapListModel = new DefaultListModel<>();
		for (String name : mapNameList) {
			mapListModel.addElement(name);
		}
		model.setCurrentPath(0);
		model.setMultiMapLists(mapList);
		System.out.println(mapList);
        inputPanel.getMapList().setModel(mapListModel);

		addThumbNail();

		ViewManager.updateView();

	}

	public void clickOpenMap() {
		// TODO Auto-generated method stub
		OpenMapDialog openMapDialog = new OpenMapDialog(model);
		openMapDialog.setVisible(true);
		ViewManager.updateView();
		// int returnVal = inputPanel.getFc().showOpenDialog(null);
		// if (returnVal == JFileChooser.APPROVE_OPTION) {
		// File file = inputPanel.getFc().getSelectedFile();
		// This is where a real application would open the file.
		// } else {
		// log.append("Open command cancelled by user." + newline);
		// }
		// }

	}

	private AnimationObject addThumbNail() {
		ViewManager.getThumbNailPanel().update();
		AnimationObject ret = ViewManager.getAC().checkObjectExist(ViewManager.getThumbNailPanel());
		if (null == ret) {
			UserScreen.getUserScreen().getContentPane().add(ViewManager.getThumbNailPanel(),new Integer(5));
			ret = ViewManager.getAC().create(ViewManager.getThumbNailPanel(),ViewManager.getImageComponent() , AnimationStyle.SLIDE_LEFT, AnimationPosition.LEFT_MIDDLE,
					ViewManager.getThumbNailPanel().getWidth());
			ret.switchState(new AnimationStateSlidingUp(ret));
			ret.setSpeed(2.0);
			ViewManager.getThumbNailPanel().setVisible(true);
		}
		return ret;

	}
	
	private void addHistory(){
		ArrayList<History> newHistory = (ArrayList<History>) model.getMyAccount().getHistory();
		
		newHistory.add(new History(model.getStartNode().getName(), model.getStartNode().getId(), 0));
		if(model.getEndNode().size()==1){
			newHistory.add(new History(model.getEndNode().get(0).getName(), model.getEndNode().get(0).getId(), 0));
		}
		
		model.getMyAccount().setHistory(newHistory);
	}

}