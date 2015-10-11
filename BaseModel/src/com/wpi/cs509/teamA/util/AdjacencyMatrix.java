package com.wpi.cs509.teamA.util;

/**
 * This is the interface for the Map Proxy pattern.
 * 
 * @author CS 509-Team A
 *
 */
public interface AdjacencyMatrix {

	/**
	 * This method will get an Matrix for Dijkstra as input
	 * 
	 * @return an InputMatrix data structure that contains all the Node in it
	 */
	public InputMatrix getAdjacencyMatrix();

}
