package com.wpi.cs509.teamA.strategy.datastructure;

import com.wpi.cs509.teamA.bean.Node;

/**
 * 
 * For algo
 * 
 * An Edge class consists of 2 node and the distance
 * 
 * @author JLou
 *
 */
public class Edge {

	private Node node1;
	private Node node2;

	// distance between the two nodes
	private double dist;

	public Edge() {

	}

	public Edge(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
		this.dist = node1.DistanceTo(node2);
	}

	/**
	 * for those two nodes from different maps
	 * 
	 * @param node1
	 * @param node2
	 * @param dist
	 */
	public Edge(Node node1, Node node2, double dist) {
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
	 * @param node1
	 *            the node1 to set
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
	 * @param node2
	 *            the node2 to set
	 */
	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	/**
	 * @return the dist between two nodes
	 */
	public double getDist() {
		return dist;
	}

	/**
	 * @param dist
	 *            the dist to set
	 */
	public void setDist(double dist) {
		this.dist = dist;
	}

	public Node getTheOtherNode(Node n) {
		if (n.getId() == this.node1.getId())
			return this.node2;
		return this.node1;
	}

}
