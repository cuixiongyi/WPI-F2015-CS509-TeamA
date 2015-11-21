package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;

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
	public Stack<Node> getRoute(Node startNode, Node endNode, Edge[] edges) {
		this.startNodeId = startNode.getId();
		this.endNodeId = endNode.getId();
		Graph context = new Graph (edges);
		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}
		
		Vertex source= new Vertex(); 
		source=	context.getGraph().get(startNodeId);  //point to the same object
		Vertex destination =new Vertex();
		destination = context.getGraph().get(endNodeId);
		//NavigableSet<Vertex> q = new TreeSet<>();
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Double.MAX_VALUE);
			q.add(v);
			//System.out.println("+++"+v.id);
			//System.out.println(q.size());
		}

		dijkstra(q);
		
		Vertex d= new Vertex();
		d=destination;
		//System.out.println(d.getDist());
		Stack<Node> result= new Stack<Node>();
		do{
			result.push(d);
			d=d.getPrevious();
		}while(d!=source) ;
		result.push(source);
		return result;
	}

	/** Implementation of dijkstra's algorithm using a binary heap. */
	private void dijkstra(final PriorityQueue<Vertex> q) {
		Vertex u, v;
		while (!q.isEmpty()) {

			u = q.poll(); // vertex with shortest distance (first iteration
								// will return source)
			//System.out.println("id is"+u.getId());
			//System.out.println(u.getDist());
			//System.out.println(u.getNeighborV().size());
			//System.out.println(this.endNodeId)
			if (u.getDist() == Double.MAX_VALUE)
				break; // we can ignore u (and any other remaining vertices)
						// since they are unreachable

			// look at distances to each neighbor
			for (Map.Entry<Vertex, Double> a : u.getNeighborV().entrySet()) {
				v = a.getKey(); // the neighbor in this iteration

				double alternateDist = u.getDist() + a.getValue();
				if (alternateDist < v.getDist()) { // shorter path to neighbor
													// found
					//System.out.println("+++++");
					q.remove(v);
					v.setDist(alternateDist);
					//System.out.println(v.getId());
					//System.out.println(v.getDist());
					v.setPrevious(u);
					q.add(v);
					//System.out.println(q.size());
				}
			}
		}
	}
}