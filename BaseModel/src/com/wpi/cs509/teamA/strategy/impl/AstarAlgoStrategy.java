package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;

/**
 * A* strategy
 * 
 * @author CS 509-Team A
 *
 */
public class AstarAlgoStrategy implements AlgoStrategy {
	private int startNodeId;
	private int endNodeId;

	@Override
	public List<Integer> getRoute(Node startNode, Node endNode, Graph context) {
		this.startNodeId = startNode.getId();
		this.endNodeId = endNode.getId();

		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}
		//get the vertex of startNode from integer startNodeId
		Vertex source = graph.get(startNodeId);
		Vertex destination=graph.get(endNodeId);
		
		NavigableSet<Vertex> q = new TreeSet<>();

		// set-up vertices: only info of previous; other info given by graph nature
		//q is now the unvisited set of vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Integer.MAX_VALUE);
			v.setGcost((v == source) ? 0 : Integer.MAX_VALUE);
			v.setHcost((v==destination)? 0 : this.getDistance(v,destination));
			//System.out.println(this.getDistance(v, destination));
			q.add(v);
			
		}

		//System.out.println(graph.size());
		astar(q);
		return context.getPath(this.endNodeId);
	}
	
	//this function should be included in the node class
	private Integer getDistance(Vertex a, Vertex b){
		//System.out.println(a.getCoordinate().getY());
		return (int) Math.sqrt(Math.pow(a.getCoordinate().getX()-b.getCoordinate().getX(),2)+Math.pow(a.getCoordinate().getY()-b.getCoordinate().getY(),2));
	}

	/** Implementation using a binary heap. */
	private void astar(final NavigableSet<Vertex> q) {
		Vertex u, v;
		
		//need to add not found exception
		while (!q.isEmpty()) {
			
			u = q.pollFirst(); // vertex with shortest distance (first iteration
								// will return source)
								//u removed from set here
			//System.out.println(u.getId());
			//System.out.println(u.getDist()+" "+u.getGcost()+ " "+ u.getHcost());

			if(u.getId()==this.endNodeId)
				break;
			
			if (u.getDist() == Integer.MAX_VALUE)
				break;// we can ignore u (and any other remaining vertices)
						// since they are unreachable

			// look at distances to each neighbor
			for (Map.Entry<Vertex, Integer> a : u.getNeighbours().entrySet()) {
				v = a.getKey(); // the neighbor in this iteration
				
				//if(!q.contains(v))
					//continue;
				int alternategCost = u.getGcost() + a.getValue();
				int alternateDist= alternategCost + a.getKey().getHcost();
				
				if (alternateDist < v.getDist()) { // shorter path to neighbor
													// found
					q.remove(v);
					v.setGcost(alternategCost);
					v.setDist(alternateDist);
					v.setPrevious(u);
					q.add(v);
				}
			}
		}
	}
}
