package com.wpi.cs509.teamA.bean;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.*;
import java.util.HashSet;
import java.util.Set;





/**
 * Node definition. This class represents the smallest unit of a map. It include
 * all the information we need on a point of the map.
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class Node {

	/** id of the node */
	protected int id;

	/** name of the node */
	private String name;

	/** the coordinate of the node */
	protected Coordinate location;

	// which map this node belongs to
	/** define which map the node belongs to */
	protected GeneralMap map;

	// neighbor node id and distance
	// private Map<String, Integer> neighbors;
	/** neighbor node */
	//
	//private Set<Node> neighbors = new HashSet<Node>();
	private Set<Edge>neighborE=new HashSet<Edge>();
	
	
	/** type of the node */
	// this may cause great complex when input data
	private NodeType nodeType;

    /** Display coordinate on map image */
    private Coordinate displayCoor;

	/** default constructor */
	public Node() {

	}

	// TODO: We may remove this method in the future, for now it is just for
	// testing..
//	public Node(int id, int x, int y) {
//		this.id = id;
//		this.location = new Coordinate(x,y);
//	}
	// TODO: We may remove this method in the future, for now it is just for
	// testing..
	public Node(int id, int x, int y, GeneralMap mapId) {
		this.id = id;
		this.location = new Coordinate(x,y);
		this.map = mapId;
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
        this.displayCoor = new Coordinate(x, y);
	}

	/**
	 * add an new node on the map and set it's attributes
	 */
	public void saveNode() {
		NodeDao nd = new NodeDaoImpl();
		nd.saveNode(this);
//		Node node = new Node();
//		node.setNeighbors(new HashSet<Integer>());
//		node.setId(1);
//		// more setters for the node
//
//		// add node id as the neighbours
//
//		node.getNeighbors().add(2);
//		node.getNeighbors().add(3);
//		node.getNeighbors().add(4);
		
		// database
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
	public void setNeighborE(Set<Edge>neighbors) {
		this.neighborE = neighbors;
	}
	public void addNeighborE(Edge e){
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

/*	public int DistanceTo(Node a){
		return this.getMap().getScale()*(int) Math.sqrt(Math.pow(a.getLocation().getX()-this.getLocation().getX(),2)+Math.pow(a.getLocation().getY()-this.getLocation().getY(),2));
	}*/
}	
