package com.wpi.cs509.teamA.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

/**
 * Created by cuixi on 11/24/2015.
 */
public class ImageMouseListener implements MouseInputListener {



    private StateContext stateContext;

    public ImageMouseListener() {
        this.stateContext = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        stateContext.execute(e);
    }


    @Override
    public void mousePressed(MouseEvent e) {
    	
    	stateContext.getImageComponent().setPressxPos(e.getX());
    	stateContext.getImageComponent().setPressyPos(e.getY());
    	stateContext.getImageComponent().setImageStartXpos(stateContext.getImageComponent().getImageXpos());
    	stateContext.getImageComponent().setImageStartYpos(stateContext.getImageComponent().getImageYpos());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



    public void setStateContext(StateContext stateContext) {
        this.stateContext = stateContext;
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
//		stateContext.getImageComponent().setImageXpos(stateContext.getImageComponent().getImageStartXpos()+  e.getX()-stateContext.getImageComponent().getPressxPos());
//		stateContext.getImageComponent().setImageYpos(stateContext.getImageComponent().getImageStartYpos()+  e.getY()-stateContext.getImageComponent().getPressyPos());
//		stateContext.getImageComponent().repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
