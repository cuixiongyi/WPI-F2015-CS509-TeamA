package com.wpi.cs509.teamA.bean;

public class Edge {
	private int id1, id2; // ID's of two nodes
	private int dist; // distance between the two nodes

	public Edge(int id1, int id2, int dist) {
		this.id1 = id1;
		this.id2 = id2;
		this.dist = dist;
	}

	/**
	 * @return the id1
	 */
	public int getId1() {
		return id1;
	}

	/**
	 * @param id1
	 *            the id1 to set
	 */
	public void setId1(int id1) {
		this.id1 = id1;
	}

	/**
	 * @return the id2
	 */
	public int getId2() {
		return id2;
	}

	/**
	 * @param id2
	 *            the id2 to set
	 */
	public void setId2(int id2) {
		this.id2 = id2;
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