package com.wpi.cs509.teamA.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.MapDao;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.MapDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;

public class Database {
	private static Map<Integer,Node> allNodesDataHM;
	private static Map<Integer,GeneralMap> allMapDataHM;
	private static List<Node> allNodesDataHL;
	private static List<GeneralMap> allMapDataHL;
	
//	private List<Edge> allEdgeData;
	
	public Database(){
		
	}
	
	public static void InitFromDatabase(){
		
		// get all maps from database
		MapDao md = new MapDaoImpl();
		allMapDataHL = md.getAllMaps();
		allMapDataHM = new HashMap<Integer,GeneralMap>();
		Iterator<GeneralMap> iterMap = allMapDataHL.iterator();
		while (iterMap.hasNext()) {
			GeneralMap tempMap = iterMap.next();
			allMapDataHM.put(tempMap.getMapId(), tempMap);
		}
		
		// get all nodes from database
		NodeDao nd = new NodeDaoImpl();
		allNodesDataHL = nd.getAllNodes();
		allNodesDataHM = new HashMap<Integer,Node>();
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			allNodesDataHM.put(tempNode.getId(), tempNode);
		}
	}
	
	public static List<Node> getAllNodeListFromDatabase(){
		return allNodesDataHL;
	}

	public static Node getNodeFromId(int nodeId) {
		return allNodesDataHM.get(nodeId);
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
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getMap().getMapId() == currentMapId)
				res.add(tempNode);
		}
		return res;
	}
	
	public static int getNodeIdFromName(String node_name){
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getName().equals(node_name))
				return tempNode.getId();
		}
		return -1;
	}
	
	public static Coordinate getNodeCoordinateFromId(int nodeId) {
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getId() == nodeId){
				return tempNode.getLocation();
			}
		}
		return null;
	}
	
	
	/**Deal with Maps*/
	
	public static List<GeneralMap> getAllMapFromDatabase(){
		return allMapDataHL;
	}
	public static GeneralMap getMapEntityFromMapId(int map_id){
		return allMapDataHM.get(map_id);
	}
	
}
