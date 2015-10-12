package com.wpi.cs509.teamA.strategy;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * 
 * @author CS 509-Team A
 *
 */
public interface AlgoStrategy {

	Map<Integer, List<Node>> getRoute(Node startNode, Node endNode, List<InputMatrix> matrixes);
	
}
