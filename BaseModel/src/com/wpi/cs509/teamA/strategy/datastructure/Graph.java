package com.wpi.cs509.teamA.strategy.datastructure;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * A Graph that has all the vertex information
 * 
 * This is more like to build a relationship between different vertices
 * 
 * We use all the edges to construct the graph
 * 
 * and we also have a hashmap that contains node id and the Vertex
 * 
 * @author JLou
 *
 */
public class Graph {

	/**
	 * mapping of vertex id to Vertex objects, built from a set of Edges
	 * 
	 * the reason to use hashmap is to use id to tell the difference between
	 * vertices
	 */
	private HashMap<Integer, Vertex> graph;

	public Graph() {

	}

	/** Builds a graph from a list of edges */
	public Graph(List<Edge> edges) {
		graph = new HashMap<>(edges.size());

		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.getNode1().getId())) {
				graph.put(e.getNode1().getId(), new Vertex(e.getNode1()));
			}
			if (!graph.containsKey(e.getNode2().getId())) {
				graph.put(e.getNode2().getId(), new Vertex(e.getNode2()));
			}
		}

		/**
		 * another pass to set neighboring for all the vertices
		 * 
		 * must add vertex neighbor when all edges information are collected
		 * 
		 * from edge we can get a pair of neighbor nodes and their distance
		 * between them
		 * 
		 */

		for (Edge e : edges) {
			// TODO: Should we refactor this to set neighbor?
			graph.get(e.getNode1().getId()).getNeighborV().put(graph.get(e.getNode2().getId()), e.getDist());
			graph.get(e.getNode2().getId()).getNeighborV().put(graph.get(e.getNode1().getId()), e.getDist());
		}

	}

	public HashMap<Integer, Vertex> getGraph() {
		return graph;
	}
}
