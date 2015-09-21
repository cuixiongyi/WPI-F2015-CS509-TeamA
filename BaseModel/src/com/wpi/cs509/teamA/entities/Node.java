package com.wpi.cs509.teamA.entities;

import java.util.Set;

import com.wpi.cs509.teamA.misc.Coordinate;
import com.wpi.cs509.teamA.misc.NodeType;

public class Node {
	
	private String id;
	private String name;
	private Coordinate location;
	// which map this node belongs to
	private String mapId;
	// neighbor node id and distance
	// private Map<String, Integer> neighbors;
	private Set<String> neighbors;
	private NodeType nodeType;
	// Activity will contain location
	// many activities can be hold in one place
	// private Activity activity;
	private boolean isEntrance;
	
	public Node(){
		
	}
	
	// add neighbors to the node
	public void addNeighbour(String nodeId){
		
	}
	
	// delete the node itself
	// it will also delete this node from all its neighbours
	public void deleteNode(){	
		// deleteFromNeighbour
		// this.deleteFromNeighbour(this.id);
		
	}
	
	//
	private void deleteFromNeighbour(String nodeId){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	
	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public Set<String> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(Set<String> neighbors) {
		this.neighbors = neighbors;
	}

	public boolean isEntrance() {
		return isEntrance;
	}

	public void setEntrance(boolean isEntrance) {
		this.isEntrance = isEntrance;
	}
	

}
