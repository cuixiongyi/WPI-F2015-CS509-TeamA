package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;

public class Graph {

	// mapping of vertex names to Vertex objects, built from a set of Edges
	private HashMap<Integer, Vertex> graph;
	private List<Edge> edges;

	/** Builds a graph from a set of edges */
	public Graph(Edge[] edges) {
		graph = new HashMap<>(edges.length);

		for (Edge e : edges)
		{
			this.edges.add(e);
		}
		
		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.getNode1().getId())) {
				graph.put(e.getNode1().getId(), new Vertex(e.getNode1().getId(), e.getNode1().getLocation()));
			}
			if (!graph.containsKey(e.getNode2().getId())) {
				graph.put(e.getNode2().getId(), new Vertex(e.getNode2().getId(), e.getNode2().getLocation()));
			}
		}

		// another pass to set neighbouring vertices
		for (Edge e : edges) {
			graph.get(e.getNode2().getId()).getNeighbours().put(graph.get(e.getNode1().getId()), e.getDist());
		}
	}

	public List<Edge> getEdges()
	{
		return this.edges;
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
