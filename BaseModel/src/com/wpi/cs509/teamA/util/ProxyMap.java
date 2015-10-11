package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;

/**
 * Proxy map class, we init a matrix for a map from database by using this class
 * 
 * @author CS 509-Team A
 *
 */
public class ProxyMap implements AdjacencyMatrix {

	/**
	 * 
	 */
	private GeneralMap generalMap;
	/**
	 * 
	 */
	private String mapName;

	/**
	 * This is the proxy way of getting a matrix. It will use the method from
	 * generalMap to get the matrix. If the instance for generalMap is null,
	 * create a new generalMap. If not, just use the one that have already
	 * created.
	 */
	@Override
	public InputMatrix getAdjacencyMatrix() {
		// TODO Auto-generated method stub
		if (generalMap == null) {
			generalMap = new GeneralMap(mapName);
		}

		return generalMap.getAdjacencyMatrix();

	}
	
	/**
	 * This is the wrap class for initializing the map.
	 * @param mapName
	 */
	public ProxyMap(String mapName) {
		this.mapName = mapName;
		System.out.println("you are using  ProxyMap to init " + mapName + "..");
	}

}
