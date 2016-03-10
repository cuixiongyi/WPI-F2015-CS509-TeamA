package com.wpi.cs509.teamA.bean;

import com.wpi.cs509.teamA.util.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Node definition. Entity Class.
 * 
 * This class represents the smallest unit of a map. It include all the
 * information we need on a point of the map.
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

	/** define which map the node belongs to */
	private GeneralMap map;

	/** neighbor node */
	private Set<Edge> neighborE = new HashSet<Edge>();

	/** type of the node */
	private NodeType nodeType;

	/** default constructor */
	public Node() {

	}

	public Node(int id, int x, int y, GeneralMap map) {
		this.id = id;
		this.location = new Coordinate(x, y);
		this.map = map;
	}

	public double DistanceTo(Node a) {
		return this.getMap().getScale() * Math.sqrt(Math.pow(a.getLocation().getX() - this.getLocation().getX(), 2)
				+ Math.pow(a.getLocation().getY() - this.getLocation().getY(), 2));
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
	 * @param nodeType
	 *            the type of the node, an enum class
	 */
	public Node(int id, String name, int x, int y, GeneralMap map, String nodeType) {

		this.id = id;
		this.name = name;
		this.location = new Coordinate(x, y);
		this.map = map;
		this.nodeType = NodeType.valueOf(nodeType);
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
	public GeneralMap getMap() {
		return this.map;
	}

	/**
	 * @param mapId
	 *            the mapId to set
	 */
	public void setMap(GeneralMap map) {
		this.map = map;
	}

	/**
	 * @return the neighbors
	 */
	public Set<Edge> getNeighborE() {
		return neighborE;
	}

	/**
	 * @param neighbors
	 *            the neighbors to set
	 */
	public void setNeighborE(Set<Edge> neighbors) {
		this.neighborE = neighbors;
	}

	public void addNeighborE(Edge e) {
		this.neighborE.add(e);
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
