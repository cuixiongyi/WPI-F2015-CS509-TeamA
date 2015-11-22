package com.wpi.cs509.teamA.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.MapDao;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.impl.MapDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;

public class Database {
	private static Map<Integer,Node> allNodesDataHM;
	private static Map<Integer,GeneralMap> allMapDataHM;
	private static List<Node> allNodesDataHL;
	private static List<GeneralMap> allMapDataHL;
	private static List<Edge> allEdgesHL;
	private static HashMap<Integer, List<Edge>> allEdgesDataHM;
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
            tempMap.readImage();
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
		
		// get all edges from database
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		allEdgesHL = nrd.getAllEdges();
		allEdgesDataHM = new HashMap<Integer,List<Edge>>();
		Iterator<Integer> mapIds = allMapDataHM.keySet().iterator();
		while(mapIds.hasNext()){
			int temp_map_id = mapIds.next();
			allEdgesDataHM.put(temp_map_id, nrd.getAllNodeRelationsForCurrentMap(temp_map_id));
			
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
		return allNodesDataHM.get(nodeId).getLocation();
	}
	
	public static Node getNodeFromName(String node_name){
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if(tempNode.getName().equals(node_name))
				return tempNode;
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
	
	/**Deal with Edges*/
	public static List<Edge>getAllEdges(){
		return allEdgesHL;
	}
	
	public static List<Edge>getAllEdgesForCurrentMap(int map_id){
		return allEdgesDataHM.get(map_id);
	}
	
	
}
