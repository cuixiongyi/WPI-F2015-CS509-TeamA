package com.wpi.cs509.teamA.strategy;

import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * We decided to use a Strategy Pattern for the path finding algorithms. We did
 * this because we might try out different algorithms, for example Dijkstra's
 * and A*, to determine which is more suitable for this application. The scope
 * of the algorithm should be flexible, run the map as a whole to get a global
 * optimum, or run each map individually to get a high performance, greedy
 * solution. We can also run multiple strategies in parallel in order to test
 * for execution times. This will allow us to tune the application to base what
 * algorithm it uses on the context of the search.
 * 
 * @author CS 509-Team A
 *
 */
public interface AlgoStrategy {

	Map<Integer, List<Node>> getRoute(Node startNode, Node endNode, List<InputMatrix> matrixes);

}
