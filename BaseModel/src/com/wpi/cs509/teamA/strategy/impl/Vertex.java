package com.wpi.cs509.teamA.strategy.impl;
import com.wpi.cs509.teamA.bean.*;

import java.util.HashMap;
import java.util.Map;




public class Vertex extends Node implements Comparable<Vertex> {
	private GeneralMap map;
	private int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	private Vertex previous = null;
	private int hcost;
	private int gcost;

	private Map<Vertex, Integer> neighborV = new HashMap<>();
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
	
	public int getGcost() {
		return gcost;
	}

	public void setGcost(int gcost) {
		this.gcost = gcost;
	}
	/**
	 * @return the hcost
	 */
	public int getHcost() {
		return hcost;
	}

	/**
	 * @param hcost the hcost to set
	 */
	public void setHcost(int hcost) {
		this.hcost = hcost;
	}



	public int compareTo(Vertex other) {
		return Integer.compare(dist, other.dist);
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
	public Map<Vertex, Integer> getNeighborV() {
		return this.neighborV;
	}

	/**
	 * @param neighbours
	 *            the neighbors to set
	 */
	public void setNeighborV(Map<Vertex, Integer> neighbours) {
		this.neighborV = neighbours;
	}

}