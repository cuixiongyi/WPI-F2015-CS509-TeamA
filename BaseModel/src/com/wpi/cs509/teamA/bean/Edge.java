package com.wpi.cs509.teamA.bean;

public class Edge {
	private Node node1, node2;
	private double dist; // distance between the two nodes

	public Edge(){
		
	}
	public Edge(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
		this.dist = node1.DistanceTo(node2);
	}
	
	//for then two nodes are from different maps
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
	
	public Node getTheOtherNode(Node n){
		if (n.id==this.node1.id) return this.node1;
		return this.node2;
	}

}

