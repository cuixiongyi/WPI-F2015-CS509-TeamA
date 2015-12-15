package com.wpi.cs509.teamA.junit;


import java.util.Stack;

import com.sun.org.apache.xpath.internal.axes.NodeSequence;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.allEdges;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;

public class MultipleDestinationsTest {
	
	public MultipleDestinationsTest(){
		
		AlgoTestDriver a= new AlgoTestDriver();
		
		
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		//DijkstraMultipleDestinations d = new DijkstraMultipleDestinations();
		//MultipleDestinations d = new MultipleDestinations();
		//AstarAlgoStrategy d= new AstarAlgoStrategy();
		Stack<Node> route= new Stack<Node>();
		Node[] node00={a.getNodes().get(14), a.getNodes().get(3)};
		//Node node00=new Node(88,40,40, map1);
		allEdges edgess= new allEdges(a.getEdges(), a.getMapedges(),a.getNodes().get(7), node00);
		route=d.getRoute(edgess);
		
		//route=algoController.getRoute();
		
		System.out.print("Route: ");
		while (!route.isEmpty())
		{
			System.out.print(route.pop().getId() + " -> ");
		}
	}
	
}