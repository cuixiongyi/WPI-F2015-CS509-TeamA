package com.wpi.cs509.teamA.strategy.controller;

import java.util.ArrayList;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.model.AlgoModel;
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

	// all the edges including the edges that cross the maps
	private AlgoModel algoModel;
	private boolean isMultipleDestination = false;

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
	 *            the destination node, maybe a multiple destination one
	 */
	public AlgoController(Node from, ArrayList<Node> to, boolean isMultiopleDestination) {

		if (isMultiopleDestination) {

			Node[] end = new Node[to.size()];
			to.toArray(end);
			algoModel = new AlgoModel(Database.getAllEdges(), Database.getAllMapEdges(), from, end);
			this.isMultipleDestination = isMultiopleDestination;

		} else {

			algoModel = new AlgoModel(Database.getAllEdges(), Database.getAllMapEdges(), from, to.get(0));
			this.isMultipleDestination = isMultiopleDestination;

		}

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

		if (this.isMultipleDestination) {
			generalAlgorithm.setAlgoStrategy(new MultipleDestinations());
		} else {
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
		}

		return generalAlgorithm.findPath(algoModel);

	}

}
