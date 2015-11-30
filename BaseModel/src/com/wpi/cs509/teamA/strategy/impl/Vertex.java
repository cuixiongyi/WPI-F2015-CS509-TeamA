package com.wpi.cs509.teamA.strategy.impl;
import com.wpi.cs509.teamA.bean.*;

import java.util.HashMap;
import java.util.Map;




public class Vertex extends Node implements Comparable<Vertex> {
	private GeneralMap map;
	private double dist = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
	private Vertex previous = null;
	private double hcost;
	private double gcost;

	private Map<Vertex, Double> neighborV = new HashMap<>();
	public Vertex(){
		
	}
	
	public Vertex(Node node) {
		this.id = node.getId();
		this.location = node.getLocation();
		this.map=node.getMap();;
	}

	public GeneralMap getMap() {
		return map;
	}


	public void setMap(GeneralMap map) {
		this.map = map;
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
	 * @param hcost the hcost to set
	 */
	public void setHcost(double hcost) {
		this.hcost = hcost;
	}



	public int compareTo(Vertex other) {
		return Double.compare(dist, other.dist);
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