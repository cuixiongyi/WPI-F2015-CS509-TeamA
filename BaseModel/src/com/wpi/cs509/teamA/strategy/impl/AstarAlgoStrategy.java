package com.wpi.cs509.teamA.strategy.impl;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.model.AlgoModel;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.strategy.datastructure.Graph;
import com.wpi.cs509.teamA.strategy.datastructure.MapVertex;
import com.wpi.cs509.teamA.strategy.datastructure.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * A* strategy
 * 
 * @author CS 509-Team A
 * 
 */
public class AstarAlgoStrategy implements AlgoStrategy {
	private Node startNode;
	private Node endNode;
	private int startNodeId;
	private int endNodeId;

	public Stack<Node> getRoute(AlgoModel alledges) {
		this.startNode = alledges.getStartNode();
		this.endNode = alledges.getEndNode();
		List<Node> l = new ArrayList<Node>();
		Stack<Node> result = new Stack<Node>();
		//alledges.init();
		if (!alledges.isMultipleMap()) {
			l.add(endNode);
			Graph context = new Graph(alledges.getEdgesOnMap(startNode.getMap()));
			return astarRoute(this.startNode, l, context);
		}

		@SuppressWarnings("unchecked")
		Stack<MapVertex> s = (Stack<MapVertex>) alledges.getMapsOnPath().clone();
		MapVertex m = new MapVertex();
		while (!s.isEmpty()) {
			Stack<Node> r = new Stack<Node>();
			m = s.pop();
			// System.out.println("the number of edges on map is
			// "+alledges.getEdgesOnMap(m).size());
			Graph context = new Graph(alledges.getEdgesOnMap(m));
			if (m.getMapId() == endNode.getMap().getMapId()) { // this is the
																// last map
				l.add(endNode);
			} else {
				l = alledges.getBoundaryNodes(m, s.peek());
			}
			// System.out.println("+++++++++++++");
			// System.out.println(l.size());

			r = astarRoute(this.startNode, l, context);
			l.clear();
			startNode = alledges.getOtherNode(r.firstElement());
			System.out.println("last on path is " + r.firstElement().getId());
			//System.out.println("next start is "+startNode.getId());
			r.addAll(result);
			result = r;
		}
		return result;
	}

	public Stack<Node> astarRoute(Node startNode, List<Node> endNode, Graph context) {
		{
			this.startNodeId = startNode.getId();
			// this.endNodeId = endNode.getId();

			HashMap<Integer, Vertex> graph = context.getGraph();
			if (!graph.containsKey(startNodeId)) {
				System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
				// return 0;
			}
			// get the vertex of startNode from integer startNodeId;
			Vertex source = new Vertex();
			source = context.getGraph().get(startNodeId); // point to the same
															// object
			Vertex[] destination = new Vertex[endNode.size()];
			for (int i = 0; i < destination.length; i++) {
				destination[i] = context.getGraph().get(endNode.get(i).getId());
			}

			PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();

			// set-up vertices: only info of previous; other info given by graph
			// nature
			// q is now the unvisited set of vertices
			for (Vertex v : graph.values()) {
				v.setPrevious((v == source) ? source : null);
				v.setDist((v == source) ? 0 : Integer.MAX_VALUE);
				v.setGcost((v == source) ? 0 : Integer.MAX_VALUE);
				boolean flag = true;
				for (Vertex x : destination) { // if v is one of the
												// destinations
					if (v == x) {
						v.setHcost(0);
						flag = false;
						break;
					}
				}
				if (flag) {
					double i = 0;
					for (Vertex w : destination) {
						i += w.DistanceTo(v);
					}
					v.setHcost(i / destination.length);
				}

				// v.setHcost((v==destination)? 0 : v.DistanceTo(destination)));
				q.add(v);
				// System.out.println("+++"+v.id);
				// System.out.println(q.size());

			}

			// System.out.println(graph.size());
			// System.out.println(q.size());

			Vertex d = new Vertex();
			d = astar(q, destination);
			// System.out.println(d.getDist());
			Stack<Node> result = new Stack<Node>();
			do {
				result.push(d);
				d = d.getPrevious();
			} while (d != source);
			result.push(source);
			// return context.getPath(this.endNodeId);
			return result;
		}
	}

	private Vertex astar(final PriorityQueue<Vertex> q, Vertex[] destination) {
		Vertex u, v;
		// System.out.println(q.size());
		// need to add not found exception
		while (!q.isEmpty()) {

			u = q.poll(); // vertex with shortest distance (first iteration
							// will return source)
							// u removed from set here
			// System.out.println(u.getId());
			// System.out.println(u.getDist()+" "+u.getGcost()+ " "+
			// u.getHcost());
			// System.out.println(u.getNeighborV().size());
			// System.out.println(this.endNodeId);
			for (Vertex e : destination) {
				if (u.getId() == e.getId()) {
					return u;
				}
			}
			// if(u.getId()==this.endNodeId)
			// {
			// //System.out.println(u.id);
			// //System.out.println(u.getPrevious().id);
			// break;}

			if (u.getDist() == Integer.MAX_VALUE)
				return null;// we can ignore u (and any other remaining
							// vertices)
			// since they are unreachable

			// look at distances to each neighbor

			for (Map.Entry<Vertex, Double> a : u.getNeighborV().entrySet()) {
				v = a.getKey(); // the neighbor in this iteration

				// if(!q.contains(v))
				// continue;
				double alternategCost = u.getGcost() + a.getValue();
				double alternateDist = alternategCost + a.getKey().getHcost();

				if (alternateDist < v.getDist()) { // shorter path to neighbor
													// found
					q.remove(v);
					v.setGcost(alternategCost);
					v.setDist(alternateDist);
					v.setPrevious(u);
					q.add(v);
					// System.out.println(q.size());
				}
			}
		}
		return null;
	}
}
