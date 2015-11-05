package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.dao.NodeDao;
import com.wpi.cs509.teamA.util.Coordinate;

public class NodeDaoImpl implements NodeDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Statement stmt = null;

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
			JdbcConnect.resultClose(rs, stmt);
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

	@Override
	public Set<Node> getAllNodes() {
		// TODO Auto-generated method stub
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
		
		// TODO: Check if the node exists.. the same coordinate should be considered the same node..F
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

}
