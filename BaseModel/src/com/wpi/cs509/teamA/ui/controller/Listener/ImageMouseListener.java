package com.wpi.cs509.teamA.ui.controller.Listener;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.ui.Animation.AnimationObject;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingIn;
import com.wpi.cs509.teamA.ui.Animation.AnimationStatePattern.AnimationStateSlidingOut;
import com.wpi.cs509.teamA.ui.view.ImageComponent;
import com.wpi.cs509.teamA.ui.view.ViewManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

/**
 * Created by cuixi on 11/24/2015.
 */
public class ImageMouseListener implements MouseInputListener {


    private ImageComponent imageComponent = null;
    private MainModel model = null;

    public ImageMouseListener(ImageComponent pImageComponent, MainModel pModel) {
        this.imageComponent = pImageComponent;
        model = pModel;
        addMouseMotionListener();
        addMouseInputListener();

    }

    public void addMouseInputListener() {
        imageComponent.addMouseListener(this);
    }

    public void addMouseMotionListener() {
        imageComponent.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
//                if (e.getButton() == MouseEvent.BUTTON1) {

                    int x = imageComponent.getImageXpos();
                    int y = imageComponent.getImageYpos();
                    int x2 = imageComponent.getImageStartXpos() + e.getX() - imageComponent.getPressxPos();
                    int y2 = imageComponent.getImageStartYpos() + e.getY() - imageComponent.getPressyPos();
                    imageComponent.setImageXpos(x2);
                    imageComponent.setImageYpos(y2);
                model.getLinearTransform().setX(x2);
                model.getLinearTransform().setY(y2);
                    imageComponent.repaint();
//                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                AnimationObject ret = ViewManager.getAC().checkObjectExist(ViewManager.getThumbNailPanel());
                if (null == ret) {
                    return;
                }
                int x = e.getX();
                int y = e.getY();
                if (x < 120) {
                    ret.switchState(new AnimationStateSlidingOut(ret));
                }
                else {
                    ret.switchState(new AnimationStateSlidingIn(ret));
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        model.execute(e);

    }


    @Override
    public void mousePressed(MouseEvent e) {
//        if (e.getButton() == MouseEvent.BUTTON1) {
            imageComponent.setPressxPos(e.getX());
            imageComponent.setPressyPos(e.getY());
            imageComponent.setImageStartXpos((int)model.getLinearTransform().getX());
            imageComponent.setImageStartYpos((int)model.getLinearTransform().getY());
//        }
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
