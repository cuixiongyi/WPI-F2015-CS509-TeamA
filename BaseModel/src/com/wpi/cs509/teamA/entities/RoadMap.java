package com.wpi.cs509.teamA.entities;

import java.util.Set;

/**Road map definition
 * @author CS 509-Team A 
 * @version Oct 5th*/

public class RoadMap {

	/**the map id*/
	private String mapId;
	/**the map scale*/
	private int Scale;

	// the place that can arrive in the map
	// in spite of it is a building or campus
	/**the place/node that can arrive in the map*/
	protected Set<Node> roadMap;

	// protected Set<Activity> activities;

	// add node to map
	/**add node to the map*/
	protected void addNode(String nodeId) {

	}

	// delete node from set
	/**delete node from the map set
	 * @param nodeId the id of the node*/
	protected void deleteNode(String nodeId) {
		// this.deleteNode();
	}

	/**map id getter*/
	public String getMapId() {
		return mapId;
	}

	/**map id setter*/
	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	/**available node getter*/
	public Set<Node> getRoadMap() {
		return roadMap;
	}

	/**available node setter*/
	public void setRoadMap(Set<Node> roadMap) {
		this.roadMap = roadMap;
	}
	
	/**map scale getter*/
	public int getScale() {
		return Scale;
	}

	/**map scale setter*/
	public void setScale(int scale) {
		Scale = scale;
	}

	/*
	 * public Set<Activity> getActivities() { return activities; }
	 * 
	 * public void setActivities(Set<Activity> activities) { this.activities =
	 * activities; }
	 */

}
