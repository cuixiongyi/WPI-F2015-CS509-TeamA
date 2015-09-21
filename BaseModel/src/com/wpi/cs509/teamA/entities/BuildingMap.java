package com.wpi.cs509.teamA.entities;

// marks all the places in a building
public class BuildingMap extends RoadMap{
	
	private String buildingName;

	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
