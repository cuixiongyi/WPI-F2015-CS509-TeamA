package com.wpi.cs509.teamA.bean;

import java.util.HashSet;
import java.util.Set;

import com.wpi.cs509.teamA.misc.Coordinate;
import com.wpi.cs509.teamA.misc.NodeType;
/**
 * Node definition
 * @author CS 509-Team A 
 * @version Oct 5th
*/
public class Node {
	
	/**id of the node*/
	private int id;
	
	/**name of the node*/
	private String name;
	
	/**the coordinate of the node*/
	private Coordinate location;
	
	// which map this node belongs to
	/**define which map the node belongs to*/
	private String mapId;
	
	// neighbor node id and distance
	// private Map<String, Integer> neighbors;
	/**neighbor node id and distance*/
	private Set<Integer> neighbors;
	
	/**type of the node*/
	private NodeType nodeType;
	// Activity will contain location
	// many activities can be hold in one place
	// private Activity activity;
	/**if the node is an entrance*/
	private boolean isEntrance;
	
	/**default constructor*/
	public Node(){
		
	}
	
	/**constructure with information
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
	
	/**add an new node on the map and set it's attributes*/
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
	/**add neighbors to the node*/
	public void addNeighbour(String nodeId){
		
	}
	
	// delete the node itself
	// it will also delete this node from all its neighbours
	/**delete the node itself and also delte this node from all its neighbors*/
	public void deleteNode(){	
		// deleteFromNeighbour
		// this.deleteFromNeighbour(this.id);
		
	}
	
	/**delete the node from one of it's neighbor
	 * @param nodeId the neighbor id*/
	private void deleteFromNeighbour(String nodeId){
		
	}

	/**node id getter*/
	public int getId() {
		return id;
	}

	/**node id setter*/
	public void setId(int id) {
		this.id = id;
	}

	/**node name getter*/
	public String getName() {
		return name;
	}

	/**node name setter*/
	public void setName(String name) {
		this.name = name;
	}

	/**node coordinate getter*/
	public Coordinate getLocation() {
		return location;
	}

	/**node coordinate setter*/
	public void setLocation(Coordinate location) {
		this.location = location;
	}

	/**node type getter*/
	public NodeType getNodeType() {
		return nodeType;
	}

	/**node type setter*/
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
	
	/**node map id getter*/
	public String getMapId() {
		return mapId;
	}

	/**node map id setter*/
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/**node neighbor getter*/
	public Set<Integer> getNeighbors() {
		return neighbors;
	}

	
	/**node neighbor setter*/ 
	public void setNeighbors(Set<Integer> neighbors) {
		this.neighbors = neighbors;
	}

	/**get if the node is an entrance*/
	public boolean isEntrance() {
		return isEntrance;
	}

	/**set the map as an entrance*/
	public void setEntrance(boolean isEntrance) {
		this.isEntrance = isEntrance;
	}
	

}
