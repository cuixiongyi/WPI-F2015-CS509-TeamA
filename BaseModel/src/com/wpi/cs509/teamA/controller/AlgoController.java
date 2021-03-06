package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.InitAllMatrixDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
//import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.*;
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
	private Node endNode=null;
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
	 private allEdges edges;
	 private boolean flag=false;
	 boolean isMultipleDestination = false;
	 
	public AlgoController(Node from, Node to) {

		
		
	}
	
	public AlgoController(Node from, ArrayList<Node> to) {
		if(to.size()==1){
			edges= new allEdges(Database.getAllEdges(),Database.getAllMapEdges(),from, to.get(0));
			flag=true;
		}
	}
	
	public AlgoController(Node from, ArrayList<Node> to, boolean isMultiopleDestination) {
		Node[] end = new Node[to.size()];
		to.toArray(end);
		edges= new allEdges(Database.getAllEdges(),Database.getAllMapEdges(),from, end);
		this.isMultipleDestination=true;
	}

	/**
	 * This method will get source and destination from the AlgoController class
	 * and get the route use strategy pattern
	 * 
	 * @return a map data structure that the key is the map id and the value is
	 *         a list of nodes that represents the path on that map
	 */
	public Stack<Node> getRoute() {

		// we support searching node now only..

		// get the node from database
		//Node fromNode = Database.getNodeFromName(startNode);    /////////////////
        // TODO this is a hack need to get the function working

       
        
		// TODO: use singleton here..
		GeneralAlgorithm generalAlgorithm = new GeneralAlgorithm();

		if(flag){
//			edges.init();
			System.out.println("normal path");
//			if(edges.getMaps().size()>3)
//				generalAlgorithm.setAlgoStrategy(new AstarAlgoStrategy());
//			else
			long tStart = System.currentTimeMillis();

			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());

			//flag=false;
			 result = generalAlgorithm.findPath(edges);
//			long tEnd = System.currentTimeMillis();
//			long tDelta = tEnd - tStart;
//			double elapsedSeconds = tDelta / 1000.0;
			return result;
		}
		
		if(this.isMultipleDestination){
			System.out.println("multiple destination");
			generalAlgorithm.setAlgoStrategy(new MultipleDestinations());
			this.isMultipleDestination=false;
		}
		else{
			System.out.println("Find nearest");
			generalAlgorithm.setAlgoStrategy(new DijkstraAlgoStrategy());
		}
		
		return result = generalAlgorithm.findPath(edges);

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

}
