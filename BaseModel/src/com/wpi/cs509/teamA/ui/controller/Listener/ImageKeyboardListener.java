package com.wpi.cs509.teamA.ui.controller.Listener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.view.InputPanel;
import com.wpi.cs509.teamA.util.PaintHelper;

public class ImageKeyboardListener {
	private MainModel model;

	public void printRouteKeyboardListener() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {

			public boolean postProcessKeyEvent(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if (e.isAltDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P) {
				
					for (GeneralMap mp : model.getMultiMapLists()) {
						
						PaintHelper.printRoute(mp);

					}
					
				}
				return false;
			}
		});
	}

	/**
	 * @param inputPanel
	 *            the inputPanel to set
	 */
	public void setModel(MainModel model) {
		this.model = model;
	}
}
