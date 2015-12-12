package com.wpi.cs509.teamA.ui.controller.Listener;

import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class ImageKeyboardListener {
	 public void mousePressed(MouseEvent e) {
		  KeyboardFocusManager manager =  KeyboardFocusManager.getCurrentKeyboardFocusManager();
		    manager.addKeyEventPostProcessor(new KeyEventPostProcessor(){

				public boolean postProcessKeyEvent(KeyEvent e) {
					// TODO Auto-generated method stub

			        if(e.isAltDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P){
			    	    
					return false;
				}
					return false;
				}    	
		    });
	    }
	  
   }
