package com.wpi.cs509.teamA.entities;


/**
 * mark all the places in a building
 * @author CS509-Team A
 * @version Oct 4th
 */
public class BuildingMap extends RoadMap{
	
	/** the name of the building*/
	private String buildingName;

	/**
	 * get the name of the building
	 * @author CS509-Team A
	 * @return name of the building
	 */
	public String getBuildingName() {
		return buildingName;
	}
	
	/**
	 * set the name of the building
	 * @author CS509-Team A
	 * @param name of the building
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
