package com.wpi.cs509.teamA.strategy.datastructure;

import java.util.ArrayList;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;

/**
 * All node nodes consists of a path in a certain map. This class is intended
 * for searching result
 * 
 * @author teama
 */
public class Path {

	// the map of the path
	private GeneralMap map;
	// a list of nodes that represent the path
	private ArrayList<Node> nodes = new ArrayList<Node>();

	public Path() {

	}

	public Path(GeneralMap pmap, ArrayList<Node> pnodes) {
		map = pmap;
		nodes = pnodes;
	}

	public void addNode(Node node) {
		if (null == nodes) {
			nodes = new ArrayList<Node>();
		}
		nodes.add(node);
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
