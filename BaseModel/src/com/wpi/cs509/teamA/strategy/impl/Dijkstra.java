package com.wpi.cs509.teamA.strategy.impl;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Takes one source and one graph run Dijkstra
 */

public class Dijkstra {

	private PriorityQueue<Vertex> q;

	public Dijkstra(Map<Integer, Vertex> graph, Vertex source) {
		
		q = new PriorityQueue<Vertex>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Double.MAX_VALUE);
			q.add(v);
		}

		dijkstra(q);
	}

	/** Implementation of dijkstra's algorithm using a binary heap. */
	private void dijkstra(final PriorityQueue<Vertex> q) {
		Vertex u, v;
		while (!q.isEmpty()) {

			// vertex with shortest distance (first iteration will return
			// source)
			u = q.poll();

			// we can ignore u (and any other remaining vertices) since they are
			// unreachable
			if (u.getDist() == Double.MAX_VALUE)
				break;

			// look at distances to each neighbor
			for (Map.Entry<Vertex, Double> a : u.getNeighborV().entrySet()) {
				// the neighbor in this iteration
				v = a.getKey();

				double alternateDist = u.getDist() + a.getValue();
				if (alternateDist < v.getDist()) {
					// shorter path to neighbor found
					q.remove(v);
					v.setDist(alternateDist);
					v.setPrevious(u);
					q.add(v);
				}
			}
		}
	}
}
