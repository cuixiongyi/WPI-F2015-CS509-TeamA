package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
//import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.GeneralAlgorithm;
import com.wpi.cs509.teamA.strategy.impl.Graph;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * This class will get the start and end nodes from the front end. The node got
 * from the front end is in String form, So this class will get a Node from
 * database based on the string it receive.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class AlgoController {

	// TODO: Make this class singleton, we use setter and getter to operate it..

	/**
	 * The start node get from front end It is a String
	 */
	private Node startNode;
	/**
	 * The destination node get from the front end It is a String
	 */
	private Node endNode;
	/**
	 * the result of the path finding
	 */
	private Stack<Node> result = new Stack<Node>();

	/**
	 * default constructor
	 */
	public AlgoController() {

	}

	/**
	 * Constructor,initiate with the source and destination
	 *
	 * @param from
	 *            the source node
	 * @param to
	 *            the destination node
	 */
	public AlgoController(Node from, Node to) {

		this.startNode = from;
		this.endNode = to;
	}

	/**
	 * This method will get source and destination from the AlgoController class
	 * and get the route use strategy pattern
	 * 
	 * @return a map data structure that the key is the map id and the value is
	 *         a list of nodes that represents the path on that map
	 */
	public Stack<Node> getRoute() {

		allEdges edges = new allEdges(Database.getAllEdges(), Database.getAllMapEdges(), startNode, endNode);
		// TODO: use singleton here..
		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();

		generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
		// generalAlgorithm.setAlgoStrategy(new AstarAlgoStrategy());
		return result = generalAlgorithm.findPath(edges);

	}

}
