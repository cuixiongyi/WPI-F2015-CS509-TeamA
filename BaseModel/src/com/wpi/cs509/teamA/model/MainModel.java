package com.wpi.cs509.teamA.model;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/3/2015.
 */
public class MainModel {



    private UserAccount myAccount;

    private GeneralMap currentMap = null;

    private Node startNode;
    private Node endNode;


    private List<Node> path;
    private ArrayList<ArrayList<Node>> multiMapPathLists = new ArrayList<ArrayList<Node>>();

    /**
     * if filterNodeType[i] == 1 then display that type of node
     * filterNodeType[i] == 0 don't display
     */
    private List<Integer> filterNodeType;
    public MainModel() {

        this.myAccount = new UserAccount();
        this.currentMap = null;

    }




    /**
     * setter and getters
     *
     */

    public UserAccount getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(UserAccount myAccount) {
        this.myAccount = myAccount;
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

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
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

    public void setMultiMapPathLists(ArrayList<ArrayList<Node>> multiMapPathLists) {
        this.multiMapPathLists = multiMapPathLists;
    }

    public List<Integer> getFilterNodeType() {
        return filterNodeType;
    }

    public void setFilterNodeType(List<Integer> filterNodeType) {
        this.filterNodeType = filterNodeType;
    }

}
