package com.wpi.cs509.teamA.entities;

import java.util.Set;

public class RoadMap {

	private String mapId;
	private int Scale;

	// the place that can arrive in the map
	// in spite of it is a building or campus
	protected Set<Node> roadMap;

	// protected Set<Activity> activities;

	// add node to map
	protected void addNode(String nodeId) {

	}

	// delete node from set
	protected void deleteNode(String nodeId) {
		// this.deleteNode();
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public Set<Node> getRoadMap() {
		return roadMap;
	}

	public void setRoadMap(Set<Node> roadMap) {
		this.roadMap = roadMap;
	}
	
	public int getScale() {
		return Scale;
	}

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
