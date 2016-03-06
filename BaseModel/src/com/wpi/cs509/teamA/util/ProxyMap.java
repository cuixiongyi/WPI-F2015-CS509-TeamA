package com.wpi.cs509.teamA.util;

import com.wpi.cs509.teamA.persistence.bean.GeneralMap;
import com.wpi.cs509.teamA.persistence.bean.Node;

/**
 * We decided to use Proxy Pattern to provide distributed control over all map
 * class instances. This allows us to load information for each map as we need
 * it. For instance, a matrix representation of each map must be created in
 * order to input map data to the algorithm. In our design, both the ProxyMap
 * and the GeneralMap implement the AdjacencyMatrix interface. The proxy acts as
 * a representative for the GeneralMap. It defers the creation of the GeneralMap
 * until it is needed. The proxy also acts as a surrogate for the GeneralMap
 * before and while it is being created. After that, the proxy forwards requests
 * directly to the GeneralMap.
 * 
 * @author CS 509-Team A
 *
 */
public class ProxyMap implements AdjacencyMatrix {

	/**
	 * Map class
	 */
	private GeneralMap generalMap;
	/**
	 * Name of the map
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
	 * 
	 * @param mapName
	 */
	public ProxyMap(String mapName) {
		this.mapName = mapName;
		System.out.println("you are using  ProxyMap to init " + mapName + "..");
	}

}
