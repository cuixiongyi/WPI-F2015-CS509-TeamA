package com.wpi.cs509.teamA.util;

public class AdjacencyMatrix {

	private AdjacencyMatrix() {
	}

	public static AdjacencyMatrix getInitializedMatrix() {
		if (aMatrix == null) {
			aMatrix = new AdjacencyMatrix();
		}
		
		return aMatrix;
	}

	private static AdjacencyMatrix aMatrix;

}
