package com.wpi.cs509.teamA.dao.impl;

import java.util.Map;

import com.wpi.cs509.teamA.dao.InitAllMatrix;
import com.wpi.cs509.teamA.util.AdjacencyMatrix;
import com.wpi.cs509.teamA.util.InputMatrix;
import com.wpi.cs509.teamA.util.Maps;
import com.wpi.cs509.teamA.util.ProxyMap;

// I will handle all the stuff related to this class -- Jie
/**
 * This class must be a singleton since the map should be only initialized once
 * 
 * @author CS 509-Team A
 *
 */
public class InitAllMatrixImpl implements InitAllMatrix {

	private static InitAllMatrixImpl initAllMatrixImpl;

	/**
	 * The map id and its corresponding map matrix
	 */
	private Map<Integer, InputMatrix> allMatrix;

	private InitAllMatrixImpl() {
		// we init all the matrix here
		// traverse all the maps in the Maps enum class
		System.out.println("initializing all the maps.. should happen only once..");
		for (Maps map : Maps.values()) {
			// get the matrix according to the info from database
			AdjacencyMatrix tempMap = new ProxyMap(map.getDataBaseMapName());
			// get the matrix
			InputMatrix im = tempMap.getAdjacencyMatrix();
			int mapId = map.getMapId();
			// result.put(mapId, im);
		}
	}

	public static InitAllMatrixImpl getInitAllMatrixImpl() {
		if (initAllMatrixImpl == null) {
			initAllMatrixImpl = new InitAllMatrixImpl();
		}

		return initAllMatrixImpl;

	}

	@Override
	public Map<Integer, InputMatrix> initAllMatrix() {
		// TODO Auto-generated method stub
		System.out.println("try to get all matrix..");
		return allMatrix;
	}

}