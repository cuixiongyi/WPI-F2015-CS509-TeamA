package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;

/**
 * Dijkstra Strategy
 * 
 * @author CS 509-Team A
 * 
 * Referenced RosettaCode for this implementation of Dijkstra's Algorithm
 * Link: http://rosettacode.org/wiki/Dijkstra's_algorithm 
 */
public class DijkstraAlgoStrategy implements AlgoStrategy {
	private int startNodeId;
	private int endNodeId;

	/*
	 * Dijkstra's algorithm implementation to find a route on one or multiple maps
	 */
	@Override
	public List<Integer> getRoute(Node startNode, Node endNode, Graph context) {
		this.startNodeId = startNode.getId();
		this.endNodeId = endNode.getId();

		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}
		Vertex source = graph.get(startNodeId);
		NavigableSet<Vertex> q = new TreeSet<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Integer.MAX_VALUE);
			q.add(v);
		}

		dijkstra(q);
		return context.getPath(this.endNodeId);
	}

	/** Implementation of dijkstra's algorithm using a binary heap. */
	private void dijkstra(final NavigableSet<Vertex> q) {
		Vertex u, v;
		while (!q.isEmpty()) {

			u = q.pollFirst(); // vertex with shortest distance (first iteration
								// will return source)
			if (u.getDist() == Integer.MAX_VALUE)
				break; // we can ignore u (and any other remaining vertices)
						// since they are unreachable

			// look at distances to each neighbor
			for (Map.Entry<Vertex, Integer> a : u.getNeighbours().entrySet()) {
				v = a.getKey(); // the neighbor in this iteration

				int alternateDist = u.getDist() + a.getValue();
				if (alternateDist < v.getDist()) { // shorter path to neighbor
													// found
					q.remove(v);
					v.setDist(alternateDist);
					v.setPrevious(u);
					q.add(v);
				}
			}
		}
	}
}