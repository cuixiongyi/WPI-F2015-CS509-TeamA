package com.wpi.cs509.teamA.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * This is the general algorithm for the strategy pattern. This is the class
 * that we can set our strategy.
 * 
 * 
 * @author CS 509-Team A
 *
 */
public class GeneralAlgorithm {

	// TODO: Make this class singleton..

	/**
	 * the strategy will be applied on the path finding
	 */
	private AlgoStrategy algoStrategy;

	/**
	 * default constructor
	 */
	public GeneralAlgorithm() {

	}

	/**
	 * Constructor.
	 * 
	 * @param algoStrategy
	 *            the strategy will be applied, usually should be the default
	 *            strategy
	 */
	public GeneralAlgorithm(AlgoStrategy algoStrategy) {
		this.algoStrategy = algoStrategy;
	}

	/**
	 * 
	 * @param startNode
	 *            the source
	 * @param endNode
	 *            the destination
	 * @param im
	 *            a list of matrix that will be used in the path finding
	 * @return a map data structure that takes the id of the map as key and the
	 *         its corresponding matrix as the value
	 */
	public Map<Integer, List<Node>> findPath(Node startNode, Node endNode, List<InputMatrix> im) {

		System.out.println("General Algorithm..");

		Map<Integer, List<Node>> result = new HashMap<Integer, List<Node>>();
		// add this one by one to the result
		List<Node> routeOnOneMap = new ArrayList<Node>();

		// different strategy will use different algorithm..
		result = algoStrategy.getRoute(startNode, endNode, im);

		return result;
	}

	/**
	 * @return the algoStrategy
	 */
	public AlgoStrategy getAlgoStrategy() {
		return algoStrategy;
	}

	/**
	 * @param algoStrategy
	 *            the algoStrategy to set
	 */
	public void setAlgoStrategy(AlgoStrategy algoStrategy) {
		this.algoStrategy = algoStrategy;
	}

}
