package com.wpi.cs509.teamA.strategy.impl;

import com.wpi.cs509.teamA.bean.*;
import java.util.HashMap;
import java.util.List;

public class Graph {

	// mapping of vertex names to Vertex objects, built from a set of Edges
	private HashMap<Integer, Vertex> graph;
	private List<Edge> edges;

	/** Builds a graph from a set of edges */
	public Graph(List<Edge> edges) {
		graph = new HashMap<>(edges.size());
		//
		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.getNode1().getId())) {
				graph.put(e.getNode1().getId(), new Vertex(e.getNode1()));
			}
			if (!graph.containsKey(e.getNode2().getId())) {
				graph.put(e.getNode2().getId(), new Vertex(e.getNode2()));
			}
		}

		// another pass to set neighboring vertices
		// must add vertex neighbor when all edges information are collected
		for (Edge e : edges) {
			graph.get(e.getNode1().getId()).getNeighborV().put(graph.get(e.getNode2().getId()), e.getDist());
			graph.get(e.getNode2().getId()).getNeighborV().put(graph.get(e.getNode1().getId()), e.getDist());
		}

		// add edges info to nodes for deletions
	}

	public List<Edge> getEdges() {
		return this.edges;
	}

	public HashMap<Integer, Vertex> getGraph() {
		return graph;
	}
}
