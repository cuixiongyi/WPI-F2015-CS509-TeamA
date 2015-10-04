package com.wpi.cs509.teamA.findRouteImpl;

import java.util.List;

import com.wpi.cs509.teamA.entities.*;
import com.wpi.cs509.teamA.findRoute.FindRoute;

public class FindRouteImpl implements FindRoute{

	/** 
	 * Finds a route from a source to destination on the same map
	 * @param startNode Starting location of the route 
	 * @param endNode Ending location of the route
	 * @return Route from source to destination
	 */
	public List<Node> findRouteSameMap(Node startNode, Node endNode){
		return null;
	}
	
	/** 
	 * Finds a route from a source to destination on multiple maps
	 * @param startNode Starting location of the route 
	 * @param endNode Ending location of the route
	 * @return Route from source to destination across multiple maps
	 */
	public List<List<Node>> findRouteMultipleMap(Node startNode, Node endNode){
		return null;
	}

	

}
