package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;

import com.wpi.cs509.teamA.bean.Edge;

public class Graph {

	// mapping of vertex names to Vertex objects, built from a set of Edges
	private HashMap<Integer, Vertex> graph;

	/** Builds a graph from a set of edges */
	public Graph(Edge[] edges) {
		graph = new HashMap<>(edges.length);

		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.getId1())) {
				graph.put(e.getId1(), new Vertex(e.getId1()));
			}
			if (!graph.containsKey(e.getId2())) {
				graph.put(e.getId2(), new Vertex(e.getId2()));
			}
		}

		// another pass to set neighbouring vertices
		for (Edge e : edges) {
			graph.get(e.getId1()).getNeighbours().put(graph.get(e.getId2()), e.getDist());
			graph.get(e.getId2()).getNeighbours().put(graph.get(e.getId1()), e.getDist());
		}
	}

	public List<Integer> getPath(int endId) {
		if (!graph.containsKey(endId)) {
			System.err.printf("Graph doesn't contain end vertex \"%d\"\n", endId);
			return null;
		}

		graph.get(endId).clearPathNodeIds();
		graph.get(endId).getNext();
		List<Integer> result = graph.get(endId).getPathNodeIds();
		result.add(endId);
		// return list of node ids that make up the path
		return result; 
	}

	/** Prints a path from the source to the specified vertex */
	/*public void printPath(int endId) {
		if (!graph.containsKey(endId)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endId);
			return;
		}

		graph.get(endId).printPath();
		System.out.println();
	}*/

	/**
	 * Prints the path from the source to every vertex (output order is not
	 * guaranteed)
	 */
	/*
	public void printAllPaths() {
		for (Vertex v : graph.values()) {
			v.printPath();
			System.out.println();
		}
	}*/

	public HashMap<Integer, Vertex> getGraph() {
		return graph;
	}
}
