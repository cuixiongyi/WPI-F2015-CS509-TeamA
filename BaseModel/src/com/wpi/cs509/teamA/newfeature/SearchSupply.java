package com.wpi.cs509.teamA.newfeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.wpi.cs509.teamA.model.MainModel;
import com.wpi.cs509.teamA.persistence.bean.GeneralMap;
import com.wpi.cs509.teamA.persistence.bean.History;
import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.bean.NodeName;
import com.wpi.cs509.teamA.persistence.bean.UserAccount;
import com.wpi.cs509.teamA.persistence.dao.OtherFeatureDao;
import com.wpi.cs509.teamA.persistence.dao.impl.OtherFeatureDaoImpl;
import com.wpi.cs509.teamA.ui.controller.MouseActionStatePattern.MouseActionSelectNode;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.AutoSuggestUtil.SuggestorPainter.SuggestorEnum;

public class SearchSupply {
	public static String StringFilter(String str){
		Matcher m = null;
		Pattern p;
		try{
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\////]";
		p = Pattern.compile(regEx);
		
		m = p.matcher(str);
		}catch(PatternSyntaxException pse){
			System.out.println("Invalid char detected");
		}
		return m.replaceAll(" ").trim();
	}

	public String getSearchPattern(String searchingStr) {
		// searchingStr = searchingStr.replaceAll(" ", "");
		searchingStr = searchingStr.toLowerCase();
		String newPattern = "(.*)";
		for (int i = 0; i < searchingStr.length(); i++) {
			newPattern += searchingStr.charAt(i) + "(.*)";
		}

		return newPattern;
	}

	public int updatePriority(String searchingStr, String tempKey, int priority) {
		tempKey = tempKey.toLowerCase();
		String[] searchSplits = searchingStr.toLowerCase().split(" ");
		for (int i = 0; i < searchSplits.length; i++) {
			if (!tempKey.contains(searchSplits[i].trim())) {
				priority = 0;
				return priority;
			}
		}
		return priority;
	}

	public ArrayList<NodeForSearch> sortByPriority(Map<NodeForSearch, Integer> searchResultsList) {

		ArrayList<NodeForSearch> newSortedList = new ArrayList<NodeForSearch>();
		ArrayList<Entry<NodeForSearch, Integer>> arrayList = new ArrayList<Entry<NodeForSearch, Integer>>(
				searchResultsList.entrySet());

		Collections.sort(arrayList, new Comparator<Map.Entry<NodeForSearch, Integer>>() {
			public int compare(Map.Entry<NodeForSearch, Integer> map1, Map.Entry<NodeForSearch, Integer> map2) {
				return (map2.getValue() - map1.getValue());
			}
		});
		for (Entry<NodeForSearch, Integer> entry : arrayList) {
			// System.out.println("Entry: "+ entry.getKey().getPriority());
			newSortedList.add(entry.getKey());
			// newSortedList.put(entry.getKey().getStringForDisplay(),
			// entry.getKey());
		}
		return newSortedList;
	}

	public Map<String, NodeForSearch> getAllInformationForSearch() {
		Map<String, NodeForSearch> allFromDatabase = Database.getAllNodesForSearch();
		// System.out.println(allFromDatabase.size());
		// get User Account

		if (MainModel.getStaticModel().getMyAccount() == null) {
			return allFromDatabase;
		}
		UserAccount currentUser = MainModel.getStaticModel().getMyAccount();
		System.out.println("User information: " + currentUser.getEmail());
		System.out.println("History size: " + currentUser.getHistory().size());
		Iterator<History> iter = currentUser.getHistory().iterator();
		while (iter.hasNext()) {
			History tempHistory = iter.next();
			String hisString = tempHistory.getHistoryStr();
			int hisNodeId = tempHistory.getNodeid();
			String nodeNameComplete = hisString;
			String nodeNameAbbr = hisString;
			int hisCount = tempHistory.getCount();
			NodeForSearch tempNodeForSearch = new NodeForSearch(Database.getNodeFromId(hisNodeId), nodeNameComplete,
					nodeNameAbbr, SuggestorEnum.History);
			allFromDatabase.put(nodeNameComplete, tempNodeForSearch);
		}
		return allFromDatabase;
	}

	public ArrayList<NodeForSearch> getSearchSupply(String searchingStr) {

		Map<NodeForSearch, Integer> searchResultsList = new HashMap<NodeForSearch, Integer>();
		searchingStr = SearchSupply.StringFilter(searchingStr);
		String newPattern = getSearchPattern(searchingStr);

		// create Pattern
		Pattern r = Pattern.compile(newPattern);

		for (Map.Entry<String, NodeForSearch> entry : getAllInformationForSearch().entrySet()) {
			String tempKey = entry.getKey();
			NodeForSearch tempNode = entry.getValue();
			// create matcher
			Matcher m = r.matcher(tempKey.toLowerCase());
			if (m.find()) {
				// System.out.println(tempNode.getStringForSearch());
				if(tempNode.getNode_label().name().equals("History")){
				tempNode.setPriority(updatePriority(searchingStr, tempKey, tempNode.getPriority()));}
				searchResultsList.put(tempNode, tempNode.getPriority());
			}
		}
		ArrayList<NodeForSearch> newsearchResultsList = sortByPriority(searchResultsList);
		return newsearchResultsList;
	}

	// TODO: remove this method
	// public static void main(String[] args) {
	// Database.InitFromDatabase();
	// System.out.println("Map-7 nodes size: "+
	// Database.getAllNodesForCurrentMap(7).size());
	// System.out.println(Database.getAllNodeFromDatabase().size());
	/*
	 * MainModel.setStaticModel(new MainModel());
	 * MainModel.getStaticModel().switchToState((new
	 * MouseActionSelectNode(MainModel.getStaticModel())));
	 * 
	 * SearchSupply ss = new SearchSupply();
	 * 
	 * ArrayList<NodeForSearch> getSS = ss.getSearchSupply("p"); for
	 * (NodeForSearch entry : getSS) { System.out.println("Key = " +
	 * entry.getStringForDisplay()+" ,Priority = " + entry.getPriority()); }
	 * OtherFeatureDao testFeature= new OtherFeatureDaoImpl(); for ( Node n :
	 * testFeature.getListofNodesWithLabel("pizza")){
	 * System.out.println(n.getName()); }
	 */
	// }
}
