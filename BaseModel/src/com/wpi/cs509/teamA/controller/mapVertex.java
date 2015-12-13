package com.wpi.cs509.teamA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.strategy.impl.Vertex;

public class mapVertex extends GeneralMap implements Comparable<mapVertex> {
	private Integer id;
	private int dist = Integer.MAX_VALUE;
	private mapVertex previous = null;
	private List<mapVertex> neighborM = new ArrayList<>();
	private List<Edge> BoundaryEdges = new ArrayList<>();

	public mapVertex() {

	}

	public mapVertex(GeneralMap map) {
		this.id = (Integer) map.getMapId();
		this.BoundaryEdges = map.getBoundaryEdges();
	}

	@Override
	public int getMapId() {
		return id;
	}

	public void setMapId(int id) {
		this.id = id;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public mapVertex getPrevious() {
		return previous;
	}

	public void setPrevious(mapVertex map) {
		this.previous = map;
	}

	public List<mapVertex> getNeighborM() {
		return neighborM;
	}

	public void setNeighborM(List<mapVertex> neighborM) {
		this.neighborM = neighborM;
	}

	public List<Edge> getBoundaryEdges() {
		return BoundaryEdges;
	}

	public void setBoundaryEdges(List<Edge> boundaryEdges) {
		BoundaryEdges = boundaryEdges;
	}

	public int compareTo(mapVertex other) {
		return Integer.compare(dist, other.dist);
	}

}
