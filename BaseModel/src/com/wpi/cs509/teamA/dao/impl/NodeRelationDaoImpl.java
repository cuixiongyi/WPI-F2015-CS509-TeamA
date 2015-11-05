package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public boolean insertMultipleEdges(Set<NodeRelation> nodeRelation) {
		// TODO Auto-generated method stub
		try {
			for (NodeRelation edge : nodeRelation) {
				// insert every edge..
				Coordinate firstNodeCoordinate = edge.getFirstNodeCoordinate();
				Coordinate secondNodeCoordinate = edge.getSecondNodeCoordinate();

				int fromId = this.checkNodeInDB(firstNodeCoordinate);
				int endId = this.checkNodeInDB(secondNodeCoordinate);

				if ((fromId > 0) && (endId > 0)) {
					if (!this.checkEdgeInDB(fromId, endId)) {
						System.out.println("++++++++++++++++");
						System.out.println(fromId);
						System.out.println(endId);
						System.out.println("++++++++++++++++");
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

					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
			JdbcConnect.connClose();
		}

		return false;
	}

	private int checkNodeInDB(Coordinate coordinate) {

		if (conn == null) {
			System.out.println("The Connection has not built yet..");
			return -1;
		} else {
			try {
				String checkNodesInDB = "select id from RouteFinder.node where x=? and y=?";
				pstmt = conn.prepareStatement(checkNodesInDB);
				pstmt.setInt(1, coordinate.getX());
				pstmt.setInt(2, coordinate.getY());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getInt("id");
				} else {
					System.out.println("Cannot find node x1, y1");
					return -1;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				return -1;
			} finally {
				JdbcConnect.resultClose(rs, pstmt);
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
			try {
				String checkEdgeInDB = "select id from RouteFinder.relations where node_from=? and node_to=?";
				pstmt = conn.prepareStatement(checkEdgeInDB);
				pstmt.setInt(1, id1);
				pstmt.setInt(2, id2);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("Edge exists!");
					return true;
				}
			} catch (SQLException se) {
				System.out.println("fail to connect database");
				se.printStackTrace();
				return true;
			} finally {
				JdbcConnect.resultClose(rs, pstmt);
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
