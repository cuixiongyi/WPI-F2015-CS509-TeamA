package com.wpi.cs509.teamA.test.algo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Graph;

public class AlgoTest {

	@Test
	public void testAlgoStrategy1() {
		Node startNode = new Node(1);
		Node endNode = new Node(5);
		Edge edge1 = new Edge(1, 2, 10);
		Edge edge2 = new Edge(2, 3, 1);
		Edge edge3 = new Edge(3, 4, 3);
		Edge edge4 = new Edge(4, 5, 6);
		
		List<Integer> route = new ArrayList<Integer>();
		Edge[] edges = {edge1, edge2, edge3, edge4};
		Graph context = new Graph (edges);
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		route = d.getRoute(startNode, endNode, context);
		System.out.print("Route: ");
		for (int i : route)
		{
			System.out.print(i + " -> ");
		}
	}

	@Test
	public void testAlgoStrategy2() {
		Node startNode = new Node(2);
		Node endNode = new Node(3);
		Edge edge1 = new Edge(7, 2, 10);
		Edge edge2 = new Edge(7, 4, 8);
		Edge edge3 = new Edge(2, 4, 6);
		Edge edge4 = new Edge(2, 1, 9);
		Edge edge5 = new Edge(1, 4, 12);
		Edge edge6 = new Edge(1, 3, 11);
		Edge edge7 = new Edge(4, 5, 7);
		Edge edge8 = new Edge(3, 5, 2);
		Edge edge9 = new Edge(3, 6, 5);
		Edge edge10 = new Edge(5, 8, 8);
		Edge edge11 = new Edge(6, 8, 8);
		
		List<Integer> route = new ArrayList<Integer>();
		Edge[] edges = {edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11};
		Graph context = new Graph (edges);
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		route = d.getRoute(startNode, endNode, context);
		System.out.print("Route: ");
		for (int i : route)
		{
			System.out.print(i + " -> ");
		}
	}

	
	
	
}
