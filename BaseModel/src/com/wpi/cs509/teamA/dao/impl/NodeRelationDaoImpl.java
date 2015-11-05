package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.strategy.impl.Edge;
import com.wpi.cs509.teamA.util.Coordinate;

// TODO: Using proxy pattern to handle all the database connection

public class NodeRelationDaoImpl implements NodeRelationDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public NodeRelationDaoImpl() {
		try {
			// connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertOneEdge(NodeRelation nodeRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<NodeRelation> insertMultipleEdges(Set<NodeRelation> nodeRelation) {
		// TODO Auto-generated method stub
		if (nodeRelation == null) {
			System.out.println(
					"public Set<NodeRelation> insertMultipleEdges(Set<NodeRelation> nodeRelation) ... set of node relations cannot be null..");

			return null;
		}

		try {
			for (NodeRelation edge : nodeRelation) {

				// insert every edge..
				Coordinate firstNodeCoordinate = edge.getFirstNodeCoordinate();
				Coordinate secondNodeCoordinate = edge.getSecondNodeCoordinate();

				int fromId = this.checkNodeInDBByCoordinate(firstNodeCoordinate);
				int endId = this.checkNodeInDBByCoordinate(secondNodeCoordinate);

				if ((fromId > 0) && (endId > 0)) {
					if (!this.checkNodeRelationInDBById(fromId, endId)) {

						double distance = Math.sqrt((secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
								* (secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
								+ (secondNodeCoordinate.getY() - firstNodeCoordinate.getY())
										* (secondNodeCoordinate.getY() - firstNodeCoordinate.getY()));
						String insertEdgeToDB = "INSERT INTO RouteFinder.relations (node_from, node_to, distance) VALUES (?, ?, ?)";
						pstmt = conn.prepareStatement(insertEdgeToDB);
						pstmt.setInt(1, fromId);
						pstmt.setInt(2, endId);
						pstmt.setDouble(3, distance);
						pstmt.executeUpdate();
						conn.commit();

					} else {

						System.out.println("Edge: " + edge.getFirstNodeCoordinate().getX() + " "
								+ edge.getFirstNodeCoordinate().getY() + " " + edge.getSecondNodeCoordinate().getX()
								+ " " + edge.getSecondNodeCoordinate().getY() + " exists..");

					}
				}

			}

			// get the return value, which is all the node relations exists in
			// the DB..
			return this.getAllNodeRelations();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}

		return null;
	}

	@Override
	public boolean checkNodeRelationInDBById(int id1, int id2) {

		//
		return checkNodeRelationInDBHelper(id1, id2) || checkNodeRelationInDBHelper(id2, id1);

	}

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return true if the edge exist, false if the edge does not exist
	 */
	private boolean checkNodeRelationInDBHelper(int id1, int id2) {

		ResultSet resultSet = null;

		try {
			String checkEdgeInDB = "select id from RouteFinder.relations where node_from=? and node_to=?";
			pstmt = conn.prepareStatement(checkEdgeInDB);
			pstmt.setInt(1, id1);
			pstmt.setInt(2, id2);
			resultSet = null;
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}

		return false;

	}

	@Override
	public int checkNodeInDBByCoordinate(Coordinate coordinate) {

		ResultSet resultSet = null;
		int x = coordinate.getX();
		int y = coordinate.getY();
		try {

			String checkNodesInDB = "select id from RouteFinder.node where x=? and y=?";
			pstmt = conn.prepareStatement(checkNodesInDB);
			pstmt.setInt(1, x);
			pstmt.setInt(2, y);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("id");
			} else {
				System.out.println("Cannot find node " + x + ", " + y);
				return -1;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}
	}

	@Override
	public int checkNodeRelationInDBByNodeRelation(NodeRelation[] nodeRelation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NodeRelation> getAllNodeRelations() {

		Set<NodeRelation> res = new HashSet<NodeRelation>();
		// get all the edges from db
		ResultSet resultSet = null;
		try {
			String getAllEdges = "SELECT node_from, node_to FROM RouteFinder.relations";
			resultSet = null;
			pstmt = conn.prepareStatement(getAllEdges);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				Coordinate fromNodeCoordinate = new NodeDaoImpl()
						.getNodeCoordinateFromId(resultSet.getInt("node_from"));
				Coordinate toNodeCoordinate = new NodeDaoImpl().getNodeCoordinateFromId(resultSet.getInt("node_to"));

				NodeRelation nr = new NodeRelation();
				nr.setFirstNodeCoordinate(fromNodeCoordinate);
				nr.setSecondNodeCoordinate(toNodeCoordinate);
				res.add(nr);
			}

			return res;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(resultSet, pstmt);
		}

		return null;

	}

}
