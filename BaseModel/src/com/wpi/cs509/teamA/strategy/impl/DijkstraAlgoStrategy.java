package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.Stack;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.controller.AllEdges;

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
	public Stack<Node> getRoute(AllEdges alledges) {

		// TODO: the start node should not get from all edges.. so obscure
		this.startNodeId = alledges.getStartNode().getId();
		// initialize the graph, make a graph via all edges
		Graph context = new Graph(alledges.getAllEdges());
		HashMap<Integer, Vertex> graph = context.getGraph();

		if (!graph.containsKey(startNodeId)) {
			// TODO: throw an exception here!
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
		}
		// get the start vertex
		Vertex source = context.getGraph().get(startNodeId);

		// TODO: need to get a return value here!
		new DijkstraImpl(graph, source).runDijkstra();
		Vertex destination = new Vertex();

		if (alledges.isMulEndNodes()) {
			this.endNodeId = alledges.getEndNode().getId();
			destination = context.getGraph().get(endNodeId);
		} else {
			double i = Integer.MAX_VALUE;
			for (Node n : alledges.getEnd()) {
				Vertex des = new Vertex();
				des = context.getGraph().get(n.getId());
				if (des.getDist() < i) {
					i = des.getDist();
					destination = des;
				}
			}
		}

		return getPathFromDijRes(source, destination);
	}

	private Stack<Node> getPathFromDijRes(Vertex source, Vertex d) {

		Stack<Node> result = new Stack<Node>();
		do {
			result.push(d);
			d = d.getPrevious();
		} while (d != source);
		result.push(source);
		return result;
	}
}