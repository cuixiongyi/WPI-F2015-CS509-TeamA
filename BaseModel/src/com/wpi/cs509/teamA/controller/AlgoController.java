package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.GeneralAlgorithm;
import com.wpi.cs509.teamA.strategy.impl.Graph;
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
	 * Constructor,initiate with the source and destination
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
	 * This method will get source and destination from the AlgoController class
	 * and get the route use strategy pattern
	 * 
	 * @return a map data structure that the key is the map id and the value is
	 *         a list of nodes that represents the path on that map
	 */
	public Map<Integer, List<Node>> getRoute() {

		// we support searching node now only..

		// get the node from database
		Node fromNode = this.getNodeFromName(startNode);
		Node toNode = this.getNodeFromName(endNode);

		// use this two to decide how which maps are involved in searching..
		int startMapId = fromNode.getMapId();
		int endMapId = toNode.getMapId();

		// get all edges from database..
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		Edge[] inputEdges = new Edge[nrd.getNodeRelationNum()];
		Set<Edge> edges = nrd.getAllEdges();
		int temp = 0;
		for (Edge edge : edges) {
			inputEdges[temp++] = edge;
			// System.out.println("edge: " + edge.getId1() + " " +
			// edge.getId2());

		}

		// TODO: Build Graph of all nodes in scenario in the following format:
		// (int nodeid1, int nodeid2, int distance)
		Graph context = new Graph(inputEdges);

		// TODO: use singleton here..
		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();

		// TODO: Make a decision here which strategy we will use..
		// always use Dijkstra's for now
		// in the same map..
		if (startMapId == endMapId) {

			// assemble 2 nodes just for test.. definitely should not use id to
			// search..
			fromNode.setId(Integer.valueOf(startNode));
			toNode.setId(Integer.valueOf(endNode));

			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, context);
			return result;
		} else {
			// for later use

			generalAlgorithm.setAlgoStrategy(new AstarAlgoStrategy());
			result = generalAlgorithm.findPath(fromNode, toNode, context);
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
	 * based on the source and destination user has inputed.
	 * 
	 * @return a list of input matrix that we will need as the input of the
	 *         algorithm
	 */
	private List<InputMatrix> getAlgoMatrix(int startMapId, int endMapId) {

		// Initialize all the matrix
		// we can initialize it in a much more earlier phase of the system
		// check the workflow here..
		Map<Integer, InputMatrix> allMatrixes = InitAllMatrixDaoImpl.initAllMatrix().getAllInitializedMatrix();

		// TODO: find the maps we need from the allMatrixes and return a list of
		// matrix that we want

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
