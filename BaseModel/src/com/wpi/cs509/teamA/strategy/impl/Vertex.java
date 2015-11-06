package com.wpi.cs509.teamA.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.util.Coordinate;

public class Vertex implements Comparable<Vertex> {
	private int id;
	private int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	private Vertex previous = null;
	private int hcost;
	private int gcost;


	private Map<Vertex, Integer> neighbours = new HashMap<>();
	private static List<Integer> pathNodeIds = new ArrayList<Integer>();
	private Coordinate coordinate;

	public Vertex(int id, Coordinate coordinate) {
		this.id = id;
		this.coordinate = coordinate;
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

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Integer getNext() {
		if (this == this.previous) {
			//System.out.printf("%s", this.id);
			return this.id;
		} else if (this.previous == null) {
			System.out.printf("%s(unreached)", this.id);
			return null;
		} else {
			Integer tmp = this.previous.getNext();
			if (null != tmp)
			{
				pathNodeIds.add(tmp);
			}
			
			return this.id;
		}
	}

	/*
	public void printPath() {
		if (this == this.previous) {
			System.out.printf("%s", this.id);
		} else if (this.previous == null) {
			System.out.printf("%s(unreached)", this.id);
		} else {
			this.previous.printPath();
			System.out.printf(" -> %s(%d)", this.id, this.dist);
		}
	}*/

	public int compareTo(Vertex other) {
		return Integer.compare(dist, other.dist);
	}
	
	/**
	 * @return the pathNodeIds
	 */
	public List<Integer> getPathNodeIds() {
		return pathNodeIds;
	}
	
	/**
	 * @param pathNodeIds
	 *            the pathNodeIds to set
	 */
	public void clearPathNodeIds() {
		pathNodeIds = new ArrayList<Integer>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the neighbours
	 */
	public Map<Vertex, Integer> getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours
	 *            the neighbours to set
	 */
	public void setNeighbours(Map<Vertex, Integer> neighbours) {
		this.neighbours = neighbours;
	}

}