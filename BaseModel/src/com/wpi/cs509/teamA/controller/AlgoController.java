package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.InitAllMatrix;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixImpl;
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
public class AlgoController {

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
	public AlgoController() {

	}

	/**
	 * construtor,initiate with the source and destination
	 *
	 * @param from
	 *            the source node
	 * @param to
	 *            the destination node
	 */
	public AlgoController(String from, String to) {

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

		// decide the context of the algorithm
		// get a list of matrixes that we will use in the algorithm
		// System.out.println("The system begin to get the matrix resource..");
		List<InputMatrix> im = this.getAlgoMatrix(startMapId, endMapId);
		// System.out.println("The system has successfully get the matrix
		// resource..");

		// the algorithm strategy is not set here, we can add it later
		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();

		if (im.size() == 1) {
			generalAlgorithm.setAlgoStrategy(new AstarAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, im);
			return result;

		} else {
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, im);
			return result;

		}
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

		// Initialize all the matrix
		// we can initialize it in a much more earlier phase of the system
		Map<Integer, InputMatrix> allMatrixes = InitAllMatrixImpl.getInitAllMatrixImpl().initAllMatrix();

		// TODO: find the maps we need from the allMatrixes and return a list of matrix that we want

		// test only
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
	 * @param endNode
	 *            end node of the route
	 */
	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

}