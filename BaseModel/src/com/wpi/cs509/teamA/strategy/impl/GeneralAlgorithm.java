package com.wpi.cs509.teamA.strategy.impl;

import java.util.Stack;

import com.wpi.cs509.teamA.controller.allEdges;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;

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
	 * 
	 * 
	 * @param startNode
	 * @param endNode
	 * @param context
	 * @return
	 */
	public Stack<Node> findPath(allEdges edges) {

		Stack<Node> result = new Stack<Node>();

		// different strategy will use different algorithm..
		result = algoStrategy.getRoute(edges);

//		// TODO: Query node objects from node Id's and add all node objects to
//		// result
//		NodeDao nd = new NodeDaoImpl();
//		// add this one by one to the result
//		List<Node> routeOnOneMap = nd.getNodeFromIds(nodeIds);
//
//		// 
//		result.put(UIDataBuffer.getCurrentMapId(), routeOnOneMap);

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
