package com.wpi.cs509.teamA.test.algo;


import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.AllEdges;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import org.junit.Test;

import static org.junit.Assert.*;


public class MultipleDestinationsTest {
	
	public MultipleDestinationsTest() {

	}
	@Test
	public void testMulti() {
		AlgoTestDriver a= new AlgoTestDriver();
		
		
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		//DijkstraMultipleDestinations d = new DijkstraMultipleDestinations();
		//MultipleDestinations d = new MultipleDestinations();
		//AstarAlgoStrategy d= new AstarAlgoStrategy();
		Stack<Node> route= new Stack<Node>();
		Node[] node00={a.getNodes().get(16), a.getNodes().get(5)};
		//Node node00=new Node(88,40,40, map1);
		AllEdges edgess= new AllEdges(a.getEdges(), a.getMapedges(),a.getNodes().get(10), node00);
		route=d.getRoute(edgess);
		
		assertEquals(route.size(), 12);

		System.out.print("Route: ");
		while (!route.isEmpty())
		{
			System.out.print(route.pop().getId() + " -> ");
		}
	}
	
}