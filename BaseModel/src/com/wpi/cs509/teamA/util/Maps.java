package com.wpi.cs509.teamA.util;

/**
 * This enum class contains all the map name and its id. The map information in
 * the database will be the same as this enum class
 * 
 * @author CS 509-Team A
 *
 */
public enum Maps {

	// lists for maps as an example
	CAMPUS("campus", 0), FL1("fuller_1", 1), FL2("fuller_2", 2), FL3("fuller_3", 3);

	/**
	 * The map name in the database
	 */
	private String dataBaseMapName;
	/**
	 * the id of the map, same as the id of the map in the database
	 */
	private int mapId;

	/**
	 * Constructor for a map
	 * 
	 * @param dataBaseMapName
	 *            the name of the map in the database
	 * @param mapId
	 *            the id of the map in the database
	 */
	private Maps(String dataBaseMapName, int mapId) {
		this.dataBaseMapName = dataBaseMapName;
		this.mapId = mapId;
	}

	/**
	 * @return the dataBaseMapName
	 */
	public String getDataBaseMapName() {
		return dataBaseMapName;
	}

	/**
	 * @return the mapId
	 */
	public int getMapId() {
		return mapId;
	}

}
