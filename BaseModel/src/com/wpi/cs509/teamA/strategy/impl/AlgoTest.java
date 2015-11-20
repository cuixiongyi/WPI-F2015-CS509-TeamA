package com.wpi.cs509.teamA.strategy.impl;
import com.wpi.cs509.teamA.bean.*;
import java.util.Stack;


public class AlgoTest {

	public static void main(String args[]){
		GeneralMap map1=new GeneralMap("a",1);
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
		GeneralMap map2=new GeneralMap("b",10);
		Node node12 = new Node(12, 10, 20, map2);
		Node node13 = new Node(13, 20, 30,map2);
		Node node14 = new Node(14, 20, 40,map2);
		Node node15 = new Node(15, 20, 50,map2);
		Edge edge15 = new Edge(node12, node14);
		Edge edge16= new Edge(node15, node13);
		Edge edge17= new Edge(node12, node13);
		//MapEdge mapedge1= new MapEdge(map1,map2,50);
		Edge edge18= new Edge(node1, node12,50);
		
		
		//List<Integer> route = new ArrayList<Integer>();
		Edge[] edges = {edge1, edge2, edge3, edge4, edge5,edge6, edge7, edge8, edge9, edge10,edge11, edge12, edge13, edge14,edge15,edge16
				,edge17,edge18};
		Graph context = new Graph (edges); //for running the algorithm
		//DijkstraAlgoStrategy d = new DijkstraAlgoStrategy();
		//add neighborE to nodes for deletion of nodes
		//
		
		
		AstarAlgoStrategy d= new AstarAlgoStrategy();
		Stack<Node> route= new Stack<Node>();
		route=d.getRoute(node10, node15, context);
		System.out.print("Route: ");
		while (!route.isEmpty())
		{
			System.out.print(route.pop().getId() + " -> ");
		}
	}
	
}

