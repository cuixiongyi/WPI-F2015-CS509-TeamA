package com.wpi.cs509.teamA.bean;

import java.util.Set;

import com.wpi.cs509.teamA.misc.Coordinate;
import com.wpi.cs509.teamA.misc.NodeType;

/**
 * @author JLou
 *
 */
public class GateNode extends Node{

	/**
	 * the same position in another map. The id of that map.
	 */
	private String correspondingMapId;
	/**
	 * the same location in another map. The location in that map.
	 */
	private Coordinate correspondingLocation;
	
	/*
	 * 
	 */
	public GateNode(){
		
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param x
	 * @param y
	 * @param mapId
	 * @param neighbors
	 * @param nodeType
	 * @param correspondingMapId
	 * @param correspondingLocation
	 */
	public GateNode(int id, String name, int x, int y, String mapId, Set<Integer> neighbors, String nodeType, String correspondingMapId, Coordinate correspondingLocation){
		
		super(id, name, x, y, mapId, neighbors, nodeType);
		this.correspondingMapId = correspondingMapId;
		this.correspondingLocation = correspondingLocation;
		
	}
	
	/**
	 * @return the correspondingMapId
	 */
	public String getCorrespondingMapId() {
		return correspondingMapId;
	}
	/**
	 * @param correspondingMapId the correspondingMapId to set
	 */
	public void setCorrespondingMapId(String correspondingMapId) {
		this.correspondingMapId = correspondingMapId;
	}
	/**
	 * @return the correspondingLocation
	 */
	public Coordinate getCorrespondingLocation() {
		return correspondingLocation;
	}
	/**
	 * @param correspondingLocation the correspondingLocation to set
	 */
	public void setCorrespondingLocation(Coordinate correspondingLocation) {
		this.correspondingLocation = correspondingLocation;
	}
	
	
	/**node id getter*/
	public int getId() {
		return super.getId();
	}

	/**node id setter*/
	public void setId(int id) {
		super.setId(id);;
	}

	/**node name getter*/
	public String getName() {
		return super.getName();
	}

	/**node name setter*/
	public void setName(String name) {
		super.setName(name);;
	}

	/**node coordinate getter*/
	public Coordinate getLocation() {
		return super.getLocation();
	}

	/**node coordinate setter*/
	public void setLocation(Coordinate location) {
		super.setLocation(location);;
	}

	/**node type getter*/
	public NodeType getNodeType() {
		return super.getNodeType();
	}

	/**node type setter*/
	public void setNodeType(NodeType nodeType) {
		super.setNodeType(nodeType);;
	}
	
	/**node map id getter*/
	public String getMapId() {
		return super.getMapId();
	}

	/**node map id setter*/
	public void setMapId(String mapId) {
		super.setMapId(mapId);;
	}

	/**node neighbor getter*/
	public Set<Integer> getNeighbors() {
		return super.getNeighbors();
	}

	
	/**node neighbor setter*/ 
	public void setNeighbors(Set<Integer> neighbors) {
		super.setNeighbors(neighbors);;
	}

}
