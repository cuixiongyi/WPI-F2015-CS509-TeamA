package com.wpi.cs509.teamA.test.sample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.strategy.impl.Graph;

public class AlgoTest {

	@Test
	public void testAlgoStrategy1() {
		Node node1 = new Node(1, 10, 20);
		Node node2 = new Node(2, 20, 30);
		Node node3 = new Node(3, 20, 40);
		Node node4 = new Node(4, 20, 50);
		Node node5 = new Node(5, 30, 50);
		Node node6 = new Node(6, 40, 50);
		Node node7 = new Node(7, 40, 40);
		Node node8 = new Node(8, 30, 10);
		Node node9 = new Node(9, 40, 20);
		Node node10 = new Node(0, 50, 20);
		Node node11 = new Node(11, 50, 30);
		Edge edge1 = new Edge(node1, node8, 35);
		Edge edge2 = new Edge(node1, node2, 11);
		Edge edge3 = new Edge(node2, node3, 5);
		Edge edge4 = new Edge(node3, node4, 7);
		Edge edge5 = new Edge(node4, node5, 8);
		Edge edge6 = new Edge(node5, node6, 9);
		Edge edge7 = new Edge(node6, node7, 4);
		Edge edge8 = new Edge(node7, node8, 22);
		Edge edge9 = new Edge(node2, node7, 15);
		Edge edge10 = new Edge(node2, node5, 14);
		Edge edge11 = new Edge(node7, node9, 8);
		Edge edge12 = new Edge(node9, node11, 8);
		Edge edge13 = new Edge(node9, node10, 9);
		Edge edge14= new Edge(node10, node11, 5);
		
		List<Integer> route = new ArrayList<Integer>();
		Edge[] edges = {edge1, edge2, edge3, edge4, edge5,edge6, edge7, edge8, edge9, edge10,edge11, edge12, edge13, edge14};
		Graph context = new Graph (edges);
		//DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		AstarAlgoStrategy d=new AstarAlgoStrategy();
		route = d.getRoute(node1, node6, context);
		System.out.print("Route: ");
		for (int i : route)
		{
			System.out.print(i + " -> ");
		}
	}

//	@Test
//	public void testAlgoStrategy2() {
//		Node startNode = new Node(2);
//		Node endNode = new Node(3);
//		Edge edge1 = new Edge(7, 2, 10);
//		Edge edge2 = new Edge(7, 4, 8);
//		Edge edge3 = new Edge(2, 4, 6);
//		Edge edge4 = new Edge(2, 1, 9);
//		Edge edge5 = new Edge(1, 4, 12);
//		Edge edge6 = new Edge(1, 3, 11);
//		Edge edge7 = new Edge(4, 5, 7);
//		Edge edge8 = new Edge(3, 5, 2);
//		Edge edge9 = new Edge(3, 6, 5);
//		Edge edge10 = new Edge(5, 8, 8);
//		Edge edge11 = new Edge(6, 8, 8);
//		
//		List<Integer> route = new ArrayList<Integer>();
//		Edge[] edges = {edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11};
//		Graph context = new Graph (edges);
//		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
//		route = d.getRoute(startNode, endNode, context);
//		System.out.print("Route: ");
//		for (int i : route)
//		{
//			System.out.print(i + " -> ");
//		}
//	}

	
	
	
}
