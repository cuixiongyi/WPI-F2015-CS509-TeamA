package com.wpi.cs509.teamA.controller;

import java.util.List;

import com.wpi.cs509.teamA.entities.Node;

public class AlogController {
	
	private String startNode;
	private String endNode;
	
	public AlogController(){
		
	}
	
	public AlogController(String from, String to){
		
		this.startNode = from;
		this.endNode = to;		
	}
	
	public List<Node> getRoute(){
		Node fromNode = getNodeFromName(startNode);
		Node toNode = getNodeFromName(endNode);
		String startMapId = fromNode.getMapId();
		String endMapId = toNode.getMapId();
		// decide which algorithm to use
		
		// walking through the same map
		if(startMapId == endMapId){
			AlogController ac= new FindRouteSameMap();
			List<Node> result = ac.pathFinding();
			return result;
			
		// can we have just two condition, sigleMap and Multiple Map?
		// find a way which is easier for programming
		}else if(startMapId != endMapId && (startMapId == "0" || endMapId == "0")){ // campus to building
			
		}else{ // building to building
			
		}
		
		return null;
	}
	
	public List<Node> pathFinding(){
		System.out.println("you must specify a implement class!");
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
	 * Gets the node cooresponding to a given location name
	 * @param nodeName The name of the location
	 * @return node cooresponding to the location name
	 */
	private Node getNodeFromName(String nodeName){
		
		// use node name to find the Node we need
		
		return null;
		
	}
	
}
