package com.wpi.cs509.teamA.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.DupEntranceMapDao;
import com.wpi.cs509.teamA.strategy.impl.Edge;

/**
 * The implementation class of DupEntranceMapDao
 * 
 * @author CS 509-Team A
 *
 */
public class DupEntranceMapDaoImpl implements DupEntranceMapDao {
	Connection con = null;

	/**
	 * Construction of DupEntranceMapDaoImpl, connect to the local database
	 * 
	 * @author CS 509-Team A
	 */
	public DupEntranceMapDaoImpl() {
		try {
			// connect to mysql Driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find the jdbc Driver");
			e.printStackTrace();
		}
		// connect to database
		String url = "jdbc:mysql://localhost:3306/RouteFinder";
		String username = "root";
		String password = "hammer";
		try {
			con = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
	}

	/**
	 * get count of nodes in the database execute "select count(*) from node"
	 * 
	 * @author CS 509-Team A
	 * @return count of nodes in the database
	 */
	public int getCountofNodes() {

		Statement countNodes;
		try {
			countNodes = (Statement) con.createStatement();
			String selectFromNodes = "select count(*) " + "from RouteFinder.node";

			ResultSet rs = countNodes.executeQuery(selectFromNodes);
			while (rs.next()) {
				System.out.println("Number of nodes in the database: " + rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
		return 100000;
	}

	/**
	 * get count of neighbors given a node_id in the database execute
	 * "select count(*) from relations where node_from = ?"
	 * 
	 * @author CS 509-Team A
	 * @return count of neighbors for the node
	 */
	public int getCountofNeighborsForNodes(int node_id) {
		PreparedStatement selectNodeNeighbors = null;
		String selectNeighbors = "select count(*) from RouteFinder.relations where node_from= ?";
		try {
			selectNodeNeighbors = (PreparedStatement) con.prepareStatement(selectNeighbors);
			selectNodeNeighbors.setInt(1, node_id);
			ResultSet rs = selectNodeNeighbors.executeQuery();
			while (rs.next()) {
				System.out.println("Number of neighbors for node " + node_id + " is : " + rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
		return 0;
	}

	/**
	 * initialize nodes from the database at once execute select * from node
	 * 
	 * @author CS 509-Team A
	 * @return an array of nodes
	 */
	public Map<Integer, Double> initNodesNeighbors(int node_id) {
		PreparedStatement selectNodeNeighbors = null;
		// int count_neighbors = getCountofNeighborsForNodes(node_id);
		Map<Integer, Double> allNeighbors = new HashMap<Integer, Double>();
		int iter_neighbors = 0;
		String selectNeighbors = "select node_to,distance from RouteFinder.relations where node_from= ?";
		try {
			selectNodeNeighbors = (PreparedStatement) con.prepareStatement(selectNeighbors);
			selectNodeNeighbors.setInt(1, node_id);
			ResultSet rs = selectNodeNeighbors.executeQuery();
			while (rs.next()) {
				System.out.println("neighbor " + rs.getInt("node_to") + " --- distance: " + rs.getDouble("distance"));
				allNeighbors.put(rs.getInt("node_to"), rs.getDouble("distance"));
			}
			return allNeighbors;
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
		return null;
	}

	/**
	 * initialize nodes from the database at once execute select * from node
	 * 
	 * @author CS 509-Team A
	 * @return an array of nodes
	 */
	public Set<Node> initAllNodes() {
		// int numberofNodes = getCountofNodes();
		Set<Node> allNodes = new HashSet<Node>();
		int iter_nodes = 0;
		Statement selectNodes;

		try {
			selectNodes = (Statement) con.createStatement();
			String selectFromNodes = "select id, name, x, y, map_id, classification " + "from RouteFinder.node";

			// Node(int id, String name, int x, int y, int mapId, Set<Integer>
			// neighbors, String nodeType);

			ResultSet rs = selectNodes.executeQuery(selectFromNodes);
			while (rs.next()) {
				int temp_id = rs.getInt("id");
				String temp_name = rs.getString("name");
				int temp_x = rs.getInt("x");
				int temp_y = rs.getInt("y");
				int temp_mapid = rs.getInt("map_id");
				String temp_class = rs.getString("classification");
				Map<Integer, Double> neighborsForNode = initNodesNeighbors(rs.getInt("id"));
				allNodes.add(new Node(temp_id, temp_name, temp_x, temp_y, temp_mapid, neighborsForNode, temp_class));
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
		return allNodes;
	}

	/**
	 * insert nodes to the database at once execute INSERT INTO RouteFinder.node
	 * (`name`, `x`, `y`, `map_id`, `classification`) VALUES ('?', '?', '?',
	 * '?', '?');
	 * 
	 * @author CS 509-Team A
	 */
	public void insertNodes(String name, int x, int y, int map_id, String classification) {
		PreparedStatement insertNodePS = null;
		String insertNodesToDB = "INSERT INTO RouteFinder.node (name, x, y, map_id, classification) VALUES (?, ?, ?, ?, ?)";
		try {
			insertNodePS = (PreparedStatement) con.prepareStatement(insertNodesToDB);
			insertNodePS.setString(1, name);
			insertNodePS.setInt(2, x);
			insertNodePS.setInt(3, y);
			insertNodePS.setInt(4, map_id);
			insertNodePS.setString(5, classification);
			insertNodePS.executeUpdate();

		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
	}

	/**
	 * insert edges to the database at once execute
	 * 
	 * @author CS 509-Team A
	 */
	
	public boolean insertEdges(int [][] edgeSaver) {
		int [][] edgeSaver = new int[10][4];
		Field[] fields = this.getClass().getDeclaredFields();
		Type[] paramTypeList = this.getGenericParameterTypes();
		for(Field field:fields){
            System.out.println(field.getName());
		}
		for(int i=0; i< 10; i++){
			
			String []splits_1 = (String.valueOf("str_"+(2*i+1))).split(",");
			edgeSaver[i][0] = Integer.valueOf(splits_1[0]);
			edgeSaver[i][1] = Integer.valueOf(splits_1[1]);
			String []splits_2 = (String.valueOf("str_"+(2*i+2))).split(",");
			edgeSaver[i][2] = Integer.valueOf(splits_2[0]);
			edgeSaver[i][3] = Integer.valueOf(splits_2[1]);
			System.out.println(edgeSaver[i][0]+ "----"+ edgeSaver[i][1]+"-----"+ edgeSaver[i][2]+ "-----"+ edgeSaver[i][3]);
		}
		return true;
	}

	/**
	 * insert one edge to the database at once execute
	 * 
	 * @author CS 509-Team A
	 */

	public boolean insertoneEdge(int x1, int y1, int x2, int y2) {
		PreparedStatement checkPointPS = null;
		PreparedStatement checkEdgePS = null;
		PreparedStatement insertEdgePS = null;
		int from_id = -1;
		int to_id = -1;
		// check if the nodes are in database
		String checkNodesInDB = "select id from RouteFinder.node where x=? and y=?";
		try {
			checkPointPS = (PreparedStatement) con.prepareStatement(checkNodesInDB);
			checkPointPS.setInt(1, x1);
			checkPointPS.setInt(2, y1);
			ResultSet rs = checkPointPS.executeQuery();
			if (rs.next()) {
				from_id = rs.getInt("id");
			} else {
				System.out.println("Cannot find node x1, y1");
				return false;
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		}

		try {
			checkPointPS = (PreparedStatement) con.prepareStatement(checkNodesInDB);
			checkPointPS.setInt(1, x2);
			checkPointPS.setInt(2, y2);
			ResultSet rs = checkPointPS.executeQuery();
			if (rs.next()) {
				to_id = rs.getInt("id");
			} else {
				System.out.println("Cannot find node x1, y1");
				return false;
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		}
		// check if the edges are in database
		String checkEdgeInDB = "select id from RouteFinder.relations where node_from=? and node_to=?";
		try {
			checkEdgePS = (PreparedStatement) con.prepareStatement(checkEdgeInDB);
			checkEdgePS.setInt(1, from_id);
			checkEdgePS.setInt(2, to_id);
			ResultSet rs = checkEdgePS.executeQuery();
			if (rs.next()) {
				System.out.println("Edge exists!");
				return false;
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		}
		double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		String insertEdgeToDB = "INSERT INTO RouteFinder.relations (node_from, node_to, distance) VALUES (?, ?, ?)";
		try {
			insertEdgePS = (PreparedStatement) con.prepareStatement(insertEdgeToDB);
			insertEdgePS.setInt(1, from_id);
			insertEdgePS.setInt(2, to_id);
			insertEdgePS.setDouble(3, distance);
			insertEdgePS.executeUpdate();

		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		}
		return true;
	}

	// initialize edges at once
	public Set<Edge> initAllEdges() {
		Set<Edge> alledges = new HashSet<Edge>();
		return alledges;
	}

	public static void main(String[] args) {
		DupEntranceMapDaoImpl demp = new DupEntranceMapDaoImpl();
		// Set<Node> nodes_list = demp.initAllNodes();
		// demp.insertNodes("323", 4, 5, 1, "OFFICE");
		demp.insertEdges("1,1", "1,2", "1,3","1,4", "1,5", "1,6","1,7","","","1,8",
				"","","","","1,10","1,11","","","","");
		demp.insertoneEdge(3, 4, 2, 6);
	}

}