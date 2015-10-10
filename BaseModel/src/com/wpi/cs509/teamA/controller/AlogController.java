package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.GeneralAlgorithm;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * This class will get the start and end nodes from the front end. The node got
 * from the front end is in String form, So this class will get a Node from
 * database based on the string it receive.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class AlogController {

	/**
	 * The start node get from front end It is a String
	 */
	private String startNode;
	/**
	 * The destination node get from the front end It is a String
	 */
	private String endNode;
	/**
	 * the result of the path finding
	 */
	private Map<Integer, List<Node>> result = new HashMap<Integer, List<Node>>();

	/**
	 * default constructor
	 */
	public AlogController() {

	}

	/**
	 * construtor,initiate with the source and destination
	 *
	 * @param from
	 *            the source node
	 * @param to
	 *            the destination node
	 * */
	public AlogController(String from, String to) {

		this.startNode = from;
		this.endNode = to;
	}

	/**
	 * @return the id of the solution
	 */
	public Map<Integer, List<Node>> getRoute() {

		// get the node from database
		Node fromNode = this.getNodeFromName(startNode);
		Node toNode = this.getNodeFromName(endNode);

		// get more information from the node we get
		int startMapId = fromNode.getMapId();
		int endMapId = toNode.getMapId();
		
		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();
		// decide the context of the algorithm
		// get a list of matrixes that we will use in the algorithm
		List<InputMatrix> im = this.getAlgoMatrix(startMapId, endMapId);
		
		if(im.size() == 1){
			generalAlgorithm.setAlgoStrategy(new AstarAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, im);
			return result;
			
		}else{
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, im);
			return result;
			
		}

		// decide which algorithm to use
		// walking through the same map
		/*
		 * FindRoute fr = new FindRouteImpl(); if (startMapId == endMapId) {
		 * result = fr.findRouteSameMap(fromNode, toNode); return result;
		 * 
		 * // can we have just two condition, sigleMap and Multiple Map? // find
		 * a way which is easier for programming } else if (startMapId !=
		 * endMapId && (startMapId == "0" || endMapId == "0")) { // campus to //
		 * building result = fr.findRouteMultipleMap(fromNode, toNode); return
		 * result;
		 * 
		 * } else { // building to building // ?? }
		 * 
		 * return null;
		 */

	}

	/**
	 * Gets the node that corresponding to a given location name
	 * 
	 * @param nodeName
	 *            The name of the location
	 * @return node corresponding to the location name
	 */
	private Node getNodeFromName(String nodeName) {

		// use node name to find the Node we need
		// search database?

		return new Node();

	}

	/**
	 * This method will decide how many maps will be used in this searching
	 * based on the points user inputed. 
	 * 
	 * @return
	 */
	private List<InputMatrix> getAlgoMatrix(int startMapId, int endMapId) {
		
		// test
		List<InputMatrix> testRes = new ArrayList<InputMatrix>();
		testRes.add(new InputMatrix());
		testRes.add(new InputMatrix());
		
		return testRes;
	}

	/**
	 * Gets the starting node for a route
	 * 
	 * @return starting node of the route
	 */
	public String getStartNode() {
		return startNode;
	}

	/**
	 * Sets the starting node for a route
	 * 
	 * @param startNode
	 *            starting node of the route
	 */
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	/**
	 * Gets the ending node for a route
	 * 
	 * @return ending node of the route
	 */
	public String getEndNode() {
		return endNode;
	}

	/**
	 * Sets the ending node for a route
	 * 
	 * @param ending
	 *            node of the route
	 */
	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

}
