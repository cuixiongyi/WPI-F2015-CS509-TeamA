package com.wpi.cs509.teamA.ui.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationControl.MyActionListener;
import com.wpi.cs509.teamA.util.PaintHelper;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationPathControl {

	private static Timer timer = new Timer(100, new MyActionListener());
	private static MainModel model;
	private static List<Node> path;
	private static GeneralMap currentMap;
	private static int currentNodeIndex;

	public AnimationPathControl() {

	}

	public static void init(MainModel model) {
		AnimationPathControl.model = model;
		if (model.getCurrentPath() != null) {
		path = model.getCurrentPath().getNodes();
		}
		currentMap = model.getCurrentMap();
		currentNodeIndex = 0;
		timer.start();
	}

	public static class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (model.getCurrentPath() != null) {
				path = model.getCurrentPath().getNodes();
				}
			if (path != null && null != model.getCurrentPath()) {
				if ((!model.getCurrentPath().getNodes().equals(path))
						|| (!model.getCurrentMap().equals(currentMap))) {
					currentMap = model.getCurrentMap();
					path = model.getCurrentPath().getNodes();
					currentNodeIndex = 0;
					model.setAnimationNode(path.get(currentNodeIndex));
				} else {
					if (path.size() > currentNodeIndex) {
						model.setAnimationNode(path.get(currentNodeIndex));
					} else {
						currentNodeIndex = 0;
						model.setAnimationNode(path.get(currentNodeIndex));
					}
				}
				currentNodeIndex++;
			}
		}
	}
}
