package com.wpi.cs509.teamA.test.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.strategy.controller.AlgoController;
import com.wpi.cs509.teamA.strategy.controller.allEdges;
import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
//import com.wpi.cs509.teamA.strategy.impl.AstarAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraAlgoStrategy;
import com.wpi.cs509.teamA.strategy.impl.DijkstraMultipleDestinations;
import com.wpi.cs509.teamA.strategy.impl.Graph;
import com.wpi.cs509.teamA.strategy.impl.MultipleDestinations;

public class AlgoTestDriver {
	
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Edge> mapedges = new ArrayList<Edge>();
	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	public AlgoTestDriver(){
		
		GeneralMap map1=new GeneralMap(0,1);
		Node node0 = new Node(1, 20, 20, map1);
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
		
		nodes.add(node0);
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		nodes.add(node7);
		nodes.add(node8);
		nodes.add(node9);
		nodes.add(node10);
		nodes.add(node11);
		nodes.add(node12);
		nodes.add(node13);
		nodes.add(node14);
		nodes.add(node15);
		nodes.add(node16);
		nodes.add(node17);
		nodes.add(node18);
		

	}
	

	public ArrayList<Node> getNodes() {
		return nodes;
	}


	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}


	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public ArrayList<Edge> getMapedges() {
		return mapedges;
	}

	public void setMapedges(ArrayList<Edge> mapedges) {
		this.mapedges = mapedges;
	}
	
	
}
