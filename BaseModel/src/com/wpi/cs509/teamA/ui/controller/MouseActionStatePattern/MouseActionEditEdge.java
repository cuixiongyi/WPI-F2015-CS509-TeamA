package com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.model.MouseActionState;
import com.wpi.cs509.teamA.model.StateContext;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.PaintHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by xiongyi on 11/23/15.
 */
public class MouseActionEditEdge  extends MouseActionState {

    private JButton btnNeighborManage;

    private java.util.List<Edge> edgeToAdd = new ArrayList<Edge>();

    private List<Node> nodesToPaint = new ArrayList<>();

    private Node lastNode = null;

    public MouseActionEditEdge(StateContext pSC) {
        super(pSC);


        model.cleanUpRoute();
    }

    @Override
    public boolean cleanup() {


        return false;
    }

    @Override
    public boolean execute(MouseEvent e) {
        /// update coor and coorTrans
        super.execute(e);

        // we have to minus 5 to correct deviation
        xPos = e.getX();
        yPos = e.getY();
        // TODO Auto-generated method stub
        // TODO this is a hack
        if (e.getButton() == MouseEvent.BUTTON1 ) {//&& neighborDialog != null && neighborDialog.isVisible()) {
           // neighborDialog.setFieldTitle(xPos, yPos);
            Node node = Database.getNodeFromCoordinate(coorTrans, model.getCurrentMap().getMapId());
            if (null == node)
            {
                return false;
            }
            System.out.println(node.getId());
            if (null == lastNode) {
                lastNode = node;
                nodesToPaint.add(node);

            }
            else {
                // TODO this is a hack, distance is set to -1 as default
                edgeToAdd.add(new Edge(lastNode, node ));
                nodesToPaint.add(node);
                NodeRelationDao nrd = new NodeRelationDaoImpl();
                nrd.deleteOrAddEdge(lastNode, node);
                Database.InitFromDatabase();
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

        PaintHelper.paintNodes(model.getCurrentMap().getNodes(),
                g2, PaintHelper.DrawStyleEnum.BasicNode);
        PaintHelper.paintEdges(model.getCurrentMap().getEdges(),
                g2, PaintHelper.DrawStyleEnum.BasicEdge);
        if (null != lastNode && model.getCurrentMap().getMapId() == lastNode.getMap().getMapId()) {
            PaintHelper.paintNode(lastNode, g2, PaintHelper.DrawStyleEnum.SelectedNode);
        }
    }
}
