package com.wpi.cs509.teamA.ui.controller.Listener;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;

import com.wpi.cs509.teamA.util.PaintHelper;

public class ImageKeyboardListener {
	private MainModel model;

	public ImageKeyboardListener(MainModel model2) {
		this.model = model2;
		printRouteKeyboardListener();
	}

	public void printRouteKeyboardListener() {

		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

		manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {

			public boolean postProcessKeyEvent(KeyEvent e) {
				// TODO Auto-generated method stub
				int i = 0;
				if (e.isAltDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
					System.out.println("start");
					System.out.println(model.getMultiMapLists().size());
					for (GeneralMap mp : model.getMultiMapLists()) {
						
					//	System.out.println(mp.getMapName());
						
						PaintHelper.printRoute(mp, mp.getImage());
						

					}
				}
				return false;
			}
		});
	}

}