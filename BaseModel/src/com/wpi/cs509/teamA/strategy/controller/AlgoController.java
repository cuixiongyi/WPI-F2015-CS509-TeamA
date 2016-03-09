package com.wpi.cs509.teamA.strategy.controller;

import java.util.ArrayList;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.*;
import com.wpi.cs509.teamA.util.Database;

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
	private AllEdges edges;
	private boolean flag = false;
	boolean isMultipleDestination = false;

	public AlgoController(Node from, Node to) {

	}

	public AlgoController(Node from, ArrayList<Node> to) {
		if (to.size() == 1) {
			edges = new AllEdges(Database.getAllEdges(), Database.getAllMapEdges(), from, to.get(0));
			flag = true;
		}
	}

	public AlgoController(Node from, ArrayList<Node> to, boolean isMultiopleDestination) {
		Node[] end = new Node[to.size()];
		to.toArray(end);
		// TODO: this is very error prone to init from node here in all edges
		// but not the algo itself
		edges = new AllEdges(Database.getAllEdges(), Database.getAllMapEdges(), from, end);
		this.isMultipleDestination = true;
	}

	/**
	 * This method will get source and destination from the AlgoController class
	 * and get the route use strategy pattern
	 * 
	 * @return a map data structure that the key is the map id and the value is
	 *         a list of nodes that represents the path on that map
	 */
	public Stack<Node> getRoute() {

		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();

		// this is legacy code for the first iteration
		// leave here for memory
		if (flag) {
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());

			result = generalAlgorithm.findPath(edges);
			return result;
		}

		if (this.isMultipleDestination) {
			generalAlgorithm.setAlgoStrategy(new MultipleDestinations());
			this.isMultipleDestination = false;
		} else {
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
		}

		return result = generalAlgorithm.findPath(edges);

	}

}
