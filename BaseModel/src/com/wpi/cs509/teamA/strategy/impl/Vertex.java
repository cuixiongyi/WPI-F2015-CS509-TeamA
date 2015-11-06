package com.wpi.cs509.teamA.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {
	private int id;
	private double dist = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
	private Vertex previous = null;
	private Map<Vertex, Double> neighbours = new HashMap<>();
	private static List<Integer> pathNodeIds = new ArrayList<Integer>();

	public Vertex(int id) {
		this.id = id;
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
		return Double.compare(dist, other.dist);
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
	 * @return the neighbours
	 */
	public Map<Vertex, Double> getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours
	 *            the neighbours to set
	 */
	public void setNeighbours(Map<Vertex, Double> neighbours) {
		this.neighbours = neighbours;
	}

}