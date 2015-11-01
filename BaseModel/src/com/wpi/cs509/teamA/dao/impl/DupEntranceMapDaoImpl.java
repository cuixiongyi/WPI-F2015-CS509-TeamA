package com.wpi.cs509.teamA.dao.impl;

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
	int getCountofNodes() {

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
	int getCountofNeighborsForNodes(int node_id) {
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
	Map<Integer,Double> initNodesNeighbors(int node_id) {
		PreparedStatement selectNodeNeighbors = null;
	//	int count_neighbors = getCountofNeighborsForNodes(node_id);
		Map<Integer,Double> allNeighbors = new HashMap <Integer,Double>();
		int iter_neighbors = 0;
		String selectNeighbors = "select node_to,distance from RouteFinder.relations where node_from= ?";
		try {
			selectNodeNeighbors = (PreparedStatement) con.prepareStatement(selectNeighbors);
			selectNodeNeighbors.setInt(1, node_id);
			ResultSet rs = selectNodeNeighbors.executeQuery();
			while (rs.next()) {
				System.out.println("neighbor " + rs.getInt("node_to")+ 
						" --- distance: "+ rs.getDouble("distance"));
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
	Set<Node> initAllNodes() {
//		int numberofNodes = getCountofNodes();
		Set<Node> allNodes = new HashSet <Node>();
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
				Map<Integer,Double> neighborsForNode = initNodesNeighbors(rs.getInt("id"));
				allNodes.add(new Node(temp_id, temp_name, temp_x, temp_y, temp_mapid, 
						neighborsForNode, temp_class));
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		}
		return allNodes;
	}

	// initialize edges at once
	Set<Edge> initAllEdges() {
		Set<Edge> alledges = new HashSet<Edge>();
		return alledges;
	}

	public static void main(String[] args) {
		DupEntranceMapDaoImpl demp = new DupEntranceMapDaoImpl();
		Set<Node> nodes_list = demp.initAllNodes();
	}

}
