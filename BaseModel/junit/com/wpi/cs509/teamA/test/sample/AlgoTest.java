package com.wpi.cs509.teamA.test.sample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.Graph;

public class AlgoTest {

	@Test
	public void testAlgoStrategy() {
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
		System.out.println();
		System.out.println();
		for (int i : route)
		{
			System.out.print(i + ", ");
		}
	}

	
}
