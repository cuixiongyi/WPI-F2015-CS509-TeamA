package com.wpi.cs509.teamA.dao;

import java.util.List;
import java.util.Set;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;

public interface MapDao {
	/**
	 * get all maps in the database
	 * 
	 * @return a set of maps
	 * 
	 */
	public List<GeneralMap> getAllMaps();	
	
	/**
	 * get all maps ids in the database
	 * 
	 * @return a set of map ids
	 * 
	 */
	public List<Integer> getAllMapIds();
	
	/**
	 * insert a new map in the database
	 */
	public void saveMap(String mapName,String mapAbbrName,String mapPathName, double mapScale);
}