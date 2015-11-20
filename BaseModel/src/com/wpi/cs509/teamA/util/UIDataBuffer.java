package com.wpi.cs509.teamA.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;

public class UIDataBuffer {

	private static int currentMapId = 1;

	public static int getCurrentMapId() {
		return currentMapId;
	}

	public static void setCurrentMapId(int mapId) {
		currentMapId = mapId;
	}

	public static Map<Integer, List<Node>> getAllNodes() {
		// get node from db
		List<Node> allNodesInDB = new ArrayList<Node>();
		allNodesInDB.addAll(Database.getAllNodesForCurrentMap(UIDataBuffer.currentMapId));
		Map<Integer, List<Node>> resMap = new HashMap<Integer, List<Node>>();
		resMap.put(1, allNodesInDB);
		return resMap;
		// test data
		// Coordinate firstNodeCoordinate = new Coordinate();
		// firstNodeCoordinate.setX(202);
		// firstNodeCoordinate.setY(100);
		// Coordinate secondNodeCoordinate = new Coordinate();
		// secondNodeCoordinate.setX(123);
		// secondNodeCoordinate.setY(333);
		// Coordinate thirdNodeCoordinate = new Coordinate();
		// thirdNodeCoordinate.setX(345);
		// thirdNodeCoordinate.setY(254);
		// Coordinate fourthNodeCoordinate = new Coordinate();
		// fourthNodeCoordinate.setX(123);
		// fourthNodeCoordinate.setY(431);
		// Node n1 = new Node();
		// Node n2 = new Node();
		// Node n3 = new Node();
		// Node n4 = new Node();
		//
		// n1.setLocation(firstNodeCoordinate);
		// n2.setLocation(secondNodeCoordinate);
		// n3.setLocation(thirdNodeCoordinate);
		// n4.setLocation(fourthNodeCoordinate);
		//
		// List<Node> res = new ArrayList<Node>();
		// res.add(n1);
		// res.add(n2);
		// res.add(n3);
		// res.add(n4);
		//
		// Map<Integer, List<Node>> resMap = new HashMap<Integer, List<Node>>();
		// resMap.put(1, res);
		//
		// return resMap;
	}

	public static Set<NodeRelation> getAllEdges() {
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		Set<NodeRelation> res = nrd.getAllNodeRelationsForCurrentMap();
		return res;

		// Coordinate firstNodeCoordinate = new Coordinate();
		// firstNodeCoordinate.setX(202);
		// firstNodeCoordinate.setY(100);
		// Coordinate secondNodeCoordinate = new Coordinate();
		// secondNodeCoordinate.setX(123);
		// secondNodeCoordinate.setY(333);
		//
		// NodeRelation nr = new NodeRelation();
		// nr.setFirstNodeCoordinate(firstNodeCoordinate);
		// nr.setSecondNodeCoordinate(secondNodeCoordinate);
		//
		// Set<NodeRelation> res = new HashSet<NodeRelation>();
		// res.add(nr);
		//
		// return res;
	}

}
