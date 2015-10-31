package com.wpi.cs509.teamA.strategy.impl;

public class Edge 
{
   public final int id1, id2; // ID's of two nodes
   public final int dist; // distance between the two nodes
   public Edge(int id1, int id2, int dist) 
   {
      this.id1 = id1;
      this.id2 = id2;
      this.dist = dist;
   }
}