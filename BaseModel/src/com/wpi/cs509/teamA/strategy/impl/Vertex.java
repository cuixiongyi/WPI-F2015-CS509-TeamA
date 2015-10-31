package com.wpi.cs509.teamA.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex implements Comparable<Vertex> 
{
	public final int id;
	public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();
	private List<Integer> pathNodeIds;
	
	public Vertex(int id) 
	{
		this.id = id;
	}
	
	public List<Integer> getPath()
	{
		return pathNodeIds;
	}
	
	public int getNext()
	{
		if (this == this.previous) 
		{
			System.out.printf("%s", this.id);
			return this.id;
		} 
		else if (this.previous == null) 
		{
			System.out.printf("%s(unreached)", this.id);
			return (Integer)null;
		} 
		else 
		{
			pathNodeIds.add(this.previous.getNext());
			return this.id;
		}
	}

	public void printPath()
	{
		if (this == this.previous) 
		{
			System.out.printf("%s", this.id);
		} 
		else if (this.previous == null) 
		{
			System.out.printf("%s(unreached)", this.id);
		} 
		else 
		{
			this.previous.printPath();
			System.out.printf(" -> %s(%d)", this.id, this.dist);
		}
	}

	public int compareTo(Vertex other) 
	{
		return Integer.compare(dist, other.dist);
	}
}