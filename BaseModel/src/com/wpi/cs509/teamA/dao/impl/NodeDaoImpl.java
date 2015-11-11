package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.JdbcConnect;
import com.wpi.cs509.teamA.util.NodeType;
import com.wpi.cs509.teamA.util.UIDataBuffer;

public class NodeDaoImpl implements NodeDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public NodeDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getNodeNum() {
		// TODO Auto-generated method stub

		try {
			String getNodeNum = "select count(*) from RouteFinder.node";
			pstmt = conn.prepareStatement(getNodeNum);
			rs = pstmt.executeQuery();
			// if there is a result..
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}

		// means you get nothing from database..
		// which means error occurs..
		// this line may never get execute then?
		return -1;
	}

	@Override
	public int getNeighborsNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNodeIdFromName(String node_name){
		ResultSet resultSet = null;
		try {
			String selectAllNodes = "SELECT id FROM RouteFinder.node where map_id=? and name=?;";
			pstmt = conn.prepareStatement(selectAllNodes);
			// TODO: potential danger..
			pstmt.setInt(1, UIDataBuffer.getCurrentMapId());
			pstmt.setString(2, node_name);
			
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			}
			return -1;
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
			JdbcConnect.connClose();
		}

		return -1;
	}
	@Override
	public Set<Node> getAllNodes() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		Set<Node> res = new HashSet<Node>();
		try {
			String selectAllNodes = "SELECT id, name, x, y, map_id, classification FROM RouteFinder.node where map_id=?;";
			pstmt = conn.prepareStatement(selectAllNodes);
			// TODO: potential danger..
			pstmt.setInt(1, UIDataBuffer.getCurrentMapId());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Node node = new Node();
				node.setId(resultSet.getInt("id"));
				node.setName(resultSet.getString("name"));
				Coordinate location = new Coordinate();
				location.setX(resultSet.getInt("x"));
				location.setY(resultSet.getInt("y"));
				node.setLocation(location);
				node.setMapId(resultSet.getInt("map_id"));
				node.setNodeType(NodeType.valueOf(resultSet.getString("classification")));
				res.add(node);

			}

			return res;

		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
			JdbcConnect.connClose();
		}

		return null;
	}

	@Override
	public Map<Integer, Double> getNodeNeighbor(int nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNode(Node node) {
		// TODO Auto-generated method stub

		// TODO: Check if the node exists.. the same coordinate should be
		// considered the same node..F
		try {
			String insertNodeToDB = "INSERT INTO RouteFinder.node (name, x, y, map_id, classification) VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(insertNodeToDB);
			pstmt.setString(1, node.getName());
			pstmt.setInt(2, node.getLocation().getX());
			pstmt.setInt(3, node.getLocation().getY());
			pstmt.setInt(4, node.getMapId());
			// get the node type from the node and then transhform it to string
			// to store it in the db
			pstmt.setString(5, node.getNodeType().toString());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}

	}

	@Override
	public boolean checkNodeInDB(Node node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Coordinate getNodeCoordinateFromId(int nodeId) {
		// TODO Auto-generated method stub

		ResultSet resultSet = null;

		try {

			String getAllEdges = "SELECT x, y FROM routefinder.node where id=?";
			pstmt = conn.prepareStatement(getAllEdges);
			pstmt.setInt(1, nodeId);
			resultSet = null;
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				Coordinate coordinate = new Coordinate();
				coordinate.setX(resultSet.getInt("x"));
				coordinate.setY(resultSet.getInt("y"));

				return coordinate;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}

		return null;
	}

	@Override
	public Node getNodeFromId(int nodeId) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;

		try {

			String getAllEdges = "SELECT id, name, x, y, map_id, classification FROM routefinder.node where id=?";
			pstmt = conn.prepareStatement(getAllEdges);
			pstmt.setInt(1, nodeId);
			resultSet = null;
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				Node node = new Node();
				node.setId(resultSet.getInt("id"));
				node.setName(resultSet.getString("name"));
				Coordinate c = new Coordinate();
				c.setX(resultSet.getInt("x"));
				c.setY(resultSet.getInt("y"));
				node.setLocation(c);
				node.setMapId(resultSet.getInt("map_id"));
				node.setNodeType(NodeType.valueOf(resultSet.getString("classification").toUpperCase()));
				return node;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}

		return null;
	}

	@Override
	public List<Node> getNodeFromIds(List<Integer> nodeIds) {
		// TODO Auto-generated method stub
		List<Node> res = new ArrayList<Node>();
		Iterator<Integer> iter = nodeIds.iterator();
		while (iter.hasNext()) {
			int nodeId = iter.next();
			res.add(this.getNodeFromId(nodeId));
		}

		return res;
	}

}
