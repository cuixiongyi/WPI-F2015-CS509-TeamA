package com.wpi.cs509.teamA.newfeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeName;
import com.wpi.cs509.teamA.util.Database;

public class SearchSupply {
	private Map<String, Node> allNodesWithName;
	private Map<Integer, String> allMaps;

	public SearchSupply() {
		initSearchSupply();
	}

	public void initSearchSupply() {
		
		allMaps = new HashMap<Integer,String>();
		allNodesWithName = new HashMap<String,Node>();
		
		// get maps
		Iterator<GeneralMap> iter_map = Database.getAllMapFromDatabase().iterator();
		while (iter_map.hasNext()) {
			GeneralMap tempMap = iter_map.next();
			int map_id = Integer.parseInt(tempMap.getMapId());
			String map_name = tempMap.getMapName();
		//	System.out.println("map_id :"+ map_id +"   map_name"+map_name);
			allMaps.put(map_id, map_name);
		}

		// get nodes and transfer
		Iterator<Node> iter = Database.getAllNodeFromDatabase().iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			String node_name = tempNode.getName();
			if(node_name.equals("Location"))
				continue;
			String map_name = allMaps.get(tempNode.getMapId());
		//	System.out.println("node_name :" + node_name+ "   map_name:"+map_name);
		//	allNodesWithName.put(node_name + " " + map_name, tempNode);
			allNodesWithName.put(map_name + " " + node_name, tempNode);
		}
	}

	public String getSearchPattern(String searchingStr) {
		searchingStr = searchingStr.toLowerCase();
		String newPattern = "(.*)";
		for (int i = 0; i < searchingStr.length(); i++) {
			newPattern += searchingStr.charAt(i) + "(.*)";
		}
		return newPattern;
	}

	public Map<String, Node> getSearchSupply(String searchingStr) {

		Map<String, Node> listResults = new HashMap<String, Node>();
		String newPattern = getSearchPattern(searchingStr);

		// create Pattern
		Pattern r = Pattern.compile(newPattern);

		for (Map.Entry<String, Node> entry : allNodesWithName.entrySet()) {
			String tempKey = entry.getKey();
			Node tempNode = entry.getValue();
		//	System.out.println("name:"+ tempKey);
			// create matcher
			Matcher m = r.matcher(tempKey.toLowerCase());
			if (m.find()) {
				listResults.put(tempKey, tempNode);
			}
		}
		return listResults;
	}

	public static void main(String[] args) {
		Database.InitFromDatabase();
	//	System.out.println(Database.getAllNodeFromDatabase().size());
		SearchSupply ss = new SearchSupply();
		Map<String,Node> getSS = ss.getSearchSupply("PC");
		for (String key : getSS.keySet()) {  
		    System.out.println("Key = " + key);  
		}  
	}
}
