package com.wpi.cs509.teamA.strategy.impl;

import com.wpi.cs509.teamA.bean.Node;

public class Edge {
	private Node node1, node2;
	private int dist; // distance between the two nodes

	public Edge(Node node1, Node node2, int dist) {
		this.node1 = node1;
		this.node2 = node2;
		this.dist = dist;
	}
	
	/**
	 * @return the node1
	 */
	public Node getNode1() {
		return node1;
	}

	/**
	 * @param node1 the node1 to set
	 */
	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	/**
	 * @return the node2
	 */
	public Node getNode2() {
		return node2;
	}

	/**
	 * @param node2 the node2 to set
	 */
	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	/**
	 * @return the dist
	 */
	public int getDist() {
		return dist;
	}

	/**
	 * @param dist
	 *            the dist to set
	 */
	public void setDist(int dist) {
		this.dist = dist;
	}

}