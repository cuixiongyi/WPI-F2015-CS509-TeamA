package com.wpi.cs509.teamA.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.util.InputMatrix;

public class GeneralAlgorithm {

	/**
	 * the strategy will be applied on the path finding
	 */
	private AlgoStrategy algoStrategy;	
	
	public GeneralAlgorithm(){
		
	}
	
	public GeneralAlgorithm(AlgoStrategy algoStrategy){
		this.algoStrategy = algoStrategy;
	}

	public AlgoStrategy getAlgoStrategy() {
		return algoStrategy;
	}

	public void setAlgoStrategy(AlgoStrategy algoStrategy) {
		this.algoStrategy = algoStrategy;
	}
	
	public Map<Integer, List<Node>> findPath(Node startNode, Node endNode, List<InputMatrix> im){
		
		System.out.println("General Algorithm");
		
		Map<Integer, List<Node>> result = new HashMap<Integer, List<Node>>();
		// add this one by one to the result
		List<Node> routeOnOneMap = new ArrayList<Node>();
		
		algoStrategy.getRoute(startNode, endNode, im);
		
		return result;
	}
}
