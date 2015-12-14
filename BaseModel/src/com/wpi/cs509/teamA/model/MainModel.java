package com.wpi.cs509.teamA.model;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.NodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
public final class MainModel extends StateContext {

	public static MainModel staticModel = null;

	private UserAccount myAccount;
	private GeneralMap currentMap = null;
	private List<NodeType> iconFilter = null;
	private static NodeType[] nodeTypes;
	private Node startNode;
	private ArrayList<Node> endNode;

	private Node focusNode = null;
	private boolean isFirstFocusNode = false;
	private boolean isFirstChangeMap = false;

	private ArrayList<ArrayList<Node>> multiMapPathListsForEachMap = null;
	private ArrayList<ArrayList<Node>> multiMapPathLists = null;

	private ArrayList<GeneralMap> multiMapLists = null;
	
	private HashMap<String, Integer> parkingAvilibility = null;

    private ArrayList<Path> paths = null;
    private Path currentPath = null;


	public MainModel() {

		this.myAccount = new UserAccount();
		myAccount = null;
		this.currentMap = null;
		this.iconFilter = new ArrayList<NodeType>();
		MainModel.nodeTypes = NodeType.values();
		addAllFilters();
		multiMapPathListsForEachMap = new ArrayList<ArrayList<Node>>();
		setCurrentMapID(1);
		endNode = new ArrayList<Node>();
		parkingAvilibility = new HashMap<String, Integer>();
		for(Node n: Database.getAllNodeListFromDatabase()){
			if(n.getNodeType() == NodeType.PARKING){
				parkingAvilibility.put(n.getName(), 0);
			}
		}	
	}

	public synchronized void setFilter(NodeType filter) {
		if (!iconFilter.contains(filter)) {
			iconFilter.add(filter);
		} else {
			iconFilter.remove(filter);
		}
		modelChanged();
	}

	// Cooresponds to "ALL" filter on the UI
	public synchronized void clearFilters() {
		iconFilter.clear();
	}

	// Cooresponds to "CLEAR" filter on the UI
	public synchronized void addAllFilters() {
		for (NodeType type : nodeTypes) {
			if (!iconFilter.contains(type)) {
				iconFilter.add(type);
			}
		}
		modelChanged();
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
        this.clearPaths();

	}

	public synchronized ArrayList<Node> getRouteOnCurrentMap() {
		// ArrayList<ArrayList<Node>> multiMapPath =
		// getMultiMapPathListsForEachMap();
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
		isFirstFocusNode = true;
		modelChanged();

	}

	public synchronized int getCurrentMapID() {
		return currentMap.getMapId();

	}

	public synchronized GeneralMap getCurrentMap() {
		return currentMap;
	}

	public synchronized void setCurrentMap(GeneralMap pCurrentMap) {
		if (pCurrentMap == this.currentMap) {
			return;
		}
		this.currentMap = pCurrentMap;
		currentMap.setDisplayScale(1.0f);
		isFirstChangeMap = true;
		addAllFilters();
		modelChanged();
	}

	public synchronized void setCurrentMapID(int mapID) {
		setCurrentMap( Database.getMapEntityFromMapId(mapID));
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
		this.setFocusNode(pStartNode);
		modelChanged();

	}

	public synchronized ArrayList<Node> getEndNode() {
		
		
		return endNode;
	}

	public synchronized void setEndNode(Node pendNode) {
		if (null == this.endNode) {
			endNode = new ArrayList<Node>();
		}
		
		this.endNode.add(pendNode);
		modelChanged();
	}

	public synchronized void clearEndNode() {
		endNode = new ArrayList<Node>();
		modelChanged();
	}

	public synchronized ArrayList<ArrayList<Node>> getMultiMapPathListsForEachMap() {
		return multiMapPathListsForEachMap;
	}

	public synchronized void setMultiMapPathListsForEachMap(ArrayList<ArrayList<Node>> pMultiMapPathLists) {
		List<GeneralMap> maps = Database.getAllMapFromDatabase();
		this.setMultiMapPathLists(pMultiMapPathLists);
		this.multiMapPathListsForEachMap = new ArrayList<ArrayList<Node>>();
//
//		for (int ii = 1; ii <= maps.size(); ++ii) {
//			ArrayList<Node> path = new ArrayList<Node>();
//			int idx = -1;
//			for (int jj = 0; jj < pMultiMapPathLists.size(); ++jj) {
//				if (pMultiMapPathLists.get(jj).get(0).getMap().getMapId() == ii) {
//					idx = jj;
//					break;
//				}
//			}
//			if (-1 != idx) {
//				path = pMultiMapPathLists.get(idx);
//			}
//			this.multiMapPathListsForEachMap.add(path);
//		}
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
		isFirstFocusNode = true;
		modelChanged();

	}

	public synchronized ArrayList<GeneralMap> getMultiMapLists() {
		if (multiMapLists == null) {
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

	public synchronized boolean isFisrtFocusNode() {
		return this.isFirstFocusNode;
	}

	public synchronized void setFisrtFocusNode2False() {
		this.isFirstFocusNode = false;
	}

	public synchronized boolean isFisrtChangeMap() {
		return this.isFirstChangeMap;
	}

	public synchronized void setFisrtChangeMapFalse() {
		this.isFirstChangeMap = false;
	}
	public synchronized boolean isLoginAdmin() {
		return false;
	}

	public static MainModel getStaticModel() {
		return staticModel;
	}

	public static void setStaticModel(MainModel pModel) {
		staticModel = pModel;
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}
	public Path getOnePath(int idx) {
		return paths.get(idx);
	}

	public void addOnePath(Path path) {
		if (null == paths) {
			paths = new ArrayList<Path>();
		}
        path.setMap(path.getNodes().get(0).getMap());
		this.paths.add(path);
	}

	public void clearPaths() {
		this.paths = null;
	}

    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(int idx) {
        if (idx >= paths.size()) {
            throw new StackOverflowError();
        }
        this.currentPath = paths.get(idx);
    }

	public HashMap<String, Integer> getParkingAvilibility() {
		return parkingAvilibility;
	}

	public void setParkingAvilibility(HashMap<String, Integer> parkingAvilibility) {
		this.parkingAvilibility = parkingAvilibility;
	}
}
