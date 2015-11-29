package com.wpi.cs509.teamA.ui;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditEdge  extends MouseActionState {

    private NeighborDialog neighborDialog;

    private JButton btnNeighborManage;

    private java.util.List<Edge> edgeToAdd;

    private List<Node> nodesToPaint = new ArrayList<>();

    private Node lastNode = null;


    public MouseActionEditEdge(StateContext pStateContext) {
        super(pStateContext);

        this.btnNeighborManage = imageComponent.getInputPanel().getBtnNeighborManage();
        this.btnNeighborManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                neighborDialog = new NeighborDialog(imageComponent);
                neighborDialog.setStateContext(stateContext);
                neighborDialog.setVisible(true);
                neighborDialog.setAlwaysOnTop(true);
            }
        });

    }

    @Override
    public boolean cleanup() {

        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {

        // we have to minus 5 to correct deviation
        xPos = e.getX();
        yPos = e.getY();
        // TODO Auto-generated method stub
        // TODO this is a hack
        if (e.getButton() == MouseEvent.BUTTON1 && neighborDialog != null && neighborDialog.isVisible()) {
           // neighborDialog.setFieldTitle(xPos, yPos);
            Node node = Database.getNodeFromCoordinate(new Coordinate(xPos, yPos), stateContext.getCurrentMap().getMapId());
            if (null == node)
            {
                return false;
            }
            if (null == lastNode) {
                lastNode = node;
                nodesToPaint.add(node);

            }
            else {
                // TODO this is a hack, distance is set to -1 as default
                edgeToAdd.add(new Edge(lastNode, node ));
                //nodesToPaint.clear();
                lastNode = null;

            }
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            //
        } else if (e.getButton() == MouseEvent.BUTTON3) {

        }

        return false;
    }

    @Override
    public void paintOnImage(Graphics2D g2) {

        //TODO draw newly added edge
        PaintHelper.paintNodes(nodesToPaint, g2);
        PaintHelper.paintEdges(edgeToAdd, g2);
    }
}
