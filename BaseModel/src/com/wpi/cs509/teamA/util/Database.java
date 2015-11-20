package com.wpi.cs509.teamA.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.MapDao;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.MapDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;

public class Database {
	private static List<Node> allNodesData;
	private static List<GeneralMap> allMapData;
	
//	private List<Edge> allEdgeData;
	
	public Database(){
		
	}
	
	public static void InitFromDatabase(){
		// get all nodes from database
		NodeDao nd = new NodeDaoImpl();
		allNodesData = nd.getAllNodes();
		
		// get all maps from database
		MapDao md = new MapDaoImpl();
		allMapData = md.getAllMaps();
		
	}
	
	public static List<Node> getAllNodeFromDatabase(){
		return allNodesData;
	}
	
	public static List<GeneralMap> getAllMapFromDatabase(){
		return allMapData;
	}
	public static Node getNodeFromId(int nodeId) {
		Iterator<Node> iter = allNodesData.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getId() == nodeId)
				return tempNode;
		}
		return null;
	}
	
	public static List<Node> getNodeFromIds(List<Integer> nodeIds){
		List<Node> res = new ArrayList<Node>();
		Iterator<Integer> iter = nodeIds.iterator();
		while (iter.hasNext()) {
			int nodeId = iter.next();
			res.add(Database.getNodeFromId(nodeId));
		}
		return res;
	}
	
	public static List<Node> getAllNodesForCurrentMap(int currentMapId) {
		List<Node> res =  new ArrayList<Node>();
		Iterator<Node> iter = allNodesData.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getMapId() == currentMapId)
				res.add(tempNode);
		}
		return res;
	}
	
	public static int getNodeIdFromName(String node_name){
		Iterator<Node> iter = allNodesData.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getName().equals(node_name))
				return tempNode.getId();
		}
		return -1;
	}
	
	public static Coordinate getNodeCoordinateFromId(int nodeId) {
		Iterator<Node> iter = allNodesData.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getId() == nodeId){
				return tempNode.getLocation();
			}
		}
		return null;
	}
}
