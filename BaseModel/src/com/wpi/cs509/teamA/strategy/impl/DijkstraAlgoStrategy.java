package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.strategy.AlgoStrategy;
import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * Dijkstra Strategy
 * 
 * @author CS 509-Team A
 *
 */
public class DijkstraAlgoStrategy implements AlgoStrategy 
{		 
			private List<Integer> routeNodeIds;
			int startNodeId;
			int endNodeId;
	
		   @Override
		   public List<Integer> getRoute(Node startNode, Node endNode, Graph context) 
		   {
			  this.startNodeId = startNode.getId();
			  this.endNodeId = endNode.getId();
			  
			  HashMap<Integer, Vertex> graph = context.getGraph();
		      if (!graph.containsKey(startNodeId)) 
		      {
		         System.err.printf("Graph doesn't contain start vertex \"%d\"\n", startNodeId);
		         //return 0;
		      }
		      final Vertex source = graph.get(startNodeId);
		      NavigableSet<Vertex> q = new TreeSet<>();
		 
		      // set-up vertices
		      for (Vertex v : graph.values()) 
		      {
		         v.previous = v == source ? source : null;
		         v.dist = v == source ? 0 : Integer.MAX_VALUE;
		         q.add(v);
		      }
		 
		      dijkstra(q);
		      routeNodeIds = context.getPath(endNodeId);
		   }
		 
		   /** Implementation of dijkstra's algorithm using a binary heap. */
		   private void dijkstra(final NavigableSet<Vertex> q) 
		   {      
		      Vertex u, v;
		      while (!q.isEmpty()) 
		      {
		 
		         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
		         if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
		 
		         //look at distances to each neighbor
		         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) 
		         {
		            v = a.getKey(); //the neighbor in this iteration
		 
		            final int alternateDist = u.dist + a.getValue();
		            if (alternateDist < v.dist) 
		            { // shorter path to neighbor found
		               q.remove(v);
		               v.dist = alternateDist;
		               v.previous = u;
		               q.add(v);
		            } 
		         }
		      }
		   }
}