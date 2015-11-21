package com.wpi.cs509.teamA.strategy.impl;
import com.wpi.cs509.teamA.bean.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

/**
 * A* strategy
 * 
 * @author CS 509-Team A
 *
 */
public class AstarAlgoStrategy{
	private int startNodeId;
	private int endNodeId;


	public Stack<Node> getRoute(Node startNode, Node endNode, Edge[] edges) {
		this.startNodeId = startNode.getId();
		this.endNodeId = endNode.getId();
		Graph context = new Graph (edges); 
		HashMap<Integer, Vertex> graph = context.getGraph();
		if (!graph.containsKey(startNodeId)) {
			System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
			// return 0;
		}
		//get the vertex of startNode from integer startNodeId;
		Vertex source= new Vertex(); 
		source=	context.getGraph().get(startNodeId);  //point to the same object
		Vertex destination =new Vertex();
		destination = context.getGraph().get(startNodeId);;
		
		//NavigableSet<Vertex> q = new TreeSet<>();
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		
		// set-up vertices: only info of previous; other info given by graph nature
		//q is now the unvisited set of vertices
		for (Vertex v : graph.values()) {
			v.setPrevious((v == source) ? source : null);
			v.setDist((v == source) ? 0 : Integer.MAX_VALUE);
			v.setGcost((v == source) ? 0 : Integer.MAX_VALUE);
			//8System.out.println(((Node)v).DistanceTo(destination));
			//cannot be calculated on different maps
			v.setHcost((v==destination)? 0 : v.DistanceTo(destination));
			q.add(v);
			//System.out.println("+++"+v.id);
			//System.out.println(q.size());
			
		}

		//System.out.println(graph.size());
		//System.out.println(q.size());
		astar(q);
		
		Vertex d= new Vertex();
		d=destination;
		//System.out.println(d.getDist());
		Stack<Node> result= new Stack<Node>();
		do{
			result.push(d);
			d=d.getPrevious();
		}while(d!=source) ;
		result.push(source);
		//return context.getPath(this.endNodeId);
		return result;
	}
	
	//this function should be included in the node class
//	private Integer getDistance(Vertex a, Vertex b){
//		//System.out.println(a.getCoordinate().getY());
//		return (int) Math.sqrt(Math.pow(a.getCoordinate().getX()-b.getCoordinate().getX(),2)+Math.pow(a.getCoordinate().getY()-b.getCoordinate().getY(),2));
//	}

	/** Implementation using a binary heap. */
	private void astar(final PriorityQueue<Vertex> q) {
		Vertex u, v;
		//System.out.println(q.size());
		//need to add not found exception
		while (!q.isEmpty()) {
			
			u = q.poll(); // vertex with shortest distance (first iteration
								// will return source)
								//u removed from set here
			//System.out.println(u.getId());
			//System.out.println(u.getDist()+" "+u.getGcost()+ " "+ u.getHcost());
			//System.out.println(u.getNeighborV().size());
			//System.out.println(this.endNodeId);
			if(u.getId()==this.endNodeId)
			{	
				//System.out.println(u.id);
				//System.out.println(u.getPrevious().id);
				break;}
			
			if (u.getDist() == Integer.MAX_VALUE)
				break;// we can ignore u (and any other remaining vertices)
						// since they are unreachable

			// look at distances to each neighbor
			
			for (Map.Entry<Vertex, Integer> a :  u.getNeighborV().entrySet()) {
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
					//System.out.println(q.size());
				}
			}
		}
	}
}

