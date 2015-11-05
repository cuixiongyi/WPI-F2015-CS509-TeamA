package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * A* strategy
 * 
 * @author CS 509-Team A
 *
 */
public class AstarAlgoStrategy implements AlgoStrategy {
	private int startNodeId;
	private int endNodeId;
	
	/*
	 * A* algorithm implementation to find a route on one or multiple maps
	 */
	@Override
	public List<Integer> getRoute(Node startNode, Node endNode, Graph context) 
	{
		this.startNodeId = startNode.getId();
		this.endNodeId = endNode.getId();

		HashMap<Integer, Vertex> graph = context.getGraph();
	    
		
		return null;
	}

}
