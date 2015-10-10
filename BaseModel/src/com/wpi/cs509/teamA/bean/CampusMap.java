package com.wpi.cs509.teamA.bean;

import java.util.HashSet;
import java.util.Set;

import com.wpi.cs509.teamA.util.InputMatrix;

/**
 * marks all the buildings and other facilities in campus
 * 
 * @author CS 509-Team A
 * @version Oct 5th
 */
public class CampusMap extends GeneralMap {

	/** the set for outdoor places such as parking lot and fountain */
	private Set<Node> outdoorPlaces = new HashSet<Node>();

	/** the set of building entrance */
	private Set<Node> buildingEntrances = new HashSet<Node>();

	/**
	 * the implementation of getting an adjacency matrix
	 */
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		
		System.out.println("this method is from campus map itself!");
		
		return null;
	}
	
	/** building entrance set getter */
	public Set<Node> getBuildingEntrances() {
		return buildingEntrances;
	}

	/** building entrance set setter */
	public void setBuildingEntrances(Set<Node> buildingEntrances) {
		this.buildingEntrances = buildingEntrances;
	}

	/** outdoor places set getter */
	public Set<Node> getOutdoorPlaces() {
		return outdoorPlaces;
	}

	/** outdoor places set setter */
	public void setOutdoorPlaces(Set<Node> outdoorPlaces) {
		this.outdoorPlaces = outdoorPlaces;
	}

}
