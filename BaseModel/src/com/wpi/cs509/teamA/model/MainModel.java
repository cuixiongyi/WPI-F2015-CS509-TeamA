package com.wpi.cs509.teamA.model;

import com.sun.org.apache.bcel.internal.generic.DADD;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuixi on 12/3/2015.
 */
public final class MainModel extends StateContext {

public static MainModel staticModel = null;
	
	private UserAccount myAccount;
	private GeneralMap currentMap = null;
	private List<NodeType> iconFilter = null;
	private Node startNode;
	private Node endNode;

	private Node focusNode;

	private ArrayList<ArrayList<Node>> multiMapPathListsForEachMap = null;
	private ArrayList<ArrayList<Node>> multiMapPathLists = null;

	private ArrayList<GeneralMap> multiMapLists = null;

	/**
	 * if filterNodeType[i] == 1 then display that type of node
	 * filterNodeType[i] == 0 don't display
	 */
	private List<Integer> filterNodeType;

	public MainModel() {

		this.myAccount = new UserAccount();
		myAccount = null;
		this.currentMap = null;
		this.iconFilter = new ArrayList<NodeType>();
		// multiMapPathListsForEachMap = new ArrayList<ArrayList<Node>>();
		setCurrentMapID(1);

	}

	public synchronized void setFilter(NodeType filter) {
		if (!iconFilter.contains(filter)) {
			iconFilter.add(filter);
		} else {
			iconFilter.remove(filter);
		}
	}

	public synchronized void clearFilters()
	{
		iconFilter.clear();
	}
	
	public synchronized boolean hasFilter(NodeType filter) {
		if (iconFilter.contains(filter)) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void cleanUpRoute() {
		this.setStartNode(null);
		this.setEndNode(null);
		this.setMultiMapPathListsForEachMap(null);
		this.setMultiMapPathLists(null);

	}

	public synchronized ArrayList<Node> getRouteOnCurrentMap() {
		// ArrayList<ArrayList<Node>> multiMapPath = getMultiMapPathListsForEachMap();
		// if (null != multiMapPath && 0 != multiMapPath.size()) {
		// int idx = getCurrentMapID()-1;
		// return multiMapPath.get(idx);
		//
		// }
		ArrayList<Node> ret = null;
		try {
			int idx = getCurrentMapID() - 1;
			ret = getMultiMapPathListsForEachMap().get(idx);
		} catch (Exception e) {
			return null;
		}
		return ret;
	}

	/**
	 * setter and getters
	 *
	 */

	public synchronized UserAccount getMyAccount() {
		return myAccount;
	}

	public synchronized void setMyAccount(UserAccount pAccount) {
		this.myAccount = pAccount;
		modelChanged();

	}

	public synchronized void setFocusToNode(Node node) {
		if (null == node) {
			return;
		}
		setCurrentMap(node.getMap());
		this.focusNode = node;
		modelChanged();

	}

	public synchronized int getCurrentMapID() {
		return currentMap.getMapId();

	}

	public synchronized GeneralMap getCurrentMap() {
		return currentMap;
	}

	public synchronized void setCurrentMap(GeneralMap currentMap) {
		this.currentMap = currentMap;
		modelChanged();
	}

	public synchronized void setCurrentMapID(int mapID) {
		this.currentMap = Database.getMapEntityFromMapId(mapID);
		modelChanged();
	}

	public synchronized Node getFocusNode() {
		return this.focusNode;
	}

	public synchronized Node getStartNode() {
		return startNode;
	}

	public synchronized void setStartNode(Node pStartNode) {
		if (pStartNode == this.startNode) {
			return;
		}
		this.multiMapPathListsForEachMap = null;

		this.startNode = pStartNode;
		modelChanged();

	}

	public synchronized Node getEndNode() {
		return endNode;
	}

	public synchronized void setEndNode(Node endNode) {
		this.endNode = endNode;
		modelChanged();
	}

	public synchronized ArrayList<ArrayList<Node>> getMultiMapPathListsForEachMap() {
		return multiMapPathListsForEachMap;
	}

	public synchronized void setMultiMapPathListsForEachMap(ArrayList<ArrayList<Node>> pMultiMapPathLists) {
		List<GeneralMap> maps = Database.getAllMapFromDatabase();
		this.setMultiMapPathLists(pMultiMapPathLists);
		this.multiMapPathListsForEachMap = new ArrayList<ArrayList<Node>>();

		for (int ii = 1; ii <= maps.size(); ++ii) {
			ArrayList<Node> path = new ArrayList<Node>();
			int idx = -1;
			for (int jj = 0; jj < pMultiMapPathLists.size(); ++jj) {
				if (pMultiMapPathLists.get(jj).get(0).getMap().getMapId() == ii) {
					idx = jj;
					break;
				}
			}
			if (-1 != idx) {
				path = pMultiMapPathLists.get(idx);
			}
			this.multiMapPathListsForEachMap.add(path);
		}
		modelChanged();
	}

	public synchronized List<Integer> getFilterNodeType() {
		return filterNodeType;
	}

	public synchronized void setFilterNodeType(List<Integer> filterNodeType) {
		this.filterNodeType = filterNodeType;
		modelChanged();

	}

	public synchronized void saveNode(Node node) {
		NodeDao nd = new NodeDaoImpl();
		nd.saveNode(node);
		Database.InitFromDatabase();
		modelChanged();

	}

	public synchronized void editNode(Node node) {

		NodeDaoImpl dao = new NodeDaoImpl();
		dao.editNode(node);
		Database.InitFromDatabase();
		modelChanged();

	}

	public ArrayList<ArrayList<Node>> getMultiMapPathLists() {
		return multiMapPathLists;
	}

	public void setMultiMapPathLists(ArrayList<ArrayList<Node>> multiMapPathLists) {
		this.multiMapPathLists = multiMapPathLists;
	}

public synchronized void setFocusNode(Node focusNode) {
		this.focusNode = focusNode;
		modelChanged();

	}

	public synchronized ArrayList<GeneralMap> getMultiMapLists() {
		if(multiMapLists==null){
			ArrayList<GeneralMap> result = new ArrayList<GeneralMap>();
			result.add(this.getCurrentMap());
			return result;
		}
		
		return multiMapLists;
	}

	public synchronized void setMultiMapLists(ArrayList<GeneralMap> multiMapLists) {
		this.multiMapLists = multiMapLists;
		modelChanged();

	}

	public synchronized boolean ifLoginAdmin() {
		return false;
	}

    public static MainModel getStaticModel() {
    	return staticModel;
    }
    public static void setStaticModel(MainModel pModel) {
    	 staticModel = pModel;
    }
    
}