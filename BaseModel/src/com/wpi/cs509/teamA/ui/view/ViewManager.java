package com.wpi.cs509.teamA.ui.view;

import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingDown;
import com.wpi.cs509.teamA.ui.Dialog.InformationPanel;
import com.wpi.cs509.teamA.ui.Dialog.ThumbNailPanel;
import com.wpi.cs509.teamA.ui.controller.BaseViewController;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.InputPanel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by cuixi on 12/3/2015.
 */
public class ViewManager extends BaseViewController implements Observer {

	private int mousePos = 0;
	private static InformationPanel nodeInformation = new InformationPanel();
	private static ThumbNailPanel thumbNailPanel = new ThumbNailPanel(model);

	public ViewManager() {

	}

	static public void infoPanelSlideDown() {
		AnimationObject AO = ViewManager.getAC().checkObjectExist(ViewManager.getNodeInformation());
		if (null == AO) {
			return;
		}
		AO.switchState(new AnimationStateSlidingDown(AO));

	}

	static public void updateImageComponent() {
		imageComponent.repaint();
	}

	static public void updateInputePanel() {
		inputPanel.repaint();
	}

	static public void updateView() {
		updateImageComponent();
		updateInputePanel();
		if (getThumbNailPanel().update()) {
			AnimationObject ao = getAC().checkObjectExist(getThumbNailPanel());
			if (null != ao) {
				ao.setToVertical_Middle();
			}
		}

	}

	static public ImageComponent getImageComponent() {
		return imageComponent;
	}

	static public InputPanel getInputPanel() {
		return inputPanel;
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (model != obs) {
			return;
		}
		ViewManager.updateView();
	}

	static public AnimationControl getAC() {
		return ac;
	}

	static public InformationPanel getNodeInformation() {
		return nodeInformation;
	}

	public static void setInformationNode(Node pnode) {
		nodeInformation.setNode(pnode);
	}

	public static ThumbNailPanel getThumbNailPanel() {
		return thumbNailPanel;
	}
}
