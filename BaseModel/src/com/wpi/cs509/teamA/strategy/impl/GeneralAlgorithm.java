package com.wpi.cs509.teamA.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.UIDataBuffer;

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
	public Map<Integer, List<Node>> findPath(Node startNode, Node endNode, Graph context) {

		Map<Integer, List<Node>> result = new HashMap<Integer, List<Node>>();

		// different strategy will use different algorithm..
		List<Integer> nodeIds = algoStrategy.getRoute(startNode, endNode, context);

		// TODO: Query node objects from node Id's and add all node objects to
		// result
	//	NodeDao nd = new NodeDaoImpl();
		// add this one by one to the result
		List<Node> routeOnOneMap = Database.getNodeFromIds(nodeIds);
		result.put(UIDataBuffer.getCurrentMapId(), routeOnOneMap);

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
