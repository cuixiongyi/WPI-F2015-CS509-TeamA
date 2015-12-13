package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wpi.cs509.teamA.bean.OtherFeature;
import com.wpi.cs509.teamA.dao.OtherFeatureDao;
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
