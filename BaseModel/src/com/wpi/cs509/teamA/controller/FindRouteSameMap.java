package com.wpi.cs509.teamA.controller;

import java.util.List;

import com.wpi.cs509.teamA.entities.Node;
import com.wpi.cs509.teamA.findRoute.FindRoute;
import com.wpi.cs509.teamA.findRouteImpl.FindRouteImpl;
/**
 * implementation of find a route on the same map
 * @author CS 509-Team A 
 * @version Oct 4th
*/


public class FindRouteSameMap extends AlogController{

	// should get parameters from the front end
	// do what it should do to find a path
	/** the start node of the route*/
	private Node startNode;
	/** the end node of the route*/
	private Node endNode;
	
	/**counstructor*/
	public FindRouteSameMap(){
		
	}
	
	/**constructor, initiate by the startNode and endNode*/
	public FindRouteSameMap(Node startNode, Node endNode){
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	/**find the path between start and end
	 * @return a route difined by a list of nodes
	*/
	public List<Node> pathFinding(){
		
		FindRoute fr = new FindRouteImpl();
		// not correctly
		List<Node> result = fr.findRouteSameMap(startNode, endNode);
		// draw on the map
		return result;
	}
	
}
