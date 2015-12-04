package com.wpi.cs509.teamA.model;

import com.sun.org.apache.bcel.internal.generic.DADD;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/3/2015.
 */
public final class MainModel extends StateContext{



    private UserAccount myAccount;
    private GeneralMap currentMap = null;

    private Node startNode;
    private Node endNode;


    private Node focusNode;

    private ArrayList<ArrayList<Node>> multiMapPathLists = null;

    private boolean hasValidRoute = false;

    /**
     * if filterNodeType[i] == 1 then display that type of node
     * filterNodeType[i] == 0 don't display
     */
    private List<Integer> filterNodeType;
    public MainModel() {

        this.myAccount = new UserAccount();
        this.currentMap = null;
//        multiMapPathLists = new ArrayList<ArrayList<Node>>();
        hasValidRoute = false;
        setCurrentMapID(1);

    }

    public void cleanUpRoute() {
        this.setStartNode(null);
        this.setEndNode(null);
        this.setMultiMapPathLists(null);
        this.setHasValidRoute(false);
    }


    /**
     * setter and getters
     *
     */

    public UserAccount getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(UserAccount pAccount) {
        if (null == pAccount) {
            pAccount = new UserAccount();
        }
        this.myAccount = pAccount;
    }

    public void setFocusToNode(Node node ) {
        if (null == node) {
            return;
        }
        setCurrentMap(node.getMap());
        this.focusNode = node;

    }
    public int getCurrentMapID() {
        return currentMap.getMapId();

    }
    public GeneralMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(GeneralMap currentMap) {
        this.currentMap = currentMap;
    }
    public void setCurrentMapID(int mapID) {
        this.currentMap = Database.getMapEntityFromMapId(mapID);
    }

    public Node getFocusNode() {
        return this.focusNode;
    }


    public Node getStartNode() {
        return startNode;
    }


    public void setStartNode(Node pStartNode) {
        if (pStartNode == this.startNode) {
            return;
        }
        this.multiMapPathLists = new ArrayList<ArrayList<Node>>();

        this.startNode = pStartNode;
    }


    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public ArrayList<ArrayList<Node>> getMultiMapPathLists() {
        return multiMapPathLists;
    }

    public void setMultiMapPathLists(ArrayList<ArrayList<Node>> pMultiMapPathLists) {
        List<GeneralMap> maps = Database.getAllMapFromDatabase();
        this.multiMapPathLists = new ArrayList<ArrayList<Node>>();

        for (int ii = 1; ii <= maps.size(); ++ii) {
            ArrayList<Node> path = new ArrayList<Node>();
            int idx = -1;
            for (int jj = 0; jj <  pMultiMapPathLists.size(); ++jj) {
                if (pMultiMapPathLists.get(jj).get(0).getMap().getMapId() == ii) {
                    idx = jj;
                    break;
                }
            }
            if (-1 != idx) {
                path = pMultiMapPathLists.get(idx);
            }
            this.multiMapPathLists.add(path);
        }
    }

    public List<Integer> getFilterNodeType() {
        return filterNodeType;
    }

    public void setFilterNodeType(List<Integer> filterNodeType) {
        this.filterNodeType = filterNodeType;
    }



    public boolean isHasValidRoute() {
        return hasValidRoute;
    }

    private void setHasValidRoute(boolean hasValidRoute) {
        this.hasValidRoute = hasValidRoute;
    }

    public void saveNode(Node node) {
        NodeDao nd = new NodeDaoImpl();
        nd.saveNode(node);
        Database.InitFromDatabase();

    }

    public void editNode(Node node) {

        NodeDaoImpl dao = new NodeDaoImpl();
        dao.editNode(node);
        Database.InitFromDatabase();
    }

}
