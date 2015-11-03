package com.wpi.cs509.teamA.test.sample;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.DupEntranceMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.Graph;
import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.ProxyMap;

/**
 * This is a sample test class to show you how to use JUnit
 * 
 * @author CS 509-Team A 
 *
 */
public class SampleTest {

	@Test
	public void testAlgoStrategy() {
		//AlgoController ac = new AlgoController();
		//ac.getRoute();
		//ac.getRoute();
		Node startNode = new Node(1, "start", 200, 300, 1, null, "Type");
		Node endNode = new Node(5, "end", 300, 200, 1, null, "Type");
		Edge edge1 = new Edge(1, 2, 10);
		Edge edge2 = new Edge(2, 3, 1);
		Edge edge3 = new Edge(3, 4, 3);
		Edge edge4 = new Edge(4, 5, 6);
		
		List<Integer> route;
		Edge[] edges = {edge1, edge2, edge3, edge4};
		Graph context = new Graph (edges);
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		route = d.getRoute(startNode, endNode, context);
		System.out.println(route.toString());
	}

	/**
	 * A wrong way to use the proxy pattern
	 */
	@Test
	public void testProxyMapWrong() {
		// though we use proxy, we still should not new the same thing several
		// times only the instance will not be initialized again, a new
		// instance, a new initialization
		for (int i = 0; i < 3; i++) {
			AdjacencyMatrix FULLER = new ProxyMap("fuller");
			InputMatrix im1 = FULLER.getAdjacencyMatrix();
			InputMatrix im2 = FULLER.getAdjacencyMatrix();
		}
	}

	/**
	 * A right way to use the proxy pattern
	 * 
	 */

	@Test
	public void testProxyMapRight() {

		AdjacencyMatrix FULLER = new ProxyMap("fuller");
		// the same FULLER instance, will never 
		InputMatrix im1 = FULLER.getAdjacencyMatrix();
		InputMatrix im2 = FULLER.getAdjacencyMatrix();
	}
	
	@Test
	public void testInitDupEntranceMap(){
		
		DupEntranceMap dem = DupEntranceMap.initDupEntranceMap();
		DupEntranceMap dem2 = DupEntranceMap.initDupEntranceMap();
		dem.getDupEntranceMap();
		dem.getDupEntranceMap();
		
		
	}
}
