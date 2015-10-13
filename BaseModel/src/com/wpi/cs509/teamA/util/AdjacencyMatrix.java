package com.wpi.cs509.teamA.util;

/**
 * This is the interface for the Map Proxy pattern. It has a
 * getAdjacencyMatrix() method. Two systems, the General Map part which can get
 * data from database and the ProxyMap class that use that method, will use the
 * same method and make the resources be initialized only once.
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
