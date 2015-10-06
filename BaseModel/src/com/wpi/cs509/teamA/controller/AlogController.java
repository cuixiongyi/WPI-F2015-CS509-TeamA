package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.entities.Node;
import com.wpi.cs509.teamA.findRoute.FindRoute;
import com.wpi.cs509.teamA.findRouteImpl.FindRouteImpl;

/**
 * This class will get the start and end nodes from the front end.
 * The node got from the front end is in String form,
 * So this class will get a Node from database based on the string it receive.
 * @author CS 509-Team A 
 * @version Oct 5th
*/
public class AlogController {
	
	/**
	 * The start node get from front end
	 * It is a String
	 */
	private String startNode;
	/**
	 * The destination node get from the front end
	 * It is a String
	 */
	private String endNode;
	
	
	
	/**
	 * default constructor
	 */
	public AlogController(){
		
	}
	
	/**construtor,initiate with the source and destination
	 *@param from  the source node
	 *@param to  the destination node
	 * */
	public AlogController(String from, String to){
		
		this.startNode = from;
		this.endNode = to;		
	}
	
	/**
	 * This method will decide which algorithm will be called to find a route between two different points
	 * It will make a decision by the id of the map.
	 * To decide a algorithm, find route between several maps, or just a single map
	 * @return the route presented by a list of nodes
	 */
	public List<Node> getRoute(){
		
		// get the node from database
		Node fromNode = getNodeFromName(startNode);
		Node toNode = getNodeFromName(endNode);
		FindRoute fr = new FindRouteImpl();
		
		// get more information from the node we get
		String startMapId = fromNode.getMapId();
		String endMapId = toNode.getMapId();
		
		// decide which algorithm to use
		// walking through the same map
		if(startMapId == endMapId){
			List<Node> result = new ArrayList<Node>();
			result = fr.findRouteSameMap(fromNode, toNode);
			
		// can we have just two condition, sigleMap and Multiple Map?
		// find a way which is easier for programming
		}else if(startMapId != endMapId && (startMapId == "0" || endMapId == "0")){ // campus to building
			Map<Integer, List<Node>> result = new HashMap<Integer, List<Node>>();
			result = fr.findRouteMultipleMap(fromNode, toNode);
			
			
		}else{ // building to building
			// ??
		}
		
		return null;
	}
	
	/**
	 * Gets the starting node for a route
	 * @return starting node of the route
	 */
	public String getStartNode() {
		return startNode;
	}

	/**
	 * Sets the starting node for a route
	 * @param startNode starting node of the route
	 */
	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	/**
	 * Gets the ending node for a route
	 * @return ending node of the route
	 */
	public String getEndNode() {
		return endNode;
	}

	/**
	 * Sets the ending node for a route
	 * @param ending node of the route
	 */
	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}
	
	/**
	 * Gets the node that corresponding to a given location name
	 * @param nodeName The name of the location
	 * @return node corresponding to the location name
	 */
	private Node getNodeFromName(String nodeName){
		
		// use node name to find the Node we need
		// search database?
		
		return null;
		
	}
	
}
