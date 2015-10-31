package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Graph 
{
   private final HashMap<Integer, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

 
   /** Builds a graph from a set of edges */
   public Graph(Edge[] edges) 
   {
      graph = new HashMap<>(edges.length);
 
      //one pass to find all vertices
      for (Edge e : edges) 
      {
         if (!graph.containsKey(e.id1)) graph.put(e.id1, new Vertex(e.id1));
         if (!graph.containsKey(e.id2)) graph.put(e.id2, new Vertex(e.id2));
      }
 
      //another pass to set neighbouring vertices
      for (Edge e : edges) 
      {
         graph.get(e.id1).neighbours.put(graph.get(e.id2), e.dist);
         //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
      }
   }
 
 public List<Integer> getPath(int endId)
 {
	 if (!graph.containsKey(endId)) 
     {
        System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endId);
        return null;
     }

	 graph.get(endId).getNext();
    return graph.get(endId).getPath(); // return list of node ids that make up the path
 }
   
   /** Prints a path from the source to the specified vertex */
   public void printPath(int endId) 
   {
      if (!graph.containsKey(endId)) 
      {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endId);
         return;
      }
 
      graph.get(endId).printPath();
      System.out.println();
   }
   
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() 
   {
      for (Vertex v : graph.values()) 
      {
         v.printPath();
         System.out.println();
      }
   }
   
   public HashMap<Integer, Vertex> getGraph()
   {
	   return graph;
   }
}
