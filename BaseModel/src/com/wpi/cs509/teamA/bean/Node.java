package com.wpi.cs509.teamA.bean;

import java.util.HashMap;
import java.util.Map;

import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.NodeType;

/**
 * Node definition. This class represents the smallest unit of a map. It include
 * all the information we need on a point of the map.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class Node {

	/** id of the node */
	private int id;

	/** name of the node */
	private String name;

	/** the coordinate of the node */
	private Coordinate location;

	// which map this node belongs to
	/** define which map the node belongs to */
	private int mapId;

	// neighbor node id and distance
	// private Map<String, Integer> neighbors;
	/** neighbor node */
	//
	private Map<Integer,Double> neighbors = new HashMap<Integer,Double>();

	/** type of the node */
	// this may cause great complex when input data
	private NodeType nodeType;

	/** default constructor */
	public Node() {

	}

	// TODO: We may remove this method in the future, for now it is just for
	// testing..
	public Node(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @param id
	 *            the id of the node in the database
	 * @param name
	 *            the name of the node in the database
	 * @param x
	 *            coordinate x
	 * @param y
	 *            coordinate y
	 * @param mapId
	 *            the map where the node is on
	 * @param neighbors
	 *            the neighbors this node has
	 * @param nodeType
	 *            the type of the node, an enum class
	 */
	public Node(int id, String name, int x, int y, int mapId, Map<Integer,Double> neighbors, String nodeType) {

		this.id = id;
		this.name = name;
		this.location = new Coordinate(x, y);
		this.mapId = mapId;
		this.neighbors = neighbors;
		this.nodeType = NodeType.valueOf(nodeType);

	}

	/**
	 * add an new node on the map and set it's attributes
	 */
	public void saveNode() {
		Node node = new Node();
		node.setNeighbors(new HashMap<Integer,Double>());
	//	node.setId(1);
		// more setters for the node

		// add node id as the neighbours

	//	node.getNeighbors().add(2);
	//	node.getNeighbors().add(3);
	//	node.getNeighbors().add(4);

	}

	// add neighbors to the node
	/** add neighbors to the node */
	public void addNeighbour(String nodeId) {

	}

	// delete the node itself
	// it will also delete this node from all its neighbours
	/**
	 * delete the node itself and also delte this node from all its neighbors
	 */
	public void deleteNode() {
		// deleteFromNeighbour
		// this.deleteFromNeighbour(this.id);

	}

	/**
	 * delete the node from one of it's neighbor
	 * 
	 * @param nodeId
	 *            the neighbor id
	 */
	private void deleteFromNeighbour(String nodeId) {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public Coordinate getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Coordinate location) {
		this.location = location;
	}

	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}

	/**
	 * @param mapId
	 *            the mapId to set
	 */
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	/**
	 * @return the neighbors
	 */
	public Map<Integer,Double> getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors
	 *            the neighbors to set
	 */
	public void setNeighbors(Map<Integer,Double> neighbors) {
		this.neighbors = neighbors;
	}

	/**
	 * @return the nodeType
	 */
	public NodeType getNodeType() {
		return nodeType;
	}

	/**
	 * @param nodeType
	 *            the nodeType to set
	 */
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

}
