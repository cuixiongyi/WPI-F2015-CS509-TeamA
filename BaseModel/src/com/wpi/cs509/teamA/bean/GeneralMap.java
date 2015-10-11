package com.wpi.cs509.teamA.bean;

import java.util.Set;

import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * General map definition
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public class GeneralMap implements AdjacencyMatrix {

	/** the map id */
	private String mapId;
	/** the map scale */
	private int Scale;

	/** the places/nodes that can arrive in the map */
	private Set<Node> GeneralMap;

	public GeneralMap() {

	}

	@Override
	/**
	 * the implementation of getting an adjacency matrix
	 */
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub

		System.out.println("this method is from general map!");

		return null;
	}

	/** add node to the map */
	protected void addNode(String nodeId) {

	}

	/**
	 * delete node from the map set
	 * 
	 * @param nodeId
	 *            the id of the node
	 */
	protected void deleteNode(String nodeId) {
		// this.deleteNode();
	}

	/** map id getter */
	public String getMapId() {
		return mapId;
	}

	/** map id setter */
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the generalMap
	 */
	public Set<Node> getGeneralMap() {
		return GeneralMap;
	}

	/**
	 * @param generalMap
	 *            the generalMap to set
	 */
	public void setGeneralMap(Set<Node> generalMap) {
		GeneralMap = generalMap;
	}

	/** map scale getter */
	public int getScale() {
		return Scale;
	}

	/** map scale setter */
	public void setScale(int scale) {
		Scale = scale;
	}

}
