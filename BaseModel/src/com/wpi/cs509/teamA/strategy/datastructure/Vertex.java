package com.wpi.cs509.teamA.strategy.datastructure;

import com.wpi.cs509.teamA.bean.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class should not extends from node.
 * 
 * This class is more like a wrapper class, maybe, extends is correct
 * 
 * This is a data structure for algorithm.
 * 
 * @author JLou
 *
 */
public class Vertex extends Node implements Comparable<Vertex> {

	/**
	 * this is the distance to the source node, initially, the distance should
	 * be max_val
	 */
	private double dist = Double.MAX_VALUE;
	// this will make a linked list that returned as result
	private Vertex previous;

	/**
	 * The neighbor vertices and the distance between this vertex and its
	 * neighbor
	 */
	private Map<Vertex, Double> neighborV = new HashMap<>();

	// the next two are for a star
	private double hcost;
	private double gcost;

	public Vertex() {

	}

	public Vertex(Node node) {

		super(node.getId(), node.getLocation().getX(), node.getLocation().getY(), node.getMap());

	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(dist, other.dist);
	}

	public double getGcost() {
		return gcost;
	}

	public void setGcost(double gcost) {
		this.gcost = gcost;
	}

	/**
	 * @return the hcost
	 */
	public double getHcost() {
		return hcost;
	}

	/**
	 * @param hcost
	 *            the hcost to set
	 */
	public void setHcost(double hcost) {
		this.hcost = hcost;
	}

	/**
	 * @return the dist to the source node
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

	/**
	 * @return the previous
	 */
	public Vertex getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	/**
	 * @return the neighbors
	 */
	public Map<Vertex, Double> getNeighborV() {
		return this.neighborV;
	}

	/**
	 * @param neighbours
	 *            the neighbors to set
	 */
	public void setNeighborV(Map<Vertex, Double> neighbours) {
		this.neighborV = neighbours;
	}

}