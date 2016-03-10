package com.wpi.cs509.teamA.controller;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingDown;
import com.wpi.cs509.teamA.ui.dialog.InformationPanel;
import com.wpi.cs509.teamA.ui.dialog.ThumbNailPanel;
import com.wpi.cs509.teamA.ui.view.renderer.ImageComponentRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.InputPanelRenderer;
import com.wpi.cs509.teamA.ui.view.renderer.ViewComponent;

import java.util.Observable;
import java.util.Observer;

/**
 * This controller will listen to the change of the model and then update the
 * view or to say, rerender the view
 * 
 * this class behaves like an "output" controller
 * 
 * when the model changes, it will decide how to rerender the component
 */
public class ViewRerenderController implements Observer {

	private static ImageComponentRenderer imageComponent = ViewComponent.getImageComponent();
	private static MainModel model = ViewComponent.getModel();
	private static InputPanelRenderer inputPanel = ViewComponent.getInputPanel();
	private static AnimationControl ac = ViewComponent.getAc();

	private static InformationPanel nodeInformation = new InformationPanel();
	private static ThumbNailPanel thumbNailPanel = new ThumbNailPanel(model);

	public ViewRerenderController() {

	}

	static public void infoPanelSlideDown() {
		AnimationObject AO = ViewRerenderController.getAC()
				.checkObjectExist(ViewRerenderController.getNodeInformation());
		if (null == AO) {
			return;
		}
		AO.switchState(new AnimationStateSlidingDown(AO));

	}

	/**
	 * Repaint the image
	 * 
	 * TODO: we can only rerender the image component
	 */
	static public void updateImageComponent() {
		imageComponent.repaint();
	}

	/**
	 * Repaint the input panel
	 * 
	 * TODO: we can only rerender the input panel
	 */
	static public void updateInputePanel() {
		inputPanel.repaint();
	}

	/**
	 * Update the all the views
	 * 
	 * We can call this method to force update or we can update the view via
	 * observe pattern
	 * 
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
	 * 
	 * we can use the first argument to check which model has been changed and
	 * then update the corresponding view
	 */
	@Override
	public void update(Observable obs, Object arg) {
		if (model != obs) {
			return;
		}
		// TODO: based on the model, we can decide which view to rerender
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
