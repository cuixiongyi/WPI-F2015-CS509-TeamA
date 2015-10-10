package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.Node;

public interface AdjacencyMatrix {
	
	/**
	 * This method will get an Matrix for Dijkstra as input
	 * @return a 2D array that contains all the Node in it
	 */
	public InputMatrix getAdjacencyMatrix();

}
