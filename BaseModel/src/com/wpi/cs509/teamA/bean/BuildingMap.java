package com.wpi.cs509.teamA.bean;

/**
 * mark all the places in a building
 * 
 * @author CS509-Team A
 * @version Oct 4th
 */
public class BuildingMap extends GeneralMap {

	/** the name of the building */
	private String buildingName;

	public BuildingMap() {
		System.out.println("initialize building map...");
	}

	/**
	 * get the name of the building
	 * 
	 * @return name of the building
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * set the name of the building
	 * 
	 * @param name
	 *            of the building
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
