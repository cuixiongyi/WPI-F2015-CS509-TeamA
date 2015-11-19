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
	public Set<GeneralMap> getAllMaps();	
	
	/**
	 * get all maps ids in the database
	 * 
	 * @return a set of map ids
	 * 
	 */
	public List<GeneralMap> getAllMapIds();
	
}