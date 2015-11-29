package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wpi.cs509.teamA.bean.Edge;
import com.wpi.cs509.teamA.bean.Node;
import com.wpi.cs509.teamA.bean.NodeRelation;
import com.wpi.cs509.teamA.dao.NodeRelationDao;
import com.wpi.cs509.teamA.util.Coordinate;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.JdbcConnect;
import com.wpi.cs509.teamA.util.UIDataBuffer;

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
	public int getNodeRelationNum() {
		// TODO Auto-generated method stub
		try {
			String getNodeNum = "select count(*) from routefinder.relations";
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
		}

		// means you get nothing from database..
		// which means error occurs..
		// this line may never get execute then?
		return -1;

	}

	@Override
	public boolean insertOneEdge(NodeRelation nodeRelation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Edge> insertMultipleEdges(Set<Edge> nodeRelation) {
		// TODO Auto-generated method stub
		if (nodeRelation == null) {
			System.out.println(
					"public Set<NodeRelation> insertMultipleEdges(Set<NodeRelation> nodeRelation) ... set of node relations cannot be null..");

			return null;
		}

		try {
			for (Edge edge : nodeRelation) {

				// insert every edge..
				Coordinate firstNodeCoordinate = edge.getNode1().getLocation();
				Coordinate secondNodeCoordinate = edge.getNode2().getLocation();

				int fromId = this.checkNodeInDBByCoordinate(firstNodeCoordinate);
				int endId = this.checkNodeInDBByCoordinate(secondNodeCoordinate);

				if ((fromId > 0) && (endId > 0)) {
					if (!this.checkNodeRelationInDBById(fromId, endId)) {

						double distance = Math.sqrt((secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
								* (secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
								+ (secondNodeCoordinate.getY() - firstNodeCoordinate.getY())
										* (secondNodeCoordinate.getY() - firstNodeCoordinate.getY()));
						String insertEdgeToDB = "INSERT INTO routefinder.relations (node_from, node_to, distance) VALUES (?, ?, ?)";
						pstmt = conn.prepareStatement(insertEdgeToDB);
						pstmt.setInt(1, fromId);
						pstmt.setInt(2, endId);
						pstmt.setDouble(3, distance);
						pstmt.executeUpdate();
						conn.commit();

					} else {

						System.out.println("Edge: " + edge.getNode1().getLocation().getX() + " "
								+ edge.getNode1().getLocation().getY() + " " + edge.getNode2().getLocation().getX()
								+ " " + edge.getNode2().getLocation().getY() + " exists..");

					}
				}

			}

			// get the return value, which is all the node relations exists in
			// the DB..
			return this.getAllNodeRelationsForCurrentMap(UIDataBuffer.getCurrentMapId());

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
			String checkEdgeInDB = "select id from routefinder.relations where node_from=? and node_to=?";
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

			String checkNodesInDB = "select id from routefinder.node where x=? and y=?";
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
	public List<Edge> getAllEdges() {
		// TODO Auto-generated method stub

		List<Edge> res = new ArrayList<Edge>();
		ResultSet resultSet = null;

		try {
			String getAllEdges = "SELECT node_from, node_to, distance FROM routefinder.relations";
			resultSet = null;
			pstmt = conn.prepareStatement(getAllEdges);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Edge edge = new Edge();
				edge.setNode1(Database.getNodeFromId(resultSet.getInt("node_from")));
				edge.setNode2(Database.getNodeFromId(resultSet.getInt("node_to")));
				edge.setDist(resultSet.getDouble("distance"));
				res.add(edge);
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

	@Override
	public List<Edge> getAllNodeRelationsForCurrentMap(int map_id) {

		List<Edge> res = new ArrayList<Edge>();
		// get all the edges from db
		ResultSet resultSet = null;
		try {
			String getAllEdges = "SELECT * FROM routefinder.relations t1 inner join routefinder.node t2 where (t1.node_from = t2.id) AND t2.map_id=?;";
			resultSet = null;
			pstmt = conn.prepareStatement(getAllEdges);
			pstmt.setInt(1, map_id);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {

				Node fromNodeCoordinate = Database.getNodeFromId(resultSet.getInt("node_from"));
				Node toNodeCoordinate = Database.getNodeFromId(resultSet.getInt("node_to"));

				Edge nr = new Edge();
				nr.setNode1(fromNodeCoordinate);
				nr.setNode2(toNodeCoordinate);
				nr.setDist(resultSet.getDouble("distance"));
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

	public boolean deleteOrAddEdge(Node n1, Node n2) {
		int id1 = n1.getId();
		int id2 = n2.getId();
		if (checkNodeRelationInDBById(id1, id2)) {
			try {
				String deleteEdgeFromDB = "delete from routefinder.relations where (node_from=? and node_to =?) or(node_from=? and node_to=?);";
				pstmt = conn.prepareStatement(deleteEdgeFromDB);
				pstmt.setInt(1, id1);
				pstmt.setInt(2, id2);
				pstmt.setInt(3, id2);
				pstmt.setInt(4, id1);
				pstmt.executeUpdate();
				conn.commit();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcConnect.resultClose(rs, pstmt);
				JdbcConnect.connClose();
			}
		}
		else{
			System.out.println("The edge doesn't exist.... inserting....");
			// insert every edge..
			Coordinate firstNodeCoordinate = n1.getLocation();
			Coordinate secondNodeCoordinate = n2.getLocation();

			int fromId = this.checkNodeInDBByCoordinate(firstNodeCoordinate);
			int endId = this.checkNodeInDBByCoordinate(secondNodeCoordinate);

			if ((fromId > 0) && (endId > 0)) {
				if (!this.checkNodeRelationInDBById(fromId, endId)) {

					double distance = Math.sqrt((secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
							* (secondNodeCoordinate.getX() - firstNodeCoordinate.getX())
							+ (secondNodeCoordinate.getY() - firstNodeCoordinate.getY())
									* (secondNodeCoordinate.getY() - firstNodeCoordinate.getY()));
					try{
					String insertEdgeToDB = "INSERT INTO routefinder.relations (node_from, node_to, distance) VALUES (?, ?, ?)";
					pstmt = conn.prepareStatement(insertEdgeToDB);
					pstmt.setInt(1, fromId);
					pstmt.setInt(2, endId);
					pstmt.setDouble(3, distance);
					pstmt.executeUpdate();
					conn.commit();
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						JdbcConnect.resultClose(rs, pstmt);
						JdbcConnect.connClose();
					}

				} else {
					System.out.println("Edge: " + n1.getLocation().getX() + " "
							+ n1.getLocation().getY() + " " + n2.getLocation().getX()
							+ " " + n2.getLocation().getY() + " exists..");

				}
			}
			return false;
		}
		return false;
	}
}
