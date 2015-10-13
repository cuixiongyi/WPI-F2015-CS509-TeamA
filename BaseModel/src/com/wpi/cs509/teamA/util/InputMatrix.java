package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.Node;

/**
 * This class contains information for a map matrix This is only a data
 * structure that hold a matrix's information it should not be considered as an
 * entity
 * 
 * @author CS 509-Team A
 *
 */
public class InputMatrix {

	/**
	 * The number of vertexes this matrix has
	 */
	private int numVertex;
	/**
	 * return the matrix as an 2D array
	 */
	private Node adjacentMatrix[][];

	/**
	 * Default constructor
	 */
	public InputMatrix() {

		// get the numVertex first
		// Node adjacentMatrix [][] = new Node[numVertex][numVertex];
	}

	/**
	 * Constructor
	 * 
	 * @param numVertex
	 *            number of vertex in the matrix
	 * @param adjMatrix
	 *            the adjMatrix we want
	 */
	public InputMatrix(int numVertex, Node[][] adjMatrix) {
		this.numVertex = numVertex;
		this.adjacentMatrix = adjMatrix;

	}

	/**
	 * @return the numVertex
	 */
	public int getNumVertex() {
		return numVertex;
	}

	/**
	 * @return the adjacentMatrix
	 */
	public Node[][] getAdjacentMatrix() {
		return adjacentMatrix;
	}

	/**
	 * @param numVertex
	 *            the numVertex to set
	 */
	public void setNumVertex(int numVertex) {
		this.numVertex = numVertex;
	}

	/**
	 * @param adjacentMatrix
	 *            the adjacentMatrix to set
	 */
	public void setAdjacentMatrix(Node[][] adjacentMatrix) {
		this.adjacentMatrix = adjacentMatrix;
	}

}
