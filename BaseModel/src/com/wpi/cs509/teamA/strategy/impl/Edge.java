package com.wpi.cs509.teamA.strategy.impl;

public class Edge {

	// ID's of two nodes
	private int id1, id2;
	// distance between the two nodes
	private double dist;

	/**
	 * 
	 */
	public Edge() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @param dist
	 */
	public Edge(int id1, int id2, double dist) {
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

}