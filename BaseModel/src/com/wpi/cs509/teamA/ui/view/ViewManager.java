package com.wpi.cs509.teamA.ui.view;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingDown;
import com.wpi.cs509.teamA.ui.Dialog.InformationPanel;
import com.wpi.cs509.teamA.ui.Dialog.ThumbNailPanel;
import com.wpi.cs509.teamA.ui.view.renderer.ImageComponentRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.InputPanelRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.ViewComponent;

import java.util.Observable;
import java.util.Observer;

/**
 * This class will listen to the change of the view and then update the view or
 * to say, rerender the view
 */
public class ViewManager implements Observer {

	private static ImageComponentRenderer imageComponent = ViewComponent.getImageComponent();
	private static MainModel model = ViewComponent.getModel();
	private static InputPanelRenderer inputPanel = ViewComponent.getInputPanel();
	private static AnimationControl ac = ViewComponent.getAc();

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

	/**
	 * Update the view, call repaint methods
	 */
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

	/**
	 * This method get executed when change in model triggered
	 */
	@Override
	public void update(Observable obs, Object arg) {
		if (model != obs) {
			return;
		}
		updateView();
	}

	static public ImageComponentRenderer getImageComponent() {
		return imageComponent;
	}

	static public InputPanelRenderer getInputPanel() {
		return inputPanel;
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
