package com.wpi.cs509.teamA.entities;

import java.util.HashSet;
import java.util.Set;

import com.wpi.cs509.teamA.misc.Coordinate;
import com.wpi.cs509.teamA.misc.NodeType;

public class Node {
	
	private int id;
	private String name;
	private Coordinate location;
	// which map this node belongs to
	private String mapId;
	// neighbor node id and distance
	// private Map<String, Integer> neighbors;
	private Set<Integer> neighbors;
	private NodeType nodeType;
	// Activity will contain location
	// many activities can be hold in one place
	// private Activity activity;
	private boolean isEntrance;
	
	public Node(){
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param x
	 * @param y
	 * @param mapId
	 * @param neighbors should be used as Node(..., new HashSet<String>(), ...)
	 * @param nodeType
	 * @param isEntrance
	 */
	
	public Node(int id, String name, int x, int y, String mapId, Set<Integer> neighbors, String nodeType, boolean isEntrance){
		
		this.id = id;
		this.name = name;
		this.location = new Coordinate(x, y);
		this.mapId = mapId;
		this.neighbors = neighbors;
		this.nodeType = NodeType.valueOf(nodeType);
		this.isEntrance = isEntrance;
		
	}
	
	public void saveNode(){
		Node node = new Node();
		node.setNeighbors(new HashSet<Integer>());
		node.setId(1);
		// more setters for the node
		
		
		// add node id as the neighbours
		
		node.getNeighbors().add(2);
		node.getNeighbors().add(3);
		node.getNeighbors().add(4);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Set<Integer> getNeighbors() {
		return neighbors;
	}

	
	// 
	public void setNeighbors(Set<Integer> neighbors) {
		this.neighbors = neighbors;
	}

	public boolean isEntrance() {
		return isEntrance;
	}

	public void setEntrance(boolean isEntrance) {
		this.isEntrance = isEntrance;
	}
	

}
