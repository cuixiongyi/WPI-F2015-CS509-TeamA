package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * This is a class that defines all the map we want. It is also the class that
 * really get the matrix from the data from database. In the other words, it
 * provides the matrix to the ProxyMap class.
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

		// TODO:get data from database here, what data we need?
		System.out.println(
				"general map is getting data from database and making a matrix.. this should happen only once.. ");
		// TODO: assign value from the database to the adjacencyMatrix, make a new Matrix
		// adjacencyMatrix = makeMatrix();

	}
	
	// TODO: assign value from the database to the adjacencyMatrix, make a new Matrix
	private InputMatrix makeMatrix(){
		
		// test..
		return new InputMatrix();
		
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

	/**
	 * @return the mapId
	 */
	public String getMapId() {
		return mapId;
	}

	/**
	 * @param mapId the mapId to set
	 */
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {
		return Scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		Scale = scale;
	}

}
