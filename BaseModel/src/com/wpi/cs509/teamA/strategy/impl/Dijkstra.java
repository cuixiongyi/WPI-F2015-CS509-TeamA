package com.wpi.cs509.teamA.strategy.impl;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * Takes one source and one graph to run Dijkstra
 * 
 * @author JLou
 *
 */
public class Dijkstra {

	private PriorityQueue<Vertex> q;

	public Dijkstra(Map<Integer, Vertex> graph, Vertex source) {

		q = new PriorityQueue<Vertex>();

		// add all the nodes to the q, the first element of the q should be the
		// start, since it is the only element that distance is 0
		for (Vertex v : graph.values()) {
			// no previous node for the source node
			v.setPrevious((v == source) ? source : null);
			// distance between source and source is 0
			v.setDist((v == source) ? 0 : Double.MAX_VALUE);
			q.add(v);
		}

	}

	/** Implementation of dijkstra's algorithm using a binary heap. */
	public void runDijkstra() {

		if (q == null) {
			throw new RuntimeException("PriorityQueue is not initialized yet!!");
		}

		Vertex currNode, neighborNode;
		while (!q.isEmpty()) {

			// vertex with shortest distance (first iteration will return
			// source)
			currNode = q.poll();

			/**
			 * we can ignore currNode (and any other remaining vertices) since
			 * they are unreachable
			 * 
			 * since this is a priority queue and the smallest is always on the
			 * top, if the top one is unreachable, no need to continue on
			 * checking the rest of the nodes
			 * 
			 * BTW, the first time the getDist() will return 0 so that the
			 * algorithm will get going on, and then all the nodes's neighbor
			 * will get updated so that the priority q will always have
			 * something reachable at the top
			 */
			if (currNode.getDist() == Double.MAX_VALUE)
				break;

			/**
			 * look at distances to each neighbor
			 * 
			 * this will update the tp of the priority q
			 */
			for (Map.Entry<Vertex, Double> neighbor : currNode.getNeighborV().entrySet()) {
				// the neighbor in this iteration
				neighborNode = neighbor.getKey();

				double alternateDist = currNode.getDist() + neighbor.getValue();
				if (alternateDist < neighborNode.getDist()) {
					// shorter path to neighbor found
					q.remove(neighborNode);
					neighborNode.setDist(alternateDist);
					/**
					 * This contains the result set...
					 */
					neighborNode.setPrevious(currNode);
					q.add(neighborNode);
				}
			}
		}
	}
}
