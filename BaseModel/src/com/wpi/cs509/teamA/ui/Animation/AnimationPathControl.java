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

	private static Timer timer = new Timer(400, new MyActionListener());
	private static MainModel model;
	private static List<Node> path;
	private static GeneralMap map;
	private static int currentNodeIndex;

	public AnimationPathControl() {

	}

	public static void init(MainModel model) {
		AnimationPathControl.model = model;
		path = null;
		map = null;
		currentNodeIndex = 0;
		timer.start();
	}

	public static class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (model.getCurrentPath() != null) {
				if ((path == null) || (map == null))
				{
					path = model.getCurrentPath().getNodes();
					map = model.getCurrentMap();
					currentNodeIndex = 0;
				}
				
				else if ((!model.getCurrentPath().getNodes().equals(path)) || (!model.getCurrentMap().equals(map))) {
					path = model.getCurrentPath().getNodes();
					map = model.getCurrentMap();
					currentNodeIndex = 0;
				}
			}

			if (path != null) {
				model.setAnimationNode(path.get(currentNodeIndex));
				if (++currentNodeIndex >= path.size()) {
					currentNodeIndex = 0;
				}
			}
		}
	}
}
