package com.wpi.cs509.teamA.model;

import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.Path;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.LinearTransform;
import com.wpi.cs509.teamA.util.NodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This model should extends from a base observable class
 * 
 * This class should contains only data
 * 
 */
public final class MainModel extends StateContextModel {

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
    private int currentPathIdx = 0;

    private Node nodeAnimation = null;
	private LinearTransform linearTransform = new LinearTransform();;

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
        linearTransform = new LinearTransform();
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
		this.clearEndNode();

		this.setMultiMapPathListsForEachMap(null);
		this.setMultiMapPathLists(null);
		this.setAnimationNode(null);
		this.setFocusNode(null);
        this.clearPaths();


	}

	public synchronized ArrayList<Node> getRouteOnCurrentMap() {

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
//		currentMap.setDisplayScale(1.0f);

		isFirstChangeMap = true;
		addAllFilters();
        setAnimationNode(null);

        if (null != paths ) {
            if ( null != getCurrentPath() && this.currentMap == getCurrentPath().getMap()) {

            }
            else {
                boolean foundMap = false;
                clearCurrentPath();
                for(int ii = 0; ii < paths.size(); ii++) {
                    if (paths.get(ii).getMap() == this.currentMap) {
                        setCurrentPath(ii);
                        foundMap = true;
                        break;
                    }
                }
                if (! foundMap) {
                    clearCurrentPath();
                }
            }
        }

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
			this.startNode = null;
			modelChanged();
			return;
		}
        if (null != pStartNode) {
            this.setFocusNode(pStartNode);
        }

        this.multiMapPathListsForEachMap = null;

		this.startNode = pStartNode;
 //       this.endNearestNodes = null;
		modelChanged();

	}

	public synchronized ArrayList<Node> getEndNode() {
		
		
		return endNode;
	}

	public synchronized boolean addOneEndNode(Node pendNode) {
		if (null == this.endNode) {
			endNode = new ArrayList<Node>();
		}

		if (endNode.contains(pendNode)) {
			endNode.remove(pendNode);
			return false;
		}
		else {
			this.endNode.add(pendNode);
		}
        modelChanged();
		return true;
	}
	
	public synchronized void setOneEndNode(Node pEndNode) {
		endNode = new ArrayList<Node>();
		if (addOneEndNode(pEndNode)) {
			setFocusNode(pEndNode);
		}
		return;
		
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
		if (null == focusNode) {
			isFirstFocusNode = false;
            modelChanged();

            return;
        }
		isFirstFocusNode = true;
        setCurrentMap(focusNode.getMap());
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

	public synchronized ArrayList<Path> getPaths() {
		return paths;
	}
	public synchronized Path getOnePath(int idx) {
		if (null == paths || 0 > idx || paths.size() <= idx )
			return null;
		return paths.get(idx);
	}

	public synchronized void addOnePath(Path ppath) {
		if (null == paths) {
			paths = new ArrayList<Path>();
		}
		ppath.setMap(ppath.getNodes().get(0).getMap());
		this.paths.add(ppath);
	}

	public synchronized void clearPaths() {
		this.paths = null;
		this.currentPathIdx = -1;
	}

    public synchronized Path getCurrentPath() {
        return getOnePath(currentPathIdx);
    }

    public synchronized int getCurrentPathIdx() {
        return currentPathIdx;
    }

    public synchronized void clearCurrentPath() {
        this.currentPathIdx = -1;
        return;
    }

    public synchronized void setCurrentPath(int idx) {
		if (null == paths) {
			return;
		}
        if (idx >= paths.size() || idx < 0) {
            return;
//            throw new ArrayIndexOutOfBoundsException();
        }
        if (idx == this.currentPathIdx) {
            return;
        }
        this.currentPathIdx = idx;
        Path path = getOnePath(currentPathIdx);
		if (null == path) {
			return;
		}
        this.setFocusNode(path.getNodes().get(0));
        if (path.getMap() != getCurrentMap()) {
            this.setCurrentMap(path.getMap());
            this.setAnimationNode(getCurrentPath().getNodes().get(0));
        }
    }

    public synchronized boolean setNextPath() {
        if (currentPathIdx+1 >= paths.size()) {
            return false;
        }
        setCurrentPath(currentPathIdx+1);
        return true;
    }

    public synchronized boolean setPrivousPath() {
        if (currentPathIdx-1 < 0) {
            return false;
        }
        setCurrentPath(currentPathIdx-1);
        return true;
    }

    public synchronized LinearTransform getLinearTransform() {
        // modelChanged();
        return linearTransform;
    }

    public synchronized void setLinearTransform(LinearTransform linearTransform) {
        this.linearTransform = linearTransform;
        modelChanged();
    }

	public void setAnimationNode(Node node) {
    	this.nodeAnimation = node;
    	modelChanged();
    }
    public Node getAnimationNode() {
    	return this.nodeAnimation;
    }
	public HashMap<String, Integer> getParkingAvilibility() {
		return parkingAvilibility;
	}

	public void setParkingAvilibility(HashMap<String, Integer> parkingAvilibility) {
		this.parkingAvilibility = parkingAvilibility;
	}


}
