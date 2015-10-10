package com.wpi.cs509.teamA.util;

public class AdjacencyMatrix {
	
	private static AdjacencyMatrix aMatrix;
	
	// add some fields here that need to get from the matrix

	private AdjacencyMatrix() {
	}

	public static AdjacencyMatrix getInitializedMatrix() {
		if (aMatrix == null) {
			aMatrix = new AdjacencyMatrix();
		}
		
		return aMatrix;
	}
	
}
