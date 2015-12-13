package com.wpi.cs509.teamA.dao;

import java.util.List;
import com.wpi.cs509.teamA.bean.GeneralMap;

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

}