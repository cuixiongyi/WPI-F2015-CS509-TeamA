package com.wpi.cs509.teamA.dao;

import java.util.Map;

import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * 
 * An interface defines what method will needed to get all the matrix
 * initialized
 * 
 * @author CS 509-Team A
 *
 */

@Deprecated
public interface InitAllMatrixDao {

	/**
	 * get all the matrix for every single map
	 * 
	 * @return a map data structure that takes the id of the map as key and the
	 *         its corresponding matrix as the value
	 */
	public Map<Integer, InputMatrix> getAllInitializedMatrix();
}
