package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by xiongyi on 11/23/15.
 *
 * This is the abstract class for state pattern
 */
public abstract class MouseActionState {

    protected StateContext stateContext;

    protected int xPos;
    protected int yPos;

    protected ImageComponent imageComponent;



    protected List<Node> nodesToPaint;


    public MouseActionState(StateContext pStateContext) {
        this.stateContext = pStateContext;
        this.xPos = -1;
        this.yPos = -1;
        this.imageComponent = pStateContext.getImageComponent();
        this.nodesToPaint = null;
    };

    abstract public void paintOnImage(Graphics2D g2);

    abstract public boolean execute(MouseEvent e) ;

    abstract public boolean cleanup();

    public List<Node> getNodesToPaint() {
        return nodesToPaint;
    }

    public void setNodesToPaint(List<Node> nodesToPaint) {
        this.nodesToPaint = nodesToPaint;
    }
}
