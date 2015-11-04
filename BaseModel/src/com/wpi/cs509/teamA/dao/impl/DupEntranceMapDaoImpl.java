package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.DupEntranceMapDao;

/**
 * The implementation class of DupEntranceMapDao
 * 
 * @author CS 509-Team A
 *
 */
public class DupEntranceMapDaoImpl implements DupEntranceMapDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	/**
	 * Construction of DupEntranceMapDaoImpl, connect to the local database
	 * 
	 * @author CS 509-Team A
	 */
	public DupEntranceMapDaoImpl() {
		try {
			// connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get count of nodes in the database execute "select count(*) from node"
	 * 
	 * @author CS 509-Team A
	 * @return count of nodes in the database
	 */
	public int getCountofNodes() {
		try {
			stmt = conn.createStatement();
			String selectFromNodes = "select count(*) " + "from RouteFinder.node";
			rs = stmt.executeQuery(selectFromNodes);
			while (rs.next()) {
				System.out.println("Number of nodes in the database: " + rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, stmt);
			JdbcConnect.connClose();
		}
		return -1;
	}

	/**
	 * get count of neighbors given a node_id in the database execute
	 * "select count(*) from relations where node_from = ?"
	 * 
	 * @author CS 509-Team A
	 * @return count of neighbors for the node
	 */
	public int getCountofNeighborsForNodes(int node_id) {
		String selectNeighbors = "select count(*) from RouteFinder.relations where node_from= ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(selectNeighbors);
			pstmt.setInt(1, node_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("Number of neighbors for node " + node_id + " is : " + rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
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

		Map<Integer, Double> allNeighbors = new HashMap<Integer, Double>();
		int iter_neighbors = 0;
		String selectNeighbors = "select node_to,distance from RouteFinder.relations where node_from= ?";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(selectNeighbors);
			pstmt.setInt(1, node_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("neighbor " + rs.getInt("node_to") + " --- distance: " + rs.getDouble("distance"));
				allNeighbors.put(rs.getInt("node_to"), rs.getDouble("distance"));
			}
			return allNeighbors;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
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

		Set<Node> allNodes = new HashSet<Node>();
		int iter_nodes = 0;
		try {
			stmt = conn.createStatement();
			String selectFromNodes = "select id, name, x, y, map_id, classification " + "from RouteFinder.node";

			ResultSet rs = stmt.executeQuery(selectFromNodes);
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
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, stmt);
			JdbcConnect.connClose();
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
		String insertNodesToDB = "INSERT INTO RouteFinder.node (name, x, y, map_id, classification) VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(insertNodesToDB);
			pstmt.setString(1, name);
			pstmt.setInt(2, x);
			pstmt.setInt(3, y);
			pstmt.setInt(4, map_id);
			pstmt.setString(5, classification);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}
	}

	/**
	 * insert one edge to the database at once execute
	 * 
	 * @author CS 509-Team A
	 */

	public boolean insertoneEdge(int x1, int y1, int x2, int y2) {

		int from_id = -1;
		int to_id = -1;
		// check if the nodes are in database
		String checkNodesInDB = "select id from RouteFinder.node where x=? and y=?";
		try {
			pstmt = conn.prepareStatement(checkNodesInDB);
			pstmt.setInt(1, x1);
			pstmt.setInt(2, y1);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				from_id = rs.getInt("id");
			} else {
				System.out.println("Cannot find node x1, y1");
				return false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
		}

		try {
			pstmt = conn.prepareStatement(checkNodesInDB);
			pstmt.setInt(1, x2);
			pstmt.setInt(2, y2);
			ResultSet rs = pstmt.executeQuery();
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
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
		}

		// check if the edges are in database
		String checkEdgeInDB = "select id from RouteFinder.relations where node_from=? and node_to=?";
		try {
			pstmt = conn.prepareStatement(checkEdgeInDB);
			pstmt.setInt(1, from_id);
			pstmt.setInt(2, to_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Edge exists!");
				return false;
			}
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
		}

		double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		String insertEdgeToDB = "INSERT INTO RouteFinder.relations (node_from, node_to, distance) VALUES (?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(insertEdgeToDB);
			pstmt.setInt(1, from_id);
			pstmt.setInt(2, to_id);
			pstmt.setDouble(3, distance);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("fail to connect database");
			se.printStackTrace();
			return false;
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}
		return true;
	}

	// initialize edges at once
	public Set<Edge> initAllEdges() {
		Set<Edge> alledges = new HashSet<Edge>();
		return alledges;
	}

}
