package com.wpi.cs509.teamA.entities;

import java.util.Set;

// marks all the buildings and other facilities in campus
public class CampusMap extends RoadMap{

	// parking lot, fountain
	private Set<Node> outdoorPlaces;
	private Set<Node> buildingEntrances;

	public Set<Node> getBuildingEntrances() {
		return buildingEntrances;
	}

	public void setBuildingEntrances(Set<Node> buildingEntrances) {
		this.buildingEntrances = buildingEntrances;
	}

	public Set<Node> getOutdoorPlaces() {
		return outdoorPlaces;
	}

	public void setOutdoorPlaces(Set<Node> outdoorPlaces) {
		this.outdoorPlaces = outdoorPlaces;
	}
		
}
