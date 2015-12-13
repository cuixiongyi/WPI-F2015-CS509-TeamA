package com.wpi.cs509.teamA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wpi.cs509.teamA.bean.Major;
import com.wpi.cs509.teamA.dao.MajorDao;
import com.wpi.cs509.teamA.util.JdbcConnect;

public class MajorDaoImpl implements MajorDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public MajorDaoImpl() {
		try {
			// Connect to Database
			conn = JdbcConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Major> getAllMajors() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		List<Major> res = new ArrayList<Major>();
		try {
			String selectAllMajor = "SELECT major,node_id,majorabbr FROM routefinder.major;";
			pstmt = conn.prepareStatement(selectAllMajor);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String majorName = resultSet.getString("major");
				String majorAbbrName = resultSet.getString("majorabbr");
				int node_id = resultSet.getInt("node_id");
				res.add(new Major(majorName, majorAbbrName, node_id));
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
