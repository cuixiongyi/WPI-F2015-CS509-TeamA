package com.wpi.cs509.teamA.controller;

import java.util.List;

import com.wpi.cs509.teamA.entities.Node;
import com.wpi.cs509.teamA.findRoute.FindRoute;
import com.wpi.cs509.teamA.findRouteImpl.FindRouteImpl;

public class FindRouteSameMap extends AlogController{

	// should get parameters from the front end
	// do what it should do to find a path
	private Node startNode;
	private Node endNode;
	
	public FindRouteSameMap(){
		
	}
	
	public FindRouteSameMap(Node startNode, Node endNode){
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	
	public List<Node> pathFinding(){
		
		FindRoute fr = new FindRouteImpl();
		// not correctly
		List<Node> result = fr.findRouteSameMap(startNode, endNode);
		// draw on the map
		return result;
	}
	
}
