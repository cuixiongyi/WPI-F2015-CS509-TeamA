package com.wpi.cs509.teamA.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.GeneralMap;
import com.wpi.cs509.teamA.bean.Major;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeInformation;
import com.wpi.cs509.teamA.bean.OtherFeature;
import com.wpi.cs509.teamA.bean.Professor;
import com.wpi.cs509.teamA.bean.UserAccount;
import com.wpi.cs509.teamA.dao.MajorDao;
import com.wpi.cs509.teamA.dao.MapDao;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.dao.OtherFeatureDao;
import com.wpi.cs509.teamA.dao.ProfessorDao;
import com.wpi.cs509.teamA.dao.impl.MajorDaoImpl;
import com.wpi.cs509.teamA.dao.impl.MapDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeDaoImpl;
import com.wpi.cs509.teamA.dao.impl.NodeRelationDaoImpl;
import com.wpi.cs509.teamA.dao.impl.OtherFeatureDaoImpl;
import com.wpi.cs509.teamA.dao.impl.ProfessorDaoImpl;
import com.wpi.cs509.teamA.newfeature.NodeForSearch;
import com.wpi.cs509.teamA.util.AutoSuggestUtil.SuggestorPainter.SuggestorEnum;

public class Database {
	/** For basic use */
	private static Map<Integer, Node> allNodesDataHM;
	private static Map<Integer, GeneralMap> allMapDataHM;
	private static List<Node> allNodesDataHL;
	private static List<GeneralMap> allMapDataHL;
	private static List<Edge> allEdgesHL;
	private static Map<Integer, List<Edge>> allEdgesDataHM;
	private static List<Edge> allMapEdgesHL;
	private static List<UserAccount> allUsersDataHL;
	private static List<Professor> allProfessors;
	private static List<Major> allMajors;
	private static List<OtherFeature> allOtherLabels;
	private static List<String> labelList;

	/** For search supply */
	private static Map<String, NodeForSearch> allNodesForSearch;

	static int nodeRange = 20;

	public Database() {

	}

	public static void InitFromDatabase() {

		// get all maps from database
		MapDao md = new MapDaoImpl();
		allMapDataHL = md.getAllMaps();
		allMapDataHM = new HashMap<Integer, GeneralMap>();
		Iterator<GeneralMap> iterMap = allMapDataHL.iterator();
		while (iterMap.hasNext()) {
			GeneralMap tempMap = iterMap.next();
			tempMap.readImage();
			allMapDataHM.put(tempMap.getMapId(), tempMap);
		}

		// get all nodes from database
		NodeDao nd = new NodeDaoImpl();
		allNodesDataHL = nd.getAllNodes();
		allNodesDataHM = new HashMap<Integer, Node>();
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			allNodesDataHM.put(tempNode.getId(), tempNode);
		}

		// get all edges from database
		NodeRelationDao nrd = new NodeRelationDaoImpl();
		allEdgesHL = nrd.getAllEdges();
		allEdgesDataHM = new HashMap<Integer, List<Edge>>();
		Iterator<Integer> mapIds = allMapDataHM.keySet().iterator();
		while (mapIds.hasNext()) {
			int temp_map_id = mapIds.next();
			allEdgesDataHM.put(temp_map_id, nrd.getAllNodeRelationsForCurrentMap(temp_map_id));

		}

		// get all maprelations from database
		NodeRelationDao nrd2 = new NodeRelationDaoImpl();
		allMapEdgesHL = nrd2.getAllMapEdges();

		// get all professors from database
		ProfessorDao pd = new ProfessorDaoImpl();
		allProfessors = pd.getAllProfessors();

		// get all majors from database
		MajorDao majord = new MajorDaoImpl();
		allMajors = majord.getAllMajors();

		// get all other labels from database
		OtherFeatureDao ofd = new OtherFeatureDaoImpl();
		allOtherLabels = ofd.getAllOtherFeatures();

		// get all labels from database
		OtherFeatureDao ofd2 = new OtherFeatureDaoImpl();
		labelList = ofd2.getAllFeatureLabels();

		// load nodes for searchSupply
		allNodesForSearch = new HashMap<String, NodeForSearch>();
		InitNodesForSearchSupply();
	}

	/** Deal with Nodes */

	public static List<Node> getAllNodeListFromDatabase() {
		return allNodesDataHL;
	}

	public static Node getNodeFromId(int nodeId) {
		return allNodesDataHM.get(nodeId);
	}

	public static List<Node> getNodeFromIds(List<Integer> nodeIds) {
		List<Node> res = new ArrayList<Node>();
		Iterator<Integer> iter = nodeIds.iterator();
		while (iter.hasNext()) {
			int nodeId = iter.next();
			res.add(allNodesDataHM.get(nodeId));
		}
		return res;
	}

	public static List<Node> getAllNodesForCurrentMap(int currentMapId) {
		List<Node> res = new ArrayList<Node>();
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if (tempNode.getMap().getMapId() == currentMapId)
				res.add(tempNode);
		}
		return res;
	}

	public static int getNodeIdFromName(String node_name) {
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if (tempNode.getName().equals(node_name))
				return tempNode.getId();
		}
		return -1;
	}

	public static Node getNodeFromCoordinate(Coordinate coor, int mapID) {
		List<Node> nodes = getAllNodesForCurrentMap(mapID);
		if (null == nodes)
			return null;
		int x = coor.getX();
		int y = coor.getY();
		boolean foundNode = false;
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if (Math.abs(x - node.getLocation().getX()) < nodeRange
					&& Math.abs(y - node.getLocation().getY()) < nodeRange) {
				foundNode = true;
				return node;
			}
		}
		return (Node) null;
	}

	public static Coordinate getNodeCoordinateFromId(int nodeId) {
		return allNodesDataHM.get(nodeId).getLocation();
	}

	public static Node getNodeFromName(String node_name) {
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			if (tempNode.getName().equals(node_name))
				return tempNode;
		}
		return null;
	}

	/** Deal with Maps */

	public static List<GeneralMap> getAllMapFromDatabase() {
		return allMapDataHL;
	}

	public static GeneralMap getMapEntityFromMapId(int map_id) {
		return allMapDataHM.get(map_id);
	}

	/** Deal with Edges */
	public static List<Edge> getAllEdges() {
		return allEdgesHL;
	}

	public static List<Edge> getAllMapEdges() {
		return allMapEdgesHL;
	}

	public static List<Edge> getAllEdgesForCurrentMap(int map_id) {
		return allEdgesDataHM.get(map_id);
	}

	public static List<Node> getAllMapRelationNodesFromMapId(int map_id) {
		List<Node> tempNodeList = new ArrayList<Node>();
		NodeRelationDao nrd2 = new NodeRelationDaoImpl();
		List<Integer> tempListInt = nrd2.getMapRelationsNodeForOneMap(map_id);
		for (Integer tempInt : tempListInt) {
			tempNodeList.add(allNodesDataHM.get(tempInt));
		}
		return tempNodeList;
	}

	/** Deal with User Account */
	public static List<UserAccount> getAllUserAccount() {
		return allUsersDataHL;
	}

	public static boolean checkUsernameInDatabase(String username) {
		Iterator<UserAccount> usernames = allUsersDataHL.iterator();
		while (usernames.hasNext()) {
			if (usernames.next().getUsername().equals(username))
				return true;
		}
		return false;
	}

	public static boolean checkPassword(String username, String password) {
		Iterator<UserAccount> usernames = allUsersDataHL.iterator();
		while (usernames.hasNext()) {
			UserAccount ua = usernames.next();
			if (ua.getUsername().equals(username)) {

			}
		}
		return false;
	}

	/** Deal with Professors */
	public static List<Professor> getAllProfessor() {
		return allProfessors;
	}

	public static List<Professor> getProfessorListWithNodeId(int nodeId) {
		List<Professor> tempProfessor = new ArrayList<Professor>();
		for (Professor pp : allProfessors) {
			if (pp.getNodeId() == nodeId)
				tempProfessor.add(pp);
		}
		return tempProfessor;
	}

	/** Deal with majors */
	public static List<Major> getAllMajors() {
		return allMajors;
	}

	public static List<Major> getMajorListWithNodeId(int nodeId) {
		List<Major> tempMajor = new ArrayList<Major>();
		for (Major m : allMajors) {
			if (m.getNodeId() == nodeId)
				tempMajor.add(m);
		}
		return tempMajor;
	}

	/** Deal with other features */
	public static List<OtherFeature> getAllOtherFeatures() {
		return allOtherLabels;
	}

	public static List<String> getAllLabels() {
		return labelList;
	}

	public static List<OtherFeature> getAllOtherFeatureListWithNodeId(int nodeId) {
		List<OtherFeature> tempOtherFeatures = new ArrayList<OtherFeature>();
		for (OtherFeature of : allOtherLabels) {
			if (of.getNodeId() == nodeId)
				tempOtherFeatures.add(of);
		}
		return tempOtherFeatures;
	}

	/** Deal with Node information */
	public static NodeInformation getNodeInformation(int nodeId) {
		// find node with this node id
		Node tempNode = allNodesDataHM.get(nodeId);
		List<Major> tempMajors = Database.getMajorListWithNodeId(nodeId);
		List<Professor> tempProfessors = Database.getProfessorListWithNodeId(nodeId);
		List<OtherFeature> tempOtherFeatures = Database.getAllOtherFeatureListWithNodeId(nodeId);
		NodeInformation nodeInfo = new NodeInformation(tempNode, tempMajors, tempProfessors, tempOtherFeatures, null);
		return nodeInfo;
	}

	/** Deal with searching */
	public static Map<String, NodeForSearch> getAllNodesForSearch() {
		return allNodesForSearch;
	}

	/** Load search supply nodes */
	public static void InitNodesForSearchSupply() {

		// Init building information
		Map<Integer, String> allMapsSearch = new HashMap<Integer, String>();
		Map<Integer, String> allMapsabbrSearch = new HashMap<Integer, String>();
		// get maps
		Iterator<GeneralMap> iter_map = allMapDataHL.iterator();
		while (iter_map.hasNext()) {
			GeneralMap tempMap = iter_map.next();
			int map_id = tempMap.getMapId();
			String map_name = tempMap.getMapName();
			// System.out.println("map_id :"+ map_id +" map_name"+map_name);
			allMapsSearch.put(map_id, map_name);
			allMapsabbrSearch.put(map_id, tempMap.getMapAbbrName());
		}

		// get nodes and transfer
		Iterator<Node> iter = allNodesDataHL.iterator();
		while (iter.hasNext()) {
			Node tempNode = iter.next();
			String nodeName = tempNode.getName();
			if (nodeName.equals("Location"))
				continue;
			String mapName = allMapsSearch.get(tempNode.getMap().getMapId());
			String nodeNameComplete = mapName + " " + nodeName;
			String nodeNameAbbr = allMapsabbrSearch.get(tempNode.getMap().getMapId()) + " " + nodeName;
			NodeForSearch tempNodeForSearch = new NodeForSearch(tempNode, nodeNameComplete, nodeNameAbbr,
					SuggestorEnum.Location);
			allNodesForSearch.put(nodeNameComplete, tempNodeForSearch);
		}

		// Init professor information
		Iterator<Professor> iterProfessor = allProfessors.iterator();
		while (iterProfessor.hasNext()) {
			Professor tempProfessor = iterProfessor.next();
			String tempProfessorName = tempProfessor.getProfessorName();
			int tempNodeId = tempProfessor.getNodeId();
			Node tempNode = getNodeFromId(tempNodeId);
			String nodeNameComplete = tempProfessorName + " " + tempNode.getName();
			String nodeNameAbbr = tempProfessorName + " " + tempNode.getName();
			NodeForSearch tempNodeForSearch = new NodeForSearch(tempNode, nodeNameComplete, nodeNameAbbr,
					SuggestorEnum.Professor);
			allNodesForSearch.put(nodeNameComplete, tempNodeForSearch);
		}

		// System.out.println("get all professor size: "+
		// Database.getAllProfessor().size());

		// Init major information
		Iterator<Major> iterMajor = allMajors.iterator();
		while (iterMajor.hasNext()) {
			Major tempMajor = iterMajor.next();
			Node tempNode = getNodeFromId(tempMajor.getNodeId());
			String nodeNameComplete = tempMajor.getMajorName() + " " + tempMajor.getMajorAbbr();
			String nodeNameAbbr = tempMajor.getMajorName();
			NodeForSearch tempNodeForSearch = new NodeForSearch(tempNode, nodeNameComplete, nodeNameAbbr,
					SuggestorEnum.Major);
			allNodesForSearch.put(nodeNameComplete, tempNodeForSearch);
		}

		// Init other information
		// System.out.println("get all features size: "+
		// Database.getAllOtherFeatures().size());
		Iterator<OtherFeature> iterOtherFeature = allOtherLabels.iterator();
		while (iterOtherFeature.hasNext()) {
			OtherFeature otherFeature = iterOtherFeature.next();
			Node tempNode = getNodeFromId(otherFeature.getNodeId());
			String tempFeatureLabel = otherFeature.getFeatureLabel();
			String nodeNameComplete = tempFeatureLabel + " " + tempNode.getName();
			String nodeNameAbbr = tempNode.getName();
			NodeForSearch tempNodeForSearch = new NodeForSearch(tempNode, nodeNameComplete, nodeNameAbbr,
					SuggestorEnum.Others);
			allNodesForSearch.put(nodeNameComplete, tempNodeForSearch);
		}

		// Init label information
		Iterator<String> iterLabels = labelList.iterator();
		while (iterLabels.hasNext()) {
			String nodeNameComplete = iterLabels.next();
			Node tempNode = null;
			String nodeNameAbbr = nodeNameComplete;
			NodeForSearch tempNodeForSearch = new NodeForSearch(tempNode, nodeNameComplete, nodeNameAbbr,
					SuggestorEnum.Labels);
			allNodesForSearch.put(nodeNameComplete, tempNodeForSearch);
		}
		// MainModel model = MainModel.getStaticModel().getMyAccount();
	}
}
