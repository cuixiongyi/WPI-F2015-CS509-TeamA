package com.wpi.cs509.teamA.strategy.impl;

import java.util.Map;
import java.util.PriorityQueue;

/*Takes one source and one graph
 * run Dijkstra
 */

public class Dijkstra {
	PriorityQueue<Vertex> q;
	//HashMap<Integer, Vertex> g;
	
	public Dijkstra(Map<Integer, Vertex> graph, Vertex source){
		q= new PriorityQueue<Vertex>();
		//g=(HashMap<Integer, Vertex>) graph;
		
		// set-up vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Double.MAX_VALUE);
			q.add(v);
			//System.out.println("+++"+v.id);
			//System.out.println(q.size());
		}
		
		dijkstra(q);
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

