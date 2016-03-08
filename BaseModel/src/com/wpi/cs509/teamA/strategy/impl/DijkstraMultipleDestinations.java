package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.Stack;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.controller.allEdges;

public class DijkstraMultipleDestinations implements AlgoStrategy {
	private int startNodeId;
	private int[] endNodeId;
	private double[][] matrix;

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
		order = getEndNodeOrder(); // top of the stack is the last node visited
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
		int i = 0;
		int minj = -1;
		double dist = -1;
		Stack<Integer> order = new Stack<Integer>();
		order.push(0);
		while (true) {
			dist = Double.MAX_VALUE;
			int j;
			for (j = 0; j < this.endNodeId.length; j++) {
				System.out.println(i + "\t" + j + "\tminj:" + minj);
				if (this.matrix[i][j] < dist && this.matrix[i][j] != 0) {
					dist = this.matrix[i][j];
					minj = j;
					System.out.println(this.matrix[i][j]);
				}
			}
			// set matrix[i][] and matrix[][i] to infinity
			for (int w = 0; w < this.endNodeId.length; w++) {
				matrix[i][w] = Double.MAX_VALUE;
			}
			for (int w = 0; w < this.endNodeId.length; w++) {
				matrix[w][i] = Double.MAX_VALUE;
			}

			if (dist == Double.MAX_VALUE)
				break;
			i = minj;
			System.out.println("minj:" + endNodeId[minj]);
			order.push((Integer) minj);
		}
		/*
		 * for(int e: order) System.out.println(e)
		 */;

		return order;
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
