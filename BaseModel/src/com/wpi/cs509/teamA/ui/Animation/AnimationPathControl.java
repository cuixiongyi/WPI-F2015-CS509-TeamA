package com.wpi.cs509.teamA.ui.Animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.model.MainModel;

/**
 * Created by cuixi on 12/10/2015.
 */
public class AnimationPathControl {

	private static Timer timer = null;
	private static MainModel model;
	private static Path path;
	private static int currentNodeIndex;

	public AnimationPathControl() {

	}

	public static void init(MainModel model) {
		AnimationPathControl.model = model;
		path = null;
		currentNodeIndex = 0;
        timer = new Timer(400, new MyActionListener());
		timer.start();
	}

	public static class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Path currPath = model.getCurrentPath();
			if (null == currPath) {
				model.setAnimationNode(null);
				AnimationPathControl.path = null;
                return;
			}

			if (model.getCurrentMap().equals(currPath.getMap())) {
				if ((AnimationPathControl.path == null))
				{
					AnimationPathControl.path = currPath;
					currentNodeIndex = 0;
				}
				else if (!(currPath.equals(AnimationPathControl.path)) && model.getCurrentMap().equals(model.getCurrentMap())) {
					AnimationPathControl.path = model.getCurrentPath();
					currentNodeIndex = 0;
				}
			}
            else {
                AnimationPathControl.path = null;
                model.setAnimationNode(null);
            }

			if (AnimationPathControl.path != null) {
				model.setAnimationNode(AnimationPathControl.path.getNodes().get(currentNodeIndex));
				if (++currentNodeIndex >= currPath.getNodes().size()) {
					currentNodeIndex = 0;
				}
			}
		}
	}
}
