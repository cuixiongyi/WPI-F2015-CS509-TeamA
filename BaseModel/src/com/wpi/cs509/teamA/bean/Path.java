package com.wpi.cs509.teamA.bean;

import java.util.ArrayList;

/**
 * Created by cuixi on 12/13/2015.
 */
public class Path {
    GeneralMap map = null;
    ArrayList<Node> nodes = null;

    Path(GeneralMap pmap, ArrayList<Node> pnodes) {
        map = pmap;
        nodes = pnodes;
    }


    public GeneralMap getMap() {
        return map;
    }

    public void setMap(GeneralMap map) {
        this.map = map;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }
}
