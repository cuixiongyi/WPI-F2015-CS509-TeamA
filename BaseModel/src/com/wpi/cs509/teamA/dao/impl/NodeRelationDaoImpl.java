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
					"public Set<NodeRelation> insertMultipleEdges(Set<NodeRelation> nodeRelation) ... nodeRelation cannot be null..");

			return null;
		}

		try {
			for (NodeRelation edge : nodeRelation) {
				// insert every edge..
				Coordinate firstNodeCoordinate = edge.getFirstNodeCoordinate();
				Coordinate secondNodeCoordinate = edge.getSecondNodeCoordinate();

				int fromId = this.checkNodeInDB(firstNodeCoordinate);
				int endId = this.checkNodeInDB(secondNodeCoordinate);

				if ((fromId > 0) && (endId > 0)) {
					if (!this.checkEdgeInDB(fromId, endId)) {

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

						System.out.println(edge.getFirstNodeCoordinate().getX() + " "
								+ edge.getFirstNodeCoordinate().getY() + " " + edge.getSecondNodeCoordinate().getX()
								+ " " + edge.getSecondNodeCoordinate().getY() + " exists..");

					}
				}

			}

			return this.getAllEdeges();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}

		return null;
	}

	private Set<NodeRelation> getAllEdeges() {

		Set<NodeRelation> res = new HashSet<NodeRelation>();
		// get all the edges from db
		ResultSet resultSet = null;
		try {
			String getAllEdges = "SELECT node_from, node_to FROM RouteFinder.relations";
			resultSet = null;
			pstmt = conn.prepareStatement(getAllEdges);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Coordinate fromNodeCoordinate = getNodeCoordinateFromId(resultSet.getInt("node_from"));
				Coordinate toNodeCoordinate = getNodeCoordinateFromId(resultSet.getInt("node_to"));

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

	private Coordinate getNodeCoordinateFromId(int nodeId) {

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

	private int checkNodeInDB(Coordinate coordinate) {

		if (conn == null) {
			System.out.println("The Connection has not built yet..");
			return -1;
		} else {
			ResultSet resultSet = null;
			try {

				String checkNodesInDB = "select id from RouteFinder.node where x=? and y=?";
				pstmt = conn.prepareStatement(checkNodesInDB);
				pstmt.setInt(1, coordinate.getX());
				pstmt.setInt(2, coordinate.getY());
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					return resultSet.getInt("id");
				} else {
					System.out.println("Cannot find node x1, y1");
					return -1;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				return -1;
			} finally {
				JdbcConnect.resultClose(resultSet, pstmt);
			}
		}

	}

	/**
	 * 
	 * 
	 * @param id1
	 * @param id2
	 * @return true if the edge exist false if the edge does not exist
	 */
	private boolean checkEdgeInDB(int id1, int id2) {

		if (conn == null) {
			System.out.println("The Connection has not built yet..");
			return true;
		} else {
			ResultSet resultSet = null;
			try {
				String checkEdgeInDB = "select id from RouteFinder.relations where node_from=? and node_to=?";
				pstmt = conn.prepareStatement(checkEdgeInDB);
				pstmt.setInt(1, id1);
				pstmt.setInt(2, id2);
				resultSet = null;
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					System.out.println("Edge exists!");
					return true;
				}

				// check reverse..
				resultSet = null;
				pstmt.setInt(1, id2);
				pstmt.setInt(2, id1);
				resultSet = pstmt.executeQuery();
				if (resultSet.next()) {
					System.out.println("Edge exists!");
					return true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				return true;
			} finally {
				JdbcConnect.resultClose(resultSet, pstmt);
			}
		}

		return false;

	}

	@Override
	public int checkRelationInDB(NodeRelation[] nodeRelation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		return null;
	}

}
