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

		// TODO: the start node should not get from all edges.. so obscure and
		// very unsafe
		this.startNodeId = alledges.getStartNode().getId();
		Graph context = new Graph(alledges.getAllEdges());
		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
		}

		Vertex source = context.getGraph().get(startNodeId);

		// TODO: what is Dijkstra about?
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
					destination = des;
				}
			}
		}
		return findPathFromDij(source, destination);
	}

	private Stack<Node> findPathFromDij(Vertex source, Vertex d) {

		Stack<Node> result = new Stack<Node>();
		do {
			result.push(d);
			d = d.getPrevious();
		} while (d != source);
		result.push(source);
		return result;
	}
}