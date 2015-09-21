package com.wpi.cs509.teamA.findRouteImpl;

import java.util.List;

import com.wpi.cs509.teamA.entities.*;
import com.wpi.cs509.teamA.findRoute.FindRoute;

public class FindRouteImpl implements FindRoute{
	
	private Node startNode;
	private Node endNode;
	
	public List<Node> findRouteBetweenSimpleTwoNodes(Node startNode, Node endNode){
		return null;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

}
