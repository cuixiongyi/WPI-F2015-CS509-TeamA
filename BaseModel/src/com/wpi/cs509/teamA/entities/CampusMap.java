package com.wpi.cs509.teamA.entities;

import java.util.Set;
/**
 * marks all the buildings and other facilities in campus
 * @author CS 509-Team A 
 * @version Oct 5th
*/
// marks all the buildings and other facilities in campus
public class CampusMap extends GeneralMap{

	// parking lot, fountain
	/**the set for parking lot and foutatin*/
	private Set<Node> outdoorPlaces;
	
	/**the set of building entrance*/
	private Set<Node> buildingEntrances;

	/**building entrance set getter*/
	public Set<Node> getBuildingEntrances() {
		return buildingEntrances;
	}

	/**building entrance set setter*/
	public void setBuildingEntrances(Set<Node> buildingEntrances) {
		this.buildingEntrances = buildingEntrances;
	}

	/**outdoor places set getter*/
	public Set<Node> getOutdoorPlaces() {
		return outdoorPlaces;
	}

	/**outdoor places set setter*/
	public void setOutdoorPlaces(Set<Node> outdoorPlaces) {
		this.outdoorPlaces = outdoorPlaces;
	}
		
}
