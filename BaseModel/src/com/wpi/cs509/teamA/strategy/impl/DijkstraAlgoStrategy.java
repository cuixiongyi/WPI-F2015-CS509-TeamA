package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.Stack;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.controller.allEdges;

/**
 * Dijkstra Strategy
 * 
 * @author CS 509-Team A
 *
 */
public class DijkstraAlgoStrategy implements AlgoStrategy {
	private int startNodeId;
	private int endNodeId;

	@Override
	public Stack<Node> getRoute(allEdges alledges) {
		this.startNodeId = alledges.getStartNode().getId();
		Graph context = new Graph(alledges.getAllEdges());
		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}

		Vertex source = new Vertex();
		source = context.getGraph().get(startNodeId); // point to the same
														// object

		// what is dij about?
		Dijkstra dij = new Dijkstra(graph, source);
		Vertex destination = new Vertex();

		if (alledges.isNormal()) {
			this.endNodeId = alledges.getEndNode().getId();
			destination = context.getGraph().get(endNodeId);
		} else {
			double i = Integer.MAX_VALUE;
			for (Node n : alledges.getEnd()) {
				Vertex des = new Vertex();
				des = context.getGraph().get(n.getId());
				if (des.getDist() < i) {
					i = des.getDist();
					//System.out.println("Des ID: "+ des.getId()+ "\n"+"Des Dis: "+des.getDist());
					destination = des;
				}
			}
		}
		return findPathFromDij(source, destination);
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