package com.wpi.cs509.teamA.strategy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.*;
import com.wpi.cs509.teamA.strategy.impl.Vertex;

public class MapVertex extends GeneralMap implements Comparable<MapVertex> {
	private Integer id;
	private int dist = Integer.MAX_VALUE;
	private MapVertex previous = null;
	private List<MapVertex> neighborM = new ArrayList<>();
	private List<Edge> BoundaryEdges = new ArrayList<>();

	public MapVertex() {

	}

	public MapVertex(GeneralMap map) {
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

	public MapVertex getPrevious() {
		return previous;
	}

	public void setPrevious(MapVertex map) {
		this.previous = map;
	}

	public List<MapVertex> getNeighborM() {
		return neighborM;
	}

	public void setNeighborM(List<MapVertex> neighborM) {
		this.neighborM = neighborM;
	}

	public List<Edge> getBoundaryEdges() {
		return BoundaryEdges;
	}

	public void setBoundaryEdges(List<Edge> boundaryEdges) {
		BoundaryEdges = boundaryEdges;
	}

	public int compareTo(MapVertex other) {
		return Integer.compare(dist, other.dist);
	}

}
