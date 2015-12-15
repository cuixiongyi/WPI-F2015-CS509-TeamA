package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.controller.allEdges;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;

public class MultipleDestinations implements AlgoStrategy {
	private int startNodeId;
	private int[] endNodeId;
	private double[][] matrix;
	private Stack<endNodeOrder> order = new Stack<endNodeOrder>();

	@SuppressWarnings("unchecked")
	@Override
	public Stack<Node> getRoute(allEdges alledges) {
		this.startNodeId = alledges.getStartNode().getId();
		this.endNodeId = new int[alledges.getEnd().length + 1];
		this.matrix = new double[alledges.getEnd().length + 1][alledges.getEnd().length + 1];
		Graph context = new Graph(alledges.getAllEdges());
		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}
		endNodeId[0] = startNodeId;
		int i = 1;
		for (Node n : alledges.getEnd()) {
			endNodeId[i] = n.getId();
			i++;
		}

		Vertex source = new Vertex();
		// source= context.getGraph().get(startNodeId); //point to the same
		// object
		i = 0;

		final Object[][] pathMatrix = new Object[alledges.getEnd().length + 1][alledges.getEnd().length + 1];
		while (i < endNodeId.length) { // last end node does not need to run
										// Dijkstra on
			source = context.getGraph().get(endNodeId[i]);
			Dijkstra dij = new Dijkstra(graph, source);
			Vertex des = new Vertex();
			for (int j = 0; j < endNodeId.length; j++) {
				des = context.getGraph().get(endNodeId[j]);
				matrix[i][j] = des.getDist();
				// matrix[j+1][i]=des.getDist();
				pathMatrix[i][j] = findPathFromDij(source, des);
			}
			// System.out.println(endNodeId.length);
			// System.out.println(context.getGraph().containsKey(14));
			i++;

		}
		for (int t = 0; t < matrix.length; t++) {
			for (int p = 0; p < matrix.length; p++) {
				System.out.print(matrix[t][p] + "\t");
			}
			System.out.println();
		}
		Stack<Integer> order = new Stack<Integer>();
		
		order=getEndNodeOrder(); // top of the stack is the last node visited
									// it is the position of the node in
									// endNodeId array +1

		Stack<Node> finalPath = new Stack<Node>();
		Stack<Node> finalPaths = new Stack<Node>();

		int t = 0;
		do { // System.out.println(order.size());

			int s = order.get(t);
			int d = order.get(t + 1);
			// System.out.println(s+"\t"+d);
			// System.out.println(endNodeId[s]+"\t"+endNodeId[d]);
			while (!((Stack<Node>) pathMatrix[s][d]).isEmpty()) {
				Node n = new Node();
				n = (Node) ((Stack<Node>) pathMatrix[s][d]).pop();
				finalPaths.add(n);
			}
			t++;

		} while (t < order.size() - 1);

		while (!finalPaths.isEmpty())
			finalPath.push(finalPaths.pop());

		return finalPath;
	}

	/*
	 * Calculate the order of the end nodes that would give the shortest total
	 * distance use the matrix as input
	 */

	private Stack<Integer> getEndNodeOrder() {
		LinkedList<Integer> t= new LinkedList<Integer>();
		Stack<Integer> result= new Stack<Integer>();
		for(int i=1;i<endNodeId.length;i++){
			t.add(i);
		}
		//System.out.println("+++"+t.size());
		endNodeOrder n= new endNodeOrder(0);
		findEndNodeOrder(n,t);
/*		for(endNodeOrder e:this.order)
			System.out.print(e.getId()+"\t");
		System.out.println();*/
		endNodeOrder nn=this.order.pop();
		System.out.println(this.order.size());
		result.push(nn.getId());
		while(nn.getNext()!=null){
			nn=nn.getNext();
			System.out.println("+++"+nn.getId());
			result.push(nn.getId());
		}
		
		for(int e:result)
			System.out.print(e+"\t");
		System.out.println();
		return result;		
		
	}
	 private double findEndNodeOrder(endNodeOrder s, LinkedList<Integer> t){
		 endNodeOrder n= new endNodeOrder();
		 n=s;
		 System.out.println("first of t is " + t.get(0));
		 if(t.size()==1){
			 endNodeOrder t1= new endNodeOrder(t.get(0));
			 n.setNext(t1); 
			 t1.setNext(null);
			 this.order.push(n);
			 System.out.println("nextEndNode is "+t1.getId());
			 return matrix[s.getId()][t.get(0)];
			 
		 }
		 else{
			 //int nextEndNode=-1;
			 double f=Double.MAX_VALUE;
			 for(int i : t){
				 endNodeOrder n1= new endNodeOrder(i);
				 LinkedList<Integer>tt=new LinkedList<Integer>();
				 tt.addAll(t);
				 tt.remove(tt.indexOf(i));
				 //System.out.println(s+"\t"+i);
				 //System.out.println("tt is " + tt.size());
				 double j=findEndNodeOrder(n1,tt);
				 //System.out.println("findEndNodeOrder: " + j);
				 j+=matrix[s.getId()][i];
				 if(f>j){
					 f=j;
					 n.setDist(f);
					 n.setNext(n1);
				 }
			 }
			
			 System.out.println("nextEndNode is "+n.getId());
			 this.order.push(n);
			 return f;
			 
		 }
	 }

	private Stack<Node> findPathFromDij(Vertex source, Vertex d) {
		// Vertex d= new Vertex();
		// d=d;
		// System.out.println(d.getDist());
		Stack<Node> result = new Stack<Node>();
		do {
			result.push(d);
			d = d.getPrevious();
		} while (d != source);
		result.push(source);
		return result;
	}
}

