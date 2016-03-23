package com.wpi.cs509.teamA.test.algo;

import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.datastructure.AllEdges;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import org.junit.Test;

import static org.junit.Assert.*;


public class AstarMultipleMapTest {


	public AstarMultipleMapTest() {

	}

	@Test
	public void testAstar() {
		AlgoTestDriver a= new AlgoTestDriver();
		
		
		//DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		//DijkstraMultipleDestinations d = new DijkstraMultipleDestinations();
		//MultipleDestinations d = new MultipleDestinations();
		AstarAlgoStrategy d= new AstarAlgoStrategy();
		Stack<Node> route= new Stack<Node>();
		//Node[] node00={a.getNodes().get(14), a.getNodes().get(3)};
		//Node node00=new Node(88,40,40, map1);
		AllEdges edgess= new AllEdges(a.getEdges(), a.getMapedges(),a.getNodes().get(7), a.getNodes().get(15));
		edgess.init();
		route=d.getRoute(edgess);
		
		//route=algoController.getRoute();
		// 7 2 1 12 13 15
		assertEquals(route.size(), 6);
		System.out.print("Route: ");
		while (!route.isEmpty())
		{
			System.out.print(route.pop().getId() + " -> ");
		}
	}
	
}
