package com.wpi.cs509.teamA.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.wpi.cs509.teamA.persistence.bean.Node;
import com.wpi.cs509.teamA.persistence.bean.OtherFeature;
import com.wpi.cs509.teamA.persistence.dao.OtherFeatureDao;
import com.wpi.cs509.teamA.util.Database;
import com.wpi.cs509.teamA.util.JdbcConnect;

public class OtherFeatureDaoImpl implements OtherFeatureDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public OtherFeatureDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveAllOtherFeatures(OtherFeature otherFeature) {
		// TODO Auto-generated method stub
		try {
			String insertOtherFeaturesToDB = "INSERT INTO routefinder.other_features (label,node_id) VALUES (?, ?)";
			pstmt = conn.prepareStatement(insertOtherFeaturesToDB);
			pstmt.setString(1, otherFeature.getFeatureLabel());
			pstmt.setInt(2, otherFeature.getNodeId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("fail to connect database..");
			se.printStackTrace();
		} finally {
			JdbcConnect.resultClose(rs, pstmt);
		}
	}

	public List<OtherFeature> getAllOtherFeatures() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<OtherFeature> res = new ArrayList<OtherFeature>();
		try {
			String selectAllOtherFeatures = "SELECT label,node_id FROM routefinder.other_features;";
			pstmt = conn.prepareStatement(selectAllOtherFeatures);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.add(new OtherFeature(resultSet.getString("label"), resultSet.getInt("node_id")));
			}
			// System.out.println("res size: "+res.size());
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
	public List<String> getAllFeatureLabels() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<String> res = new ArrayList<String>();
		try {
			String selectAllOtherFeatures = "SELECT distinct(label) FROM routefinder.other_features;";
			pstmt = conn.prepareStatement(selectAllOtherFeatures);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.add(resultSet.getString("label"));
			}
			// System.out.println("res size: "+res.size());
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
	public List<Node> getListofNodesWithLabel(String newLabel) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<Node> res = new ArrayList<Node>();
		try {
			String selectAllOtherFeaturesLabel = "SELECT distinct(node_id) FROM routefinder.other_features where label=?;";
			pstmt = conn.prepareStatement(selectAllOtherFeaturesLabel);
			pstmt.setString(1, newLabel);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				res.add(Database.getNodeFromId(resultSet.getInt("node_id")));
			}
			// System.out.println("res size: "+res.size());
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
}
