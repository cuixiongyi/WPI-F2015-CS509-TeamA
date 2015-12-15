package com.wpi.cs509.teamA.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.controller.AlgoController;
import com.wpi.cs509.teamA.controller.allEdges;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
//import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraMultipleDestinations;
import com.wpi.cs509.teamA.strategy.impl.Graph;
import com.wpi.cs509.teamA.strategy.impl.MultipleDestinations;

public class AlgoTest {
	
	@Test
	public void testAlgoStrategy3(){
		GeneralMap map1=new GeneralMap(0,1);
		Node node1 = new Node(1, 10, 20, map1);
		Node node2 = new Node(2, 20, 30,map1);
		Node node3 = new Node(3, 20, 40,map1);
		Node node4 = new Node(4, 20, 50,map1);
		Node node5 = new Node(5, 30, 50,map1);
		Node node6 = new Node(6, 40, 50,map1);
		Node node7 = new Node(7, 40, 40,map1);
		Node node8 = new Node(8, 30, 10,map1);
		Node node9 = new Node(9, 40, 20,map1);
		Node node10 = new Node(10, 50, 20,map1);
		Node node11 = new Node(11, 50, 30,map1);
		Edge edge1 = new Edge(node1, node8);
		Edge edge2 = new Edge(node1, node2);
		Edge edge3 = new Edge(node2, node3);
		Edge edge4 = new Edge(node3, node4);
		Edge edge5 = new Edge(node4, node5);
		Edge edge6 = new Edge(node5, node6);
		Edge edge7 = new Edge(node6, node7);
		Edge edge8 = new Edge(node7, node8);
		Edge edge9 = new Edge(node2, node7);
		Edge edge10 = new Edge(node2, node5);
		Edge edge11 = new Edge(node7, node9);
		Edge edge12 = new Edge(node9, node11);
		Edge edge13 = new Edge(node9, node10);
		Edge edge14= new Edge(node10, node11);
		
		GeneralMap map2=new GeneralMap(1,1);
		Node node12 = new Node(12, 10, 20, map2);
		Node node13 = new Node(13, 20, 30,map2);
		Node node14 = new Node(14, 20, 40,map2);
		Node node15 = new Node(15, 20, 50,map2);
		Edge edge15 = new Edge(node12, node14);
		Edge edge16= new Edge(node15, node13);
		Edge edge17= new Edge(node12, node13);
		Edge edge18= new Edge(node1, node12,50);
		
		GeneralMap map3=new GeneralMap(2,1);
		Node node16 = new Node(16, 10, 20, map3);
		Node node17 = new Node(17, 20, 30,map3);
		Node node18 = new Node(18, 20, 40,map3);
		Edge edge19 = new Edge(node16, node17);
		Edge edge20= new Edge(node18, node17);
		Edge edge21= new Edge(node16, node18);
		Edge edge22= new Edge(node16, node14,100);
		
		
		List<Edge> edges= new ArrayList<Edge>();
		List<Edge> mapedges= new ArrayList<Edge>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
		edges.add(edge10);
		edges.add(edge11);
		edges.add(edge12);
		edges.add(edge13);
		edges.add(edge14);
		edges.add(edge15);
		edges.add(edge16);
		edges.add(edge17);
		edges.add(edge18);
		edges.add(edge19);
		edges.add(edge20);
		edges.add(edge21);
		mapedges.add(edge18);
		mapedges.add(edge22);
//		Edge[] edges = {edge1, edge2, edge3, edge4, edge5,edge6, edge7, edge8, edge9, edge10,edge11, edge12, edge13, edge14,edge15,edge16
//				,edge17,edge18};
		//Graph context = new Graph (edges); //for running the algorithm
		//DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
	
		//AstarAlgoStrategy d= new AstarAlgoStrategy();
		
		DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		//DijkstraMultipleDestinations d = new DijkstraMultipleDestinations();
		//MultipleDestinations d = new MultipleDestinations();
		//AstarAlgoStrategy d= new AstarAlgoStrategy();
		Stack<Node> route= new Stack<Node>();
		Node[] node00={node15, node4};
		//Node node00=new Node(88,40,40, map1);
		allEdges edgess= new allEdges(edges, mapedges,node9, node00);
		route=d.getRoute(edgess);
		
		//route=algoController.getRoute();
		
		System.out.print("Route: ");
		while (!route.isEmpty())
		{
			System.out.print(route.pop().getId() + " -> ");
		}
	}
	
	
	
}
