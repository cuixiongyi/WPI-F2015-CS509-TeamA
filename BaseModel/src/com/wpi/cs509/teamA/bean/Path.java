package com.wpi.cs509.teamA.bean;

import java.util.ArrayList;

/**
 * All node nodes consists of a path in a certain map
 * 
 * @author teama
 */
public class Path {
	private GeneralMap map;
	private ArrayList<Node> nodes = new ArrayList<Node>();

	public Path(GeneralMap pmap, ArrayList<Node> pnodes) {
		map = pmap;
		nodes = pnodes;
	}

	public Path() {

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
