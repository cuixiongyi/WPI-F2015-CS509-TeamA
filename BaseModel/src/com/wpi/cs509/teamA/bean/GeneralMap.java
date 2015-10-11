package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * This is a class that defines all the map we want
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */

public class GeneralMap implements AdjacencyMatrix {

	/**
	 * the map id
	 */
	private String mapId;
	/**
	 * Map name
	 */
	private String mapName;
	/**
	 * the map scale
	 */
	private int Scale;
	/**
	 * the adjacency matrix of this map
	 */
	private InputMatrix adjacencyMatrix;

	/**
	 * Default constructor
	 */
	public GeneralMap() {

	}

	/**
	 * Constructor. The ProxyMap will use this constructor to create a new
	 * instance of the map, also use this instance's getAdjacencyMatrix() method
	 * to get the adjacency matrix. The constructor should be responsible for
	 * getting data from the database and then generate a adjacency matrix.
	 * 
	 * @param mapName
	 *            the name of the map in the database
	 */
	public GeneralMap(String mapName) {

		// get data from database here
		System.out.println(
				"general map is getting data from database and making a matrix.. this should happen only once.. ");
		// assign value to the adjacencyMatrix

	}

	@Override
	/**
	 * the implementation of getting an adjacency matrix, this method will
	 * return a Adjacency Matrix that created in the initialization time.
	 */
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return adjacencyMatrix;
	}

	/** map id getter */
	public String getMapId() {
		return mapId;
	}

	/** map id setter */
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/** map name getter */
	public String getMapName() {
		return mapName;
	}

	/** map name setter */
	public void setMapName(String mapName) {
		this.mapName = mapName;
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
